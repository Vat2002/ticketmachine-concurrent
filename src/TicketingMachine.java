public class TicketingMachine implements ServiceTicketMachine{

    private volatile boolean tonerTechnicianDone = false;
    private volatile boolean paperTechnicianDone = false;

    private int paperLevel;
    private int tonerLevel;



    public void setTonerTechnicianDone() {
        tonerTechnicianDone = true;
    }

    @Override
    public boolean isTonerTechnicianDone() {
        return tonerTechnicianDone;
    }

    public void setPaperTechnicianDone() {
        paperTechnicianDone = true;
    }

    @Override
    public boolean isPaperTechnicianDone() {
        return paperTechnicianDone;
    }

    public TicketingMachine(int paperLevel, int tonerLevel) {
        this.paperLevel = paperLevel;
        this.tonerLevel = tonerLevel;
    }

    private boolean isResourceAvailable() {

        return paperLevel > 0 && tonerLevel >= ServiceTicketMachine.MIN_TONER_LEVEL;
      }


    private void printTicket() {

        tonerLevel -= 1;
        paperLevel -= 1;

    }

    @Override
    public synchronized void replaceTonerCartridge() {

        try {
            while (tonerLevel >= ServiceTicketMachine.MIN_TONER_LEVEL) {     //if toner level is greater than minimum toner level
                System.out.println(Thread.currentThread().getName() + " is waiting as the toner level is sufficient.");
                FileUtility.writeToFile(Thread.currentThread().getName() + " is waiting as the toner level is sufficient.");
                wait();
            }
            tonerLevel = ServiceTicketMachine.FULL_TONER_LEVEL; //add toner
            System.out.println(Thread.currentThread().getName() + " replaced the toner cartridge with a full one.");
            FileUtility.writeToFile(Thread.currentThread().getName() + " replaced the toner cartridge with a full one.");
            notifyAll();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public  synchronized void refillTicketPaper() {
        try {


            while (paperLevel > 0) {
                FileUtility.writeToFile(Thread.currentThread().getName() + " is waiting as there is still paper available.");
                System.out.println(Thread.currentThread().getName() + " is waiting as there is still paper available.");
                wait();
            }

            paperLevel += ServiceTicketMachine.MAX_SHEETS; //add paper
            System.out.println(Thread.currentThread().getName() + " added " + ServiceTicketMachine.MAX_SHEETS + " sheets of paper.");
            FileUtility.writeToFile(Thread.currentThread().getName() + " added " + ServiceTicketMachine.MAX_SHEETS + " sheets of paper.");
            notifyAll();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public  synchronized  void printTicket(Ticket ticket) {

        try {

            while (!isResourceAvailable()) {

                if (paperLevel <= 0 && tonerLevel < ServiceTicketMachine.MIN_TONER_LEVEL) { //if paper level is less than 0 and toner level is greater than minimum toner level
                    System.out.println("Out of paper and toner. " + Thread.currentThread().getName() + " is waiting for resources.");
                    FileUtility.writeToFile("Out of paper and toner. " + Thread.currentThread().getName() + " is waiting for resources.");

                } else if (tonerLevel < ServiceTicketMachine.MIN_TONER_LEVEL) {
                    System.out.println("Out of toner. " + Thread.currentThread().getName() + " is waiting for toner refill.");
                    FileUtility.writeToFile("Out of toner. " + Thread.currentThread().getName() + " is waiting for toner refill.");
                } else {
                    System.out.println("Out of paper. " + Thread.currentThread().getName() + " is waiting for paper refill.");
                    FileUtility.writeToFile("Out of paper. " + Thread.currentThread().getName() + " is waiting for paper refill.");
                }

                if (isTonerTechnicianDone()) {
                    System.out.println("Toner technician has completed. Exiting " + Thread.currentThread().getName());
                    FileUtility.writeToFile("Toner technician has completed. Exiting " + Thread.currentThread().getName());
                    return;  // Terminate passenger thread
                }

                // Check if paper technician thread is terminated
                if (isPaperTechnicianDone()) {
                    System.out.println("Paper technician has completed. Exiting " + Thread.currentThread().getName());
                    FileUtility.writeToFile("Paper technician has completed. Exiting " + Thread.currentThread().getName());
                    return;  // Terminate passenger thread
                }

              //  hasNoResources.await(500, java.util.concurrent.TimeUnit.MILLISECONDS);
                wait(500);

            }

            printTicket();
            System.out.println("Ticket was printed by " + Thread.currentThread().getName() + "\n" + ticket.toString());
            FileUtility.writeToFile("Ticket was printed by " + Thread.currentThread().getName() + "\n" + ticket.toString());

            notifyAll();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getPaperLevel() {
        return paperLevel;
    }

    @Override
    public int getTonerLevel() {
        return tonerLevel;
    }
}
