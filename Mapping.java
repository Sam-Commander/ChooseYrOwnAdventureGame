import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping
{
    public static final int INITIAL_LOCATION = 95;
    static LocationMap LM = new LocationMap();
    FileLogger fl2 = new FileLogger();
    ConsoleLogger cl2 = new ConsoleLogger();

    // Vocabulary HashMap to store all directions a user can go

    HashMap<String, String> vocabulary = new HashMap<>();

    // Puts recognisable player inputs into vocabulary HashMap

    public Mapping()
    {
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");
        vocabulary.put("DOWN", "D");
        vocabulary.put("UP", "U");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("NORTHWEST", "NW");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("SOUTHWEST", "SW");
    }

    // Runs the game

    public void mapping()
    {
        Scanner sc = new Scanner(System.in);

        int playerLocation = INITIAL_LOCATION;

        while (true) {

            // Prints player location

            cl2.log(LM.get(playerLocation).getDescription());
            fl2.log(LM.get(playerLocation).getDescription() + "\n");

            // Verifies if player location is an exit, if so, exits game

            if(LM.get(playerLocation).getExits().containsValue(playerLocation))
            {
                break;
            }

            // Puts current location's direction options (exits) into a HashMap

            Map<String, Integer> newMap = new HashMap<>(LM.get(playerLocation).getExits());

            // Prints the available direction options (exits)

            String temp = LM.get(playerLocation).getExits().toString();
            temp = temp.replaceAll("[^A-Za-z0-9]", "");
            temp = temp.replaceAll("[0-9]", " ");
            temp = temp.replaceAll("\\s+", ", ");

            cl2.log("Available exits are " + temp);
            fl2.log("Available exits are " + temp + "\n");

            // Takes player input

            String choice;
            choice = sc.nextLine().toUpperCase().trim();

            /* Uses the vocabulary HashMap to extract direction player wants to go from
            player input. Available inputs are: a letter(the HashMap value), a word
            (the HashMap key), a string of words that contains the key */

            if(choice.contains(" ")) {
                choice = choice.replaceAll("[^a-zA-Z0-9\\s]", "");
                String[] sections = choice.split(" ");
                for (String iteratePlayerInput : sections) {
                    for (Map.Entry<String, String> iterateVocabHashMap : vocabulary.entrySet()) {
                        if (iterateVocabHashMap.getKey().equals(iteratePlayerInput)) {
                            choice = iterateVocabHashMap.getValue();
                        }
                    }
                }
            }

            // If input matches vocabulary key, then set 'choice' as that key's value

            for(Map.Entry<String, String> iterate: vocabulary.entrySet())
            {
                if(iterate.getKey().equals(choice)){
                    choice = iterate.getValue();
                }
            }

            // If input or 'choice' (from above) matches key of newMap then set 'playerLocation' as that key's value

            boolean found = false;

            for (Map.Entry<String, Integer> iterateNewMap : newMap.entrySet())
            {
                if (iterateNewMap.getKey().equals(choice))
                {
                    playerLocation = iterateNewMap.getValue();
                    found = true;
                }
            }

            // Prints message if player input is invalid

            if(!found)
            {
                cl2.log("You cannot go in that direction");
                fl2.log("You cannot go in that direction\n");
            }
        }
    }

    public static void main(String[] args)
    {
        // Starts the game

        Mapping play = new Mapping();
        play.mapping();
    }
}
