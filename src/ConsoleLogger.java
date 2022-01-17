public class ConsoleLogger implements Logger
{
    // Prints message to console

    @Override
    public void log(String message) {System.out.println(message);}
}
