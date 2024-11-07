public class TravelInformation {

    String destination;
    String departureLocation;
    public TravelInformation(String destination, String departureLocation) {
        this.destination = destination;
        this.departureLocation = departureLocation;
    }

    @Override
    public String toString () {
        return String.format("%n" +
                        "*  Destination: %s,%n" +
                        "*  Departure Location: %s%n" +
                        "***********************************************************%n",
                destination, departureLocation);
    }
}