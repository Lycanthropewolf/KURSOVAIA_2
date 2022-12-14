package Task;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task implements Repeatable{
    private int id;
    private String title;
    private String description;
    private Type type;
    private static int idGenerator=0;
    private LocalDateTime dateTime;

    public Task(String title, String description, Type type, LocalDateTime taskDateTime) {
        this.id = idGenerator++;
        this.title = title;
        this.description = description;
        this.type = type;
        this.dateTime=taskDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }



    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public boolean isAvailable(LocalDate inputDate) {
        return inputDate.isEqual(getDateTime().toLocalDate());

    }

    public static class MonthTask extends Task {
        public MonthTask(String name, String description, Type taskType, LocalDateTime resultDate) {
            super(name,description,taskType,resultDate);
        }
    }

    public static class WeeklyTask extends Task {
        public WeeklyTask(String name, String description, Type taskType, LocalDateTime resultDate) {
            super(name,description,taskType,resultDate);
        }
    }

    public static class DayliTask extends Task {
        public DayliTask(String name, String description, Type taskType, LocalDateTime resultDate) {
            super(name,description,taskType,resultDate);
        }
    }

    public static class YearTask extends Task {
        public YearTask(String name, String description, Type taskType, LocalDateTime resultDate) {
            super(name,description,taskType,resultDate);
        }
    }

    @Override
    public String toString() {
        return "???????????? {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", dateTime=" + dateTime +
                '}';
    }
}
