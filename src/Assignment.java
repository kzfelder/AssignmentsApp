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
}
