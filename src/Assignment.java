import java.time.LocalDateTime;

public class Assignment
{
    public enum Course
    {
        MATH,CPSC, ENGR, ENGL, SPAN, FRSM
    }

    public enum Category
    {
        HOMEWORK, QUIZ, TEST, PRESENTATION, FINAL_EXAM
    }

    public enum Day
    {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    }
    private LocalDateTime dateTime;
    private Course course;
    private Category category;
    private Priority level;

    public Assignment(LocalDateTime dateTime, Course course, Category category, Priority level)
    {
        this.dateTime = dateTime;
        this.course = course;
        this.category = category;
        this.level = level;
    }

    // Override an Assignment.equals() method
    @Override
    public boolean equals(Object rhs)
    {
        Assignment temp = (Assignment) rhs;
        if (getDateTime().equals(temp.getDateTime())
                && getCourse().equals(temp.getCourse())
                && getCategory().equals(temp.getCategory())
                && getLevel().equals(temp.getLevel()))
        {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "dateTime=" + dateTime +
                ", course=" + course +
                ", category=" + category +
                ", level=" + level +
                '}';
    }

    public String compareTo(Assignment rhs)
    {
        if (getDateTime().isBefore(rhs.getDateTime()))
        {
            return "BEFORE";
        }
        if (getDateTime().isAfter(rhs.getDateTime()))
        {
            return "AFTER";
        }
        return "EQUALS";
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
    }

    public Course getCourse()
    {
        return course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Priority getLevel()
    {
        return level;
    }

    public void setLevel(Priority level)
    {
        this.level = level;
    }
}
