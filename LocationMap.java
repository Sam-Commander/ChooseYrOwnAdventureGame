import java.io.*;
import java.util.*;

public class LocationMap implements Map<Integer, Location>
{
    private static final String LOCATIONS_FILE_NAME =  "locations.txt";
    private static final String DIRECTIONS_FILE_NAME =  "directions.txt";

    static HashMap<Integer, Location> locations = new HashMap<>();

    // Reads from LOCATIONS_FILE_NAME

    static
    {
        FileLogger fl = new FileLogger();
        ConsoleLogger cl = new ConsoleLogger();

        // Prints locations.txt to console via ConsoleLogger

        try (Scanner input = new Scanner(new File(LOCATIONS_FILE_NAME)))
        {
            cl.log("Available locations:");
            while (input.hasNextLine())
            {
                cl.log(input.nextLine().replaceAll(",(?!\\s)", ": "));
            }
        }catch(Exception e){
            fl.log(Arrays.toString(e.getStackTrace()) + "\n");
            cl.log(Arrays.toString(e.getStackTrace()));
        }

        /* extracts locationId and description from locations.txt and adds to locations HashMap
        along with Location obj containing null exits */

        try(BufferedReader br = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME)))
        {
            String read;
            while ((read = br.readLine()) != null)
            {
                String[] sections = read.split(",", 2);
                if (sections.length >= 2)
                {
                    String key = sections[0]; // becomes locationNumber
                    int locationNumber = Integer.parseInt(key);
                    String locationDescription = sections[1];
                    Location locationObj = new Location(locationNumber, locationDescription, new HashMap<>());
                    locations.put(locationNumber, locationObj);
                }
            }
        }catch(Exception e){
            fl.log(Arrays.toString(e.getStackTrace()) + "\n");
            cl.log(Arrays.toString(e.getStackTrace()));
        }

        // Writes locations.txt to StudentFileOutput.txt via FileLogger

        try(BufferedReader brForFileTwo = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME)))
        {
            String locationsAsString;
            fl.log("Available locations:\n");
            while ((locationsAsString = brForFileTwo.readLine()) != null)
            {
                locationsAsString = locationsAsString.replaceAll(",(?!\\s)", ": ");
                fl.log(locationsAsString + "\n");
            }
        }catch(Exception e){
            fl.log(Arrays.toString(e.getStackTrace()) + "\n");
            cl.log(Arrays.toString(e.getStackTrace()));
        }

        // Prints directions.txt to console via ConsoleLogger

        try(BufferedReader input2 = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME)))
        {
            String directionsAsString;
            cl.log("Available directions:");
            while ((directionsAsString = input2.readLine()) != null)
            {
                directionsAsString = directionsAsString.replaceAll(",", ": ");
                cl.log(directionsAsString);
            }
        }catch(Exception e){
            fl.log(Arrays.toString(e.getStackTrace()) + "\n");
            cl.log(Arrays.toString(e.getStackTrace()));
        }

        // Appends directions.txt to StudentFileOutput.txt via FileLogger

        try(BufferedReader brForFile = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME)))
        {
            String directionsAppended;
            fl.log("Available directions:\n");
            while ((directionsAppended = brForFile.readLine()) != null)
            {
                directionsAppended = directionsAppended.replaceAll(",", ": ");
                fl.log(directionsAppended + "\n");
            }
        }catch(Exception e){
            fl.log(Arrays.toString(e.getStackTrace()) + "\n");
            cl.log(Arrays.toString(e.getStackTrace()));
        }

        // Extracts the exits (direction, destination) from directions.txt and adds them to Location object in locations HashMap

        try(BufferedReader brForExits = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME)))
        {
            String readNow;
            while((readNow = brForExits.readLine()) != null)
            {
                String[] sections = readNow.split(",", 3);
                if (sections.length >= 3)
                {
                    String iD = sections[0];
                    int iDAsInt = Integer.parseInt(iD);
                    String direction = sections[1];
                    String destination = sections[2];
                    int destinationAsInt = Integer.parseInt(destination);
                    if(locations.containsKey(iDAsInt))
                    {
                        locations.get(iDAsInt).addExit(direction, destinationAsInt);
                    }
                }
            }
        }catch(Exception e){
            fl.log(Arrays.toString(e.getStackTrace()) + "\n");
            cl.log(Arrays.toString(e.getStackTrace()));
        }
    }

    // Implements all methods (stipulation of mark scheme)

    @Override
    public int size() {return locations.size();}

    @Override
    public boolean isEmpty() {return locations.isEmpty();}

    @Override
    public boolean containsKey(Object key) {return locations.containsKey(key);}

    @Override
    public boolean containsValue(Object value) {return locations.containsValue(value);}

    @Override
    public Location get(Object key) {return locations.get(key);}

    @Override
    public Location put(Integer key, Location value) {return locations.put(key, value);}

    @Override
    public Location remove(Object key) {return locations.remove(key);}

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {HashMap<Integer, Location> copyOf = new HashMap<>(locations);}

    @Override
    public void clear() {locations.clear();}

    @Override
    public Set<Integer> keySet() {return locations.keySet();}

    @Override
    public Collection<Location> values() {return locations.values();}

    @Override
    public Set<Entry<Integer, Location>> entrySet() {return locations.entrySet();}
}
