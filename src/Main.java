import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {


        //creating thread groups for technicians and passengers
        ThreadGroup groupTechnicians = new ThreadGroup("Technicians");
        ThreadGroup groupPassengers = new ThreadGroup("Passengers");

        ThreadGroup tonerTechGroup = new ThreadGroup(groupTechnicians, "TonerTechnicians");
        ThreadGroup paperTechGroup = new ThreadGroup(groupPassengers, "PaperTechnicians");

        //creating passenger info and travel info objects
        PassengerInformation passengerInfo1 = new PassengerInformation("James", "0763423684", "James@exampleemail.com");
        PassengerInformation passengerInfo2 = new PassengerInformation("Noah", "0778731338", "noah@exampleemail.com");
        PassengerInformation passengerInfo3 = new PassengerInformation("Jack", "07752786562", "jack@exampleemail.com");
        PassengerInformation passengerInfo4 = new PassengerInformation("John", "0773964260", "john@exampleemail.com");

        TravelInformation journey1 = new TravelInformation("Kandy", "Colombo");
        TravelInformation journey2 = new TravelInformation("Jaffna", "Gampaha");
        TravelInformation journey3 = new TravelInformation("Galle", "Mattara");
        TravelInformation journey4 = new TravelInformation("Nuwara Eliya", "Hambantota");

        //creating ticket objects
        Ticket passengerTicket1 = new Ticket(1, new BigDecimal(1000), passengerInfo1, journey1);
        Ticket passengerTicket2 = new Ticket(2, new BigDecimal(2000), passengerInfo2, journey2);
        Ticket passengerTicket3 = new Ticket(3, new BigDecimal(3000), passengerInfo3, journey3);
        Ticket passengerTicket4 = new Ticket(4, new BigDecimal(4000), passengerInfo4, journey4);

        //creating ticket machine object
        ServiceTicketMachine ticketMachine = new TicketingMachine(2, 7);

        //creating passenger and technician objects
        Passenger firstPassenger = new Passenger(ticketMachine, passengerTicket1, groupPassengers);
        Passenger secondPassenger = new Passenger(ticketMachine, passengerTicket2, groupPassengers);
        Passenger thirdPassenger = new Passenger(ticketMachine, passengerTicket3, groupPassengers);
        Passenger fourthPassenger = new Passenger(ticketMachine, passengerTicket4, groupPassengers);

        TonerTechnician ticketTonerTechnician = new TonerTechnician("William", ticketMachine, tonerTechGroup);
        PaperTechnician ticketPaperTechnician = new PaperTechnician("Adan", ticketMachine, paperTechGroup);

        //creating passenger and technician threads
        Thread threadPassenger1 = new Thread(firstPassenger, "James");
        Thread threadPassenger2 = new Thread(secondPassenger, "Noah");
        Thread threadPassenger3 = new Thread(thirdPassenger, "Jack");
        Thread threadPassenger4 = new Thread(fourthPassenger, "John");

        Thread threadTonerTech = new Thread(ticketTonerTechnician, "William");
        Thread threadPaperTech = new Thread(ticketPaperTechnician, "Adan");


        Thread[] threads = {threadPassenger1, threadPassenger2, threadPassenger3, threadPassenger4,threadTonerTech, threadPaperTech};

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Join all threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Printing final status of the TicketSystem
        FileUtility.writeToFile("System Summary.."+"\n"+"*****Ticket system final status *****" +"\n" +"     Paper level status :" +ticketMachine.getPaperLevel() + "\n" +"     Toner level status :"+ ticketMachine.getTonerLevel());
        System.out.println("System Summary.."+"\n"+"*****Ticket system final status *****" +"\n" +"     Paper level status :" +ticketMachine.getPaperLevel() + "\n" +"     Toner level status :"+ ticketMachine.getTonerLevel());

    }
}