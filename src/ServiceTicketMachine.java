public interface ServiceTicketMachine {
    int MAX_SHEETS = 8;
    int FULL_TONER_LEVEL = 15;
    int MIN_TONER_LEVEL = 2;

    boolean isTonerTechnicianDone();

    boolean isPaperTechnicianDone();

    void replaceTonerCartridge();
    void refillTicketPaper();
    void printTicket(Ticket ticket);
    int getPaperLevel();
    int getTonerLevel();

    void setTonerTechnicianDone();
    void setPaperTechnicianDone();


}
