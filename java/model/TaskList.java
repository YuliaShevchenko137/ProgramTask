package model;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Класс TaskList.
 * Реализация списка задач.
 */

public abstract class TaskList implements Iterable<Task>, Serializable {

    /**
     * контроль версий для сериализации {@value}.
     */

    static final long serialVersionUID = 42L;

    /**
     * Метод add(Task task).
     * Добавление задач.
     * @param task задача.
     */

    public abstract void add(Task task);

    /**
     * Метод size().
     * @return количество задач в списке.
     */

    public abstract int size();

    /**
     * Метод iterator().
     * Реализация итератора.
     * Задает основные функции, для возможности
     * интерирования по списку.
     * @return итератор.
     */

    public abstract Iterator<Task> iterator();

    /**
     * Метод remove(Task t).
     * Удаление элемента.
     * @param t задача.
     * @return true, если элемент удален.
     */

    public abstract boolean remove(Task t);

}