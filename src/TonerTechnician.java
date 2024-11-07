import java.util.Random;

public class TonerTechnician implements Runnable{

    public static final int NUM_OF_TRIES = 3;
    public static  int ATTEMPT_COUNT = 0;

    private final ServiceTicketMachine ticketMachine;

    private final String name;

    private final ThreadGroup threadGroup;

    public TonerTechnician(String name, ServiceTicketMachine ticketMachine, ThreadGroup threadGroup) {
        this.name = name;
        this.ticketMachine = ticketMachine;
        this.threadGroup = threadGroup;
    }

    @Override
    public void run() {


        for(int i = 0; i < NUM_OF_TRIES; i++){
            if(ticketMachine.getTonerLevel() < ServiceTicketMachine.MIN_TONER_LEVEL){
                ticketMachine.replaceTonerCartridge();
            }

            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ticketMachine.setTonerTechnicianDone();



    }
}
