
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.time.temporal.ChronoUnit.DAYS;

public class Main
{

    public static void main(String[] args) throws IOException {
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

        // Initialize a LocalDateTime object to birthdate and the time 12:35 PM
        LocalDateTime birthDate = LocalDateTime.of(1998,12,23,12,35);

        // Output the day of the week (Sunday-Saturday) born
        DayOfWeek dayOfWeekBorn = birthDate.getDayOfWeek();
        System.out.println("\nDay of week born: " + dayOfWeekBorn);

        // Output the number of days been alive
        long daysAlive = getRangeInDays(today,birthDate);
        System.out.println("Days alive: " + daysAlive);

        // Output the number of days between two dates
        LocalDateTime randomDate = getRandomDate();
        System.out.println("\nRandom date: " + randomDate);
        long numDaysBetweenDates = getRangeInDays(randomDate, tomorrow);
        System.out.println("Number of days between " + randomDate + " and " + tomorrow + ": " + numDaysBetweenDates);

        // Given two dates, output the earlier..
        LocalDateTime earlierDate = getEarlierDate(randomDate,tomorrow);
        if(earlierDate.equals(randomDate) || earlierDate.equals(tomorrow))
        {
            System.out.println("Earlier date: " + earlierDate);
        }
        else
        {
            System.out.println("These are the same dates.");
        }

        // Create a file with 100 random "month/day/year  hour:minutes" (in that format) on each line
        createFileWithNumDates("hunnidRandomDates.txt", 100);

        // Store data from the file into an ArrayList of LocalDateTime objects
        ArrayList<LocalDateTime> fileDataArrayList = storeFileDataToArrayList("hunnidRandomDates.txt");

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

    private static ArrayList<LocalDateTime> storeFileDataToArrayList(String fileName)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy HH:mm");
        ArrayList<LocalDateTime> fileData = new ArrayList<>();
        File infile = new File(fileName);
        try(Scanner sc = new Scanner(infile))
        {
            while(sc.hasNext())
            {
                LocalDateTime data = LocalDateTime.parse(sc.nextLine(),formatter);
                fileData.add(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileData;
    }

    private static void createFileWithNumDates(String fileName, int numDates)
    {
        File outfile = new File(fileName);
        try(PrintWriter pw = new PrintWriter(outfile))
        {
            for (int i = 0; i < numDates; i++)
            {
                LocalDateTime randomDate = getRandomDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy HH:mm");
                pw.println(randomDate.format(formatter));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static LocalDateTime getEarlierDate(LocalDateTime dateTimeOne, LocalDateTime dateTimeTwo)
    {
        if (dateTimeOne.isBefore(dateTimeTwo))
        {
            return dateTimeOne;
        }
        return dateTimeTwo;
    }

    private static LocalDateTime getRandomDate()
    {
        Random rand = new Random();
        return LocalDateTime.now().plusSeconds(rand.nextInt());
    }

    private static long getRangeInDays(LocalDateTime dateTimeOne, LocalDateTime dateTimeTwo)
    {
        return abs(dateTimeOne.until(dateTimeTwo, DAYS));
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

