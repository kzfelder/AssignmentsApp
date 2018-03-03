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

        // Add 5 weeks to today's LocalDateTime
        LocalDateTime fiveWeeksFromToday = addWeeksToDateTime(today, 5);
        System.out.println("5 weeks from today: " + dateTimeFormatter(fiveWeeksFromToday));

        // TODO Initialize a LocalDateTime object to birthdate and the time 12:35 PM

        // TODO Output the day of the week (Sunday-Saturday) born

        // TODO Output the number of days been alive

        // TODO Output the number of days between two dates

        // TODO Given two dates, output the earlier..

        // TODO Create a file with 100 random "month/day/year  hour:minutes" (in that format) on each line

        // TODO Store data from the file into an ArrayList of LocalDateTime objects

        // TODO Output the number of stored dates in the year [Y]

        // TODO Count the number of stored dates in the current year

        // TODO Count the number of duplicates

        // TODO Sort the dates in chronological order

        // TODO Count the number of duplicates in a sorted list without using a Java Set

        // TODO Count the number of evening (after 6pm) dates

        // TODO Count the number of dates in each of the individual 12 months without using a Java Map

        // TODO Count the number of dates in each of the individual 12 months using a Java Map

        // TODO Determine the index of the latest LocalDateTime

        // TODO Determine the indexes of the elements that have the earliest starting time, regardless of date

        // TODO Output a date in the format "January 1st, 2018"
    }

    private static LocalDateTime addWeeksToDateTime(LocalDateTime dateTime, int numWeeks)
    {
        return dateTime.plusWeeks(numWeeks);
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

