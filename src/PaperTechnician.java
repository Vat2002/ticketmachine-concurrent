import java.util.Random;

public class PaperTechnician implements Runnable{

    public static final int NUMBER_OF_TRIES = 3;


    public static int ATTEMPT_COUNT;

    private final ServiceTicketMachine ticketMachine;
    private final String name;

    private final ThreadGroup threadGroup;

    public PaperTechnician(String name, ServiceTicketMachine ticketMachine, ThreadGroup threadGroup) {
        this.name = name;
        this.ticketMachine = ticketMachine;
        this.threadGroup = threadGroup;
    }

    @Override
    public void run() {

        for (int i = 0; i < NUMBER_OF_TRIES; i++) {
            if(ticketMachine.getPaperLevel() <=0) {
                ticketMachine.refillTicketPaper();
            }
            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ticketMachine.setPaperTechnicianDone();

    }
}
