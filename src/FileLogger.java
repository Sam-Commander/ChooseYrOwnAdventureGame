import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger
{
    private static final String FILE_LOGGER_NAME =  "StudentFileOutput.txt";

    // Creates file and appends message to it

    static
    {
        try {
            File fileL = new File(FILE_LOGGER_NAME);
            if(fileL.isFile()){
                fileL.delete(); // unused boolean
            }
            fileL.createNewFile(); // unused boolean
        }catch(SecurityException | IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void log (String message)
    {
        try (FileWriter fw = new FileWriter(FILE_LOGGER_NAME, true)){
            fw.write(message);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
