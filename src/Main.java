import Task.Task;
import Task.TaskService;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            addTask(taskService,scanner);
                            break;
                        case 2:
                            removeTask(taskService,scanner);
                            break;
                        case 3:
                            getTaskByDay(taskService,scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void addTask(TaskService taskService,Scanner scanner) {
        System.out.println("Введите заголовок задачи: ");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String description = scanner.nextLine();

        LocalDate taskDate = null;
        boolean forceUserToAnswer=true;
        while (forceUserToAnswer){
            try {System.out.println("Введите дату задачи в формате dd.mm.yyyy: ");
                String date = scanner.nextLine();
                taskDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.mm.yyyy"));
                forceUserToAnswer=false;
            }catch (Exception e){
                System.out.println("Введите дату еще раз! ");
            }
        }
        System.out.println("Введите время задачи в формате HH:mm ");
        String time = scanner.nextLine();
        LocalTime taskTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime resultDate = LocalDateTime.of(taskDate, taskTime);
        System.out.println("Введите тип задачи: Личный(1) или Рабочий(2)");
        int type = scanner.nextInt();
        Type taskType = type == 1 ? Type.PERSONAL : Type.WORK;
        System.out.println("Введите повторяемость задачи:");
        System.out.println(" 0-не повторяется");
        System.out.println(" 1-дневная");
        System.out.println(" 2-недельная");
        System.out.println(" 3-месячная");
        System.out.println(" 4-годовая");
        int typeTask = scanner.nextInt();
        switch (typeTask) {
            case 0:
                taskService.add(new Task(name, description, taskType, resultDate));
                break;

            case 1:
                taskService.add(new Task.DayliTask(name, description, taskType, resultDate));
                break;

            case 2:
                taskService.add(new Task.WeeklyTask(name, description, taskType, resultDate));
                break;
            ;
            case 3:
                taskService.add(new Task.MonthTask(name, description, taskType, resultDate));
                break;
            case 4:
                taskService.add(new Task.YearTask(name, description, taskType, resultDate));
                break;
            default:
                throw new RuntimeException(" нет такого типа задач");
        }

    }
    private static void removeTask(TaskService taskService,Scanner scanner){
        System.out.println("Введите id задачи, которую нужно удалить");
        int id= scanner.nextInt();
                taskService.remove(id);
    }
    private static void getTaskByDay(TaskService taskService,Scanner scanner){
        System.out.println("Введите дату задачи в формате dd.mm.yyyy: ");
        String date= scanner.nextLine();
        LocalDate taskDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.mm.yyyy"));
        var allTaskByDay=taskService.getAllByDate(taskDate);
        System.out.println(" Список задач этого дня ");
        for (Task task:allTaskByDay){
            System.out.println(task);
        }


    }


    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n" +
                        "2. Удалить задачу\n" +
                        "3. Получить задачу на указанный день\n" +
                        "4. Получите все задачи\n" +
                        "5. Редактировать заголовок и описание задачи\n" +
                        "6. Получить список всех удаленных задач\n" +
                        "0. Выход\n"

        );

    }


}
