package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Класс CollectionsTasks.
 * Используется для создание обертки
 * TaskList и необходим для отображения
 * данных в tableView.
 */

public class CollectionsTasks {

    /**
     * Класс-обертка TaskList-а.
     */

    private ObservableList<Task> obs;

    /**
     * Список задач.
     */

    private TaskList tasks;

    /**
     * Конструктор CollectionsTasks().
     * Создает новый список задач и
     * класс-обертку для него.
     */

    public CollectionsTasks() {
        this.obs = FXCollections.observableArrayList();
        this.tasks = new ArrayTaskList();
    }

    /**
     * Метод add(Task t).
     * Добавление новой залачи.
     * @param t задача.
     */

    public final void add(final Task t) {
        this.obs.add(t);
        this.tasks.add(t);
    }

    /**
     * Метод remove(Task t).
     * Удаление задачи.
     * @param t задача.
     */

    public final void remove(final Task t) {
        this.obs.remove(t);
        this.tasks.remove(t);
    }

    /**
     *Метод getObs().
     * Возвращает ObservableList
     * текущего списка задач.
     * @return класс-обертку списка.
     */

    public final ObservableList<Task> getObs() {
        return this.obs;
    }

    /**
     * Метод getTasks().
     * Возвращает список задач.
     * @return список задач.
     */

    public final TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Метод setTasks(TaskList tasks).
     * Устанавливает список задач и
     * делает для них обертку.
     * @param taskss список задач.
     */

    public final void setTasks(final TaskList taskss) {
        this.tasks = taskss;
        for (Task t : this.tasks) {
            this.obs.add(t);
        }
    }
}
