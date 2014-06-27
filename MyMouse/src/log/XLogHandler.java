package log;

public class XLogHandler {
    public String XLogReport(String address, String message) {
        System.out.println("From: " + address);
        System.out.println("Message: " + message);
        return "ack";
    }
}