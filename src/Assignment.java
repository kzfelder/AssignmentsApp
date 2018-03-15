import java.time.LocalDateTime;
import java.util.Objects;

public class Assignment
{
    private LocalDateTime dateTime;
    private Main.Course course;
    private Main.Category category;
    private Priority level;

    public Assignment(LocalDateTime dateTime, Main.Course course, Main.Category category, Priority level)
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

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
    }

    public Main.Course getCourse()
    {
        return course;
    }

    public void setCourse(Main.Course course)
    {
        this.course = course;
    }

    public Main.Category getCategory()
    {
        return category;
    }

    public void setCategory(Main.Category category)
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
