

import java.util.Random;

public class Passenger implements Runnable{

    private final ServiceTicketMachine ticketMachine;
    private final Ticket ticket;

    private final ThreadGroup threadGroup;


    public Passenger(ServiceTicketMachine ticketMachine, Ticket ticket, ThreadGroup threadGroup) {
        this.ticketMachine = ticketMachine;
        this.ticket = ticket;
        this.threadGroup = threadGroup;

    }


    @Override
    public void run() {

        Random random = new Random();
                ticketMachine.printTicket(this.ticket);
                try {
                    Thread.sleep(random.nextInt(100) + 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

}

