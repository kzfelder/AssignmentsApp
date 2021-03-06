import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.incrementExact;
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

        // Output the number of stored dates in the year [Y]
        int year = 2016;
        ArrayList<LocalDateTime> datesOfYear = getDatesInFileWithYear("hunnidRandomDates.txt", year);
        System.out.println("\nDates in " + year + ": " + datesOfYear);

        // Count the number of stored dates in the current year
        countDatesInFileWithYear("hunnidRandomDates.txt", LocalDate.now().getYear());
        //System.out.println("Dates in " + LocalDate.now().getYear() + ": " + numDatesInCurrentYear);

        // Count the number of duplicates
        countDuplicates(fileDataArrayList);

        // Sort the dates in chronological order
        sortDatesChronologically(fileDataArrayList);
        System.out.println("\nSorted Dates: " + fileDataArrayList);

        // Count the number of duplicates in a sorted list without using a Java Set
        //sortDatesChronologically(fileDataArrayList);
        countNumDuplicatesInSortedLs(fileDataArrayList);

        // Count the number of evening (after 6pm) dates
        countDatesAfterTime(fileDataArrayList, 18);

        // Count the number of dates in each of the individual 12 months without using a Java Map
        ArrayList<Integer> numDatesOfMonth = new ArrayList<>();
        ArrayList<Integer> months = monthsArrayLs();
        numDatesOfMonth.add(0);
        for (int month: months)
        {
            if (month > 0)
            {
                numDatesOfMonth.add(countDatesInMonth(month,fileDataArrayList));
            }
        }
        System.out.println("\nArrayLs: " + numDatesOfMonth);


        // Count the number of dates in each of the individual 12 months using a Java Map
        Map<Integer, Integer> mapOfDatesInMonths = new HashMap<>();
        for (int month : months)
        {
            int count = countDatesInMonth(month, fileDataArrayList);
            mapOfDatesInMonths.putIfAbsent(month, count);
            mapOfDatesInMonths.remove(0);
        }
        System.out.println("Map: " + mapOfDatesInMonths);

        // Determine the index of the latest LocalDateTime
        LocalDateTime latestDateTime = findDateTimeOfLatest(fileDataArrayList);
        int indexOfDate = getIndexOfDateTime(latestDateTime, fileDataArrayList);
        //System.out.println("\nLatest date: " + latestDateTime);
        System.out.println("\nIndex of latest: " + indexOfDate);

        // Determine the indexes of the elements that have the earliest starting time, regardless of date
        LocalDateTime earlyDateTime = findEarliestDateTime(fileDataArrayList);
        ArrayList<Integer> indexesOfEarliest = findIndexesOfDatesWithTime(earlyDateTime, fileDataArrayList);
        System.out.println("\nIndexes of Earliest Start Times: " + indexesOfEarliest);

        // Output a date in the format "January 1st, 2018"
        System.out.println(newFormatter(LocalDate.of(2018,1,1)));


        System.out.println("\n");
        // Define and use a DayOfWeek enumerated type
        Assignment.Day day = Assignment.Day.FRIDAY;
        testEnumDay(day);

        // Define and use a Course enumerated type
        Assignment.Course course = Assignment.Course.CPSC;
        testEnumCourse(course);

        // Define and use a Category enumerated type
        Assignment.Category category = Assignment.Category.PRESENTATION;
        testEnumCategory(category);

        // In the driver, generate 2 random assignments named assign1 and assign2
        Random rand= new Random();
        Assignment assign1 = new Assignment(getRandomDate(), Assignment.Course.CPSC, Assignment.Category.PRESENTATION, new Priority(rand.nextInt(4)));
        Assignment assign2 = new Assignment(getRandomDate(), Assignment.Course.MATH, Assignment.Category.HOMEWORK, new Priority(rand.nextInt(4)));
        System.out.println("\nassign1: " + assign1 + "\nassign2: " +assign2);

        // Copy assign1 to assign3
        Assignment assign3 = copyAssignToNew(assign1);
        System.out.println("\nassign3: " + assign3);

        // Override an Assignment.equals() method
        System.out.println("\nassign1.equals(assign3): " + assign1.equals(assign3) + "\nassign1.equals(assign2): " + assign1.equals(assign2));

        // Override an Assignment.compareTo() method then use it to output BEFORE, EQUALS, or AFTER based on the LocalDateTime
        System.out.println("assign1 compared to assign2: " + assign1.compareTo(assign2) + "\nassign1 compared to assign3: " + assign1.compareTo(assign3));

        // Which of assign1, assign2, or assign3 is the earliest?
        ArrayList<Assignment> assigns = new ArrayList<>();
        assigns.add(assign1);
        assigns.add(assign2);
        assigns.add(assign3);
        System.out.println("Earliest Assignment: " + findEarliestAssign(assigns));

    }

    private static Assignment findEarliestAssign(ArrayList<Assignment> assignLs)
    {
        Assignment earliest = assignLs.get(0);
        for ( Assignment assign : assignLs)
        {
            if (earliest.compareTo(assign) == "BEFORE")
            {
                earliest = assign;
            }
        }
        return earliest;
    }

    private static Assignment copyAssignToNew(Assignment assign1)
    {
        Assignment newAssign = new Assignment(assign1.getDateTime(), assign1.getCourse(), assign1.getCategory(), assign1.getLevel());
        return newAssign;
    }

    private static void testEnumCategory(Assignment.Category category)
    {
        if (category.equals(Assignment.Category.FINAL_EXAM))
        {
            System.out.println(category + ": Rest up, don't stress!");
        }
        else if (category.equals(Assignment.Category.HOMEWORK))
        {
            System.out.println(category + ": Make sure you complete homework early so you can ask your professor about any questions you have!");
        }
        else if (category.equals(Assignment.Category.PRESENTATION))
        {
            System.out.println(category + ": Be sure to review and revise your slides.");
        }
        else if (category.equals(Assignment.Category.TEST))
        {
            System.out.println(category + ": Study your sections and homework, do your best!");
        }
        else if (category.equals(Assignment.Category.QUIZ))
        {
            System.out.println(category + ": Don't be fooled by the size, every point counts!");
        }
    }

    private static void testEnumCourse(Assignment.Course course)
    {
        if (course.equals(Assignment.Course.CPSC))
        {
            System.out.println(course + ": 3 hours");
        }
        else if (course.equals(Assignment.Course.ENGL))
        {
            System.out.println(course + ": 3 hours");
        }
        else if (course.equals(Assignment.Course.ENGR))
        {
            System.out.println(course + ": 2 hours");
        }
        else if (course.equals(Assignment.Course.FRSM))
        {
            System.out.println(course + ": 1 hour");
        }
        else if (course.equals(Assignment.Course.MATH))
        {
            System.out.println(course + ": 3 hours");
        }
        else if (course.equals(Assignment.Course.SPAN))
        {
            System.out.println(course + ": 3 hours");
        }
    }

    private static void testEnumDay(Assignment.Day day)
    {
        for (Assignment.Day days : Assignment.Day.values())
        {
            if (days.equals(day))
            {
                System.out.println("Day entered was: " + day);
            }
        }
    }

    private static String getCorrectSuffix(LocalDate date)
    {
        ArrayList<String> suffixes = new ArrayList<>();

        suffixes.add("st");
        suffixes.add("nd");
        suffixes.add("rd");
        suffixes.add("th");

        if (date.getDayOfMonth() <= 20 && date.getDayOfMonth() >= 10)
        {
            return suffixes.get(3);
        }
        else
        {
            int lastDigit = date.getDayOfMonth()%10;
            if (lastDigit == 1)
            {
                return suffixes.get(0);
            }
            else if (lastDigit == 2)
            {
                return suffixes.get(1);
            }
            else if (lastDigit == 3)
            {
                return suffixes.get(2);
            }
        }
        return suffixes.get(3);
    }

    private static String newFormatter(LocalDate date)
    {
        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MMMM");
        String month = date.format(monthFormat);

        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("d");
        String day = date.format(dayFormat);

        int year = date.getYear();

        String format = "";
        String suffix = getCorrectSuffix(date);
        format = month + " " + day + suffix + ", " + year;

        return format;
    }

    private static ArrayList<Integer> findIndexesOfDatesWithTime(LocalDateTime dateTime, ArrayList<LocalDateTime> dateList)
    {
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < dateList.size(); i++)
        {
            if (dateList.get(i).getHour() == hour)
            {
                if (dateList.get(i).getMinute() == minute)
                {
                    indexes.add(i);
                }
            }
        }

        return indexes;
    }

    private static LocalDateTime findEarliestDateTime(ArrayList<LocalDateTime> dateList)
    {
        LocalDateTime earliestDate = dateList.get(0);
        for (int i = 1; i < dateList.size(); i++)
        {
            if (dateList.get(i).getHour() < earliestDate.getHour())
            {
                if (dateList.get(i).getMinute() < earliestDate.getMinute())
                {
                    earliestDate = dateList.get(i);
                }
            }
        }

        return earliestDate;
    }

    private static int getIndexOfDateTime(LocalDateTime dateTime, ArrayList<LocalDateTime> dateList)
    {
        int index = 0;
        for (int i = 0; i < dateList.size(); i++)
        {
            if (dateList.get(i).equals(dateTime))
            {
                index = i;
            }
        }
        return index;
    }

    private static LocalDateTime findDateTimeOfLatest(ArrayList<LocalDateTime> dateList)
    {
        LocalDateTime latest = dateList.get(0);
        for (int i = 1; i < dateList.size(); i++)
        {
            if (dateList.get(i).isAfter(latest))
            {
                latest = dateList.get(i);
            }
        }

        return latest;
    }

    private static int countNumDuplicatesInSortedLs(ArrayList<LocalDateTime> dateList)
    {
        int count = 0;
        for (int i = 0; i < dateList.size(); i++)
        {
            if (dateList.get(i).equals(dateList.get(i + 1)))
            {
                count++;
            }
            else
            {
                break;
            }
        }

        return count;
    }

    private static int countDatesInMonth(int month, ArrayList<LocalDateTime> dateList)
    {
        int count = 0;
        for (int i = 0; i < dateList.size(); i++)
        {
            if (dateList.get(i).getMonthValue() == month)
            {
                count++;
            }

        }
        return count;
    }

    private static ArrayList<Integer> monthsArrayLs()
    {
        ArrayList<Integer> months = new ArrayList<>();
        for (int i = 0; i < 13; i++)
        {
            months.add(i);
        }
        return months;
    }

    private static int countDatesAfterTime(ArrayList<LocalDateTime> dateList, int time)
    {
        int count = 0;

        for (int i = 0; i < dateList.size(); i++)
        {
            if (dateList.get(i).getHour() == time)
            {
                if (dateList.get(i).getMinute() > 0)
                {
                    count++;
                }
            }
            else if (dateList.get(i).getHour() > time)
            {
                count++;
            }
        }

        return count;
    }

    private static void sortDatesChronologically(ArrayList<LocalDateTime> dateList)
    {
        for (int i = 0; i < dateList.size(); i++)
        {
            for (int j = 0; j < dateList.size(); j++)
            {
                if (dateList.get(j).isAfter(dateList.get(i)))
                {
                    LocalDateTime temp = dateList.get(j);
                    dateList.set(j, dateList.get(i));
                    dateList.set(i, temp);
                }
            }
        }
    }

    private static int countDuplicates(ArrayList<LocalDateTime> dates)
    {
        int count = 0;

        Set<LocalDateTime> dateSet = new HashSet<>();
        for (int i = 0; i < dates.size(); i++)
        {
            if(dateSet.contains(dates.get(i)))
            {
                count++;
            }
            else
            {
                dateSet.add(dates.get(i));
            }
        }

        return count;
    }

    private static int countDatesInFileWithYear(String fileName, int year)
    {
        int count = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy HH:mm");
        File infile = new File(fileName);

        try(Scanner sc = new Scanner(infile))
        {
            while(sc.hasNext())
            {
                LocalDateTime date = LocalDateTime.parse(sc.nextLine(),formatter);
                if(date.getYear() == year)
                {
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return count;
    }

    private static ArrayList<LocalDateTime> getDatesInFileWithYear(String fileName, int year)
    {
        ArrayList<LocalDateTime> datesWithYear = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy HH:mm");
        File infile = new File(fileName);

        try(Scanner sc = new Scanner(infile))
        {
            while(sc.hasNext())
            {
                LocalDateTime date = LocalDateTime.parse(sc.nextLine(),formatter);
                if(date.getYear() == year)
                {
                    datesWithYear.add(date);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return datesWithYear;
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

