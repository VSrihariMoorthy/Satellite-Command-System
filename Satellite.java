// Satellite class contains the data members and member functions that are used to manipulate the satellite.
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Satellite {
    private String orientation;
    private String solarPanels;
    private int dataCollected;

    // Logger object
    private static final Logger LOGGER = Logger.getLogger(Satellite.class.getName());

    // Constructor to initialize the data members - initial state of the satellite
    public Satellite() {
        this.orientation = "North";
        this.solarPanels = "Inactive";
        this.dataCollected = 0;
    }

    // Function to Rotate the satellite to a given direction
    public boolean rotate(String direction) {
        Set<String> validDirections = new HashSet<>(Arrays.asList("North", "South", "East", "West"));
        // Checks if the satellite is already facing the given direction
        if (direction.equals(this.getOrientation())){
            LOGGER.warning("Satellite is already facing " + direction);
            return true;
        }
        if (validDirections.contains(direction)) {
            this.orientation = direction;
            LOGGER.info("Satellite rotated to " + direction);
            return true;
        }
        else{
            LOGGER.log(Level.SEVERE, "Invalid direction provided.");
            return false;
        }
    }

    public void activatePanels() {
        if ("Inactive".equals(this.solarPanels)) {
            this.solarPanels = "Active";
            LOGGER.info("Solar panels activated");
        } else {
            LOGGER.warning("Solar panels are already active");
        }
    }

    public void deactivatePanels() {
        if ("Active".equals(this.solarPanels)) {
            this.solarPanels = "Inactive";
            LOGGER.info("Solar panels deactivated");
        } else {
            LOGGER.warning("Solar panels are already inactive");
        }
    }

    public boolean collectData() {
        if ("Active".equals(this.solarPanels)) {
            this.dataCollected += 10;
            LOGGER.info("Data collected successfully");
            return true;
        } else {
            LOGGER.severe("Cannot collect data. Solar panels are inactive");
            return false;
        }
    }

    public String getOrientation() {
        return this.orientation;
    }

    public String getSolarPanels() {
        return this.solarPanels;
    }

    public int getDataCollected() {
        return this.dataCollected;
    }
}
