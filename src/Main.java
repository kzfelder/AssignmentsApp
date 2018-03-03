import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main
{

    public static void main(String[] args)
    {
        System.out.println("\n\nHello, AssignmentsApp!\n");

        //Output the current date-time
        LocalDateTime today = LocalDateTime.now();
        System.out.println("Current date-time: " + today);

        // Output tomorrow's date using a formatter
        LocalDateTime tomorrow = addDaysToDateTime(today,1);
        String formattedTomorrow = dateTimeFormatter(tomorrow);
        System.out.println("Tomorrow: " + formattedTomorrow);
    }

    private static LocalDateTime addDaysToDateTime(LocalDateTime dateTime, int numDays)
    {
        return dateTime.plusDays(numDays);
    }

    private static String dateTimeFormatter(LocalDateTime dateTime)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}

