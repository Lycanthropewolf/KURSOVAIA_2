package Task;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {


    public WeeklyTask(String title, String description, Type type, LocalDateTime taskDateTime) {
        super(title, description, type, taskDateTime);
    }

    @Override
    public boolean isAvailable(LocalDate inputDate) {
        var startData = getDateTime().toLocalDate();
        while (!startData.isAfter(inputDate)) {
            if (startData.equals(inputDate)) {
                return true;
            }
            startData = startData.plusWeeks(1);
        }
        return false;
    }

}
