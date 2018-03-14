public class Priority
{
    public int level;

    public int setLevel(int level)
    {
        if (level >= 0 && level <= 3)
        {
            return level;
        }
        return 0;
    }

    public Priority(int level)
    {
        this.level = setLevel(level);
    }

    @Override
    public String toString() {
        return String.valueOf(level);
    }
}
