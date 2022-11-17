package Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Repeatable {
    boolean isAvailable( LocalDate inputDate );
}
