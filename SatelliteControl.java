// Satellite Control System
// SatelliteControl implements the logic behind the system that controls the satellite.
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.util.logging.FileHandler;


public class SatelliteControl {
    private static final Logger LOGGER = Logger.getLogger(SatelliteControl.class.getName());

    static {
        try {
            // Creating a FileHandler that writes log messages to a file named "log.log"
            FileHandler fileHandler = new FileHandler("log.log", true);

            // Setting the logging level for the handlers and logger
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
            
            // Setting the formatter for the handler
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            
            // Adding the handler to the logger
            Logger.getLogger("").addHandler(fileHandler);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error setting up handlers", e);
        }
    }

    // Driver code - Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Satellite satellite = new Satellite();
            System.out.println("Welcome to Satellite Command System!");
            displaySatelliteState(satellite);
            while (true) {
                // Menu like interface to accept commands
                System.out.println("\nEnter a set of commands from below separated by spaces\n1. rotate('Direction')\n2. activatePanels\n3. deactivatePanels\n4. collectData\n(Example: rotate(South) activatePanels collectData)\n");
                String commands = scanner.nextLine();
                Scanner commandScanner = new Scanner(commands);
                try{
                    while (commandScanner.hasNext()) {
                        String command = commandScanner.next();
                        
                        if (command.matches("^rotate\\((.*?)\\)$")) {
                            String direction = command.substring(7, command.length() - 1);
                            if(satellite.rotate(direction)==false){
                                System.out.println("Aborting Subsequent Commands..."); 
                                break;
                            }
                        } else if (command.equals("activatePanels")) {
                            satellite.activatePanels();
                        } else if (command.equals("deactivatePanels")) {                
                            satellite.deactivatePanels();
                        } else if (command.equals("collectData")) {
                            if(satellite.collectData()==false){
                                System.out.println("Aborting Subsequent Commands..."); 
                                break;
                            }
                        } else {
                            // If one single command is invalid, aborts rest of the command sequence and displays the current satellite state
                            LOGGER.log(Level.SEVERE, "Invalid command sequence: " + command);
                            System.out.println("Aborting Subsequent Commands..."); 
                            break;
                        }
                    }
                }
                finally {
                    if (commandScanner != null) {
                        commandScanner.close();
                    }
                }
                // Display current satellite state
                displaySatelliteState(satellite);
                System.out.print("\nDo you want to continue (yes/no)? ");
                String continueOption = scanner.nextLine().toLowerCase();
                if (!"yes".equals(continueOption)) {
                    System.out.println("Exiting Satellite Command System...");
                    break;
                }
            }
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    // Function to display the current satellite state
    private static void displaySatelliteState(Satellite satellite) {
        System.out.println("\nCurrent Satellite State:");
        System.out.println("Orientation: " + satellite.getOrientation());
        System.out.println("Solar Panels: " + satellite.getSolarPanels());
        System.out.println("Data Collected: " + satellite.getDataCollected());
    }
}

