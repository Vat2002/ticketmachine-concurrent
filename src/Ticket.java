import java.math.BigDecimal;

public class Ticket{

    private final int ticketNumber;
    private final BigDecimal ticketPrice;

    private final PassengerInformation passengerInformation;

    private final TravelInformation locationInfo;

    public Ticket(int ticketNumber, BigDecimal ticketPrice, PassengerInformation passengerInformation, TravelInformation locationInfo) {
        this.ticketNumber = ticketNumber;
        this.ticketPrice = ticketPrice;
        this.passengerInformation = passengerInformation;
        this.locationInfo = locationInfo;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public PassengerInformation getPassengerInfo() {
        return passengerInformation;
    }

    public TravelInformation getLocationInfo() {
        return locationInfo;
    }

    @Override
    public String toString() {
        return String.format("***********************************************************%n" +
                        "*  Ticket Details :%n"+
                        "*    ticketId:%d,%n" +
                        "*    ticketPrice:%.2f%n" +
                        "*%n" +
                        "* passenger Information:%s%n" +
                        "* Travel Information:%s%n",
                ticketNumber, ticketPrice, passengerInformation, locationInfo);
    }
}
