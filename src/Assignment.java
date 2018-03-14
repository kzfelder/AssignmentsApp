import java.time.LocalDateTime;

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
