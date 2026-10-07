import java.util.*;

public class ScanController {
    private final List<Scan> queue = new ArrayList<>();

    private Scan current = null;
    private boolean running = false;
    private boolean exited = false;

    public synchronized void handleCommand(String command){

        String[] parts = command.trim().split(":", 2);
        String name = parts[0].trim().toLowerCase();
        String arg = parts.length > 1 ? parts[1].trim() : "";

        switch (name) {
            case "add":    add(arg);    break;
            case "view":   view();      break;
            case "start":  start();     break;
            case "stop":   stop();      break;
            case "remove": remove(arg); break;
            case "exit":   exit();      break;
            default:       System.out.println("Unknown command: " + command.trim());}
    }

    public synchronized boolean isActive() {
        return !exited;
    }

    private void remove(int id){
        if(running == true){
            System.out.println("Scan is running");
        }else if(current != null) {
            current.setState(ScanState.RUNNING);
            current = null;
        }
    }
    private void exit() {
        exited = true;
        if (current != null) {
            current.setState(ScanState.CANCELLED);
            current = null;
        }
        System.out.println("Exit");
    }
}
