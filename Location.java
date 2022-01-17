import java.util.LinkedHashMap;
import java.util.Map;

public class Location
{
    private final int locationId;
    private final String description;
    private final Map<String, Integer> exits = new LinkedHashMap<>();

    // Handles player input for exits

    public Location(int locationId, String description, Map<String, Integer> exits)
    {
        this.locationId = locationId;
        this.description = description;

        // Sets the available exits if they're not null, otherwise sets them to quit game

        if(exits != null)
        {
            this.exits.putAll(exits);
        }
        else
        {
            this.exits.put("Q", 0);
        }
    }

    // Puts direction and location (exits) in the exits LinkedHashMap

    protected void addExit(String direction, int location) {exits.put(direction, location);}

    // Getters

    public int getLocationId() {return locationId;}
    public String getDescription() {return description;}
    public Map<String, Integer> getExits() {return exits;}
}
