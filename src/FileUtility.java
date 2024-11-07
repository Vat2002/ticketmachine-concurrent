import java.io.FileWriter;
        import java.io.IOException;
        import java.io.PrintWriter;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class FileUtility {

    private static final String OUTPUT_FILE_PATH = "output.txt";
    private static boolean firstWrite = true;

    public static synchronized void writeToFile(String message) {
        String timestamp = new SimpleDateFormat("EEEE, MMMM dd, yyyy HH:mm:ss a").format(new Date());
        String fullMessage = "[ " + timestamp + " ] " +  message;

        try (FileWriter fileWriter = new FileWriter(OUTPUT_FILE_PATH, firstWrite ? false : true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(fullMessage);
            if (firstWrite) {
                firstWrite = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

