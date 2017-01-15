package model;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Класс ArrayTaskList.
 * Реализация списка задач.
 */

public class ArrayTaskList extends TaskList implements Cloneable, Serializable {

    /**
     * Контроль версии для сериализации.
     */

    static final long serialVersionUID = 42L;

    /**
     * Длинна списка.
     */

    private int n;

    /**
     * Начальная длинна списка.
     */

    private final int startSize = 10;

    /**
     * Ноль.
     */

    private final int nulls = 0;

    /**
     * Количество внесенных задач.
     */

    private int size;

    /**
     * Массив, в который вносят задачи.
     */

    private Task[] array;

    /**
     * Конструктор ArrayTaskList().
     * Заполняет основные переменные.
     */
    
    public ArrayTaskList() {
        this.n = 0;
        this.size = 0;
        this.array = new Task[0];
    }
    
    /**
     * Метод add(Task task).
     * Добавление задачи.
     * @param task задача.
     */

    public final void add(final Task task) {
        if (task == null) {
            return;
        }
        if (this.n == 0) {
            this.array = new Task[this.startSize];
            this.array[this.size] = task;
            this.n = this.startSize;
            this.size++;
        } else {
            if (this.size == this.n) {
                this.n += this.n / 2;
                Task[] temp = new Task[this.n];
                System.arraycopy(this.array, 0, temp, 0,
                        this.n - (this.startSize / 2));
                this.array = temp;
            }
            this.array[this.size] = task;
            this.size++;
        }
    }

    /**
     * Метод size().
     * Возвращает количесво задач.
     * @return количество задач.
     */

    public final int size() {
        return this.size;
    }

    /**
     * Метод remove(Task task).
     * Удаление задачи.
     * @param task задача.
     * @return true, если удалено.
     */
    
    public final boolean remove(final Task task) {
        int i = 0;
        int res = -1;
        while (i < this.size) {
            if (this.array[i] == task) {
                res = i;
                break;
            }
            i++;
        }
        if (res == -1) {
            return false;
        }
        int k = 0;
        for (i = 0; i < this.size - 1; i++) {
            if (i == res) {
                k++;
            }
            this.array[i] = this.array[i + k];
        }
        this.array[this.size - 1] = null;
        this.size--;
        return true;
    }

    /**
     * Метод iterator().
     * Реализация итератора.
     * Задает основные функции, для
     * интерирования по списку.
     * @return итератор.
     */
    
    @Override
    public final Iterator<Task> iterator() {
        Task[] tasks = this.array;
        return new Iterator<Task>() {

            private int current = nulls;

            public boolean hasNext() {
                return this.current < tasks.length;
            }

            public Task next() {
                return tasks[this.current++];
            }

            public void remove() {
                if(this.current == 0) {
                    throw new IllegalStateException();
                }
                int k = 0;
                for (int i = 0; i < tasks.length; i++) {
                    if (i == this.current - 1) {
                        k++;
                    }
                    tasks[i] = tasks[i + k];
                }
                tasks[tasks.length] = null;
                size--;
                this.current--;
            }
        };
    }

    /**
     * Метод hashCode().
     * используется для хеширования данных:
     * {@link Object#hashCode()}.
     * @return хеш-код текущей задачи.
     */

    @Override
    public final int hashCode() {
        final int prime = 10;
        int result = 1;
        for (Task t : this) {
            int res = t.hashCode();
            result = prime * result + res;
        }
        return result;
    }

    /**
     * Метод equals(Object obj).
     * сравнивает задачи: {@link Object#equals(Object)}.
     * @param obj обьект для проверки.
     * @return true, если обьекты одинаковые; false.
     */
    
    @Override
    public final boolean equals(final Object obj) {
        if (obj == null)  {
            return false;
        } else if (obj == this) {
            return true;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            TaskList tasks = (TaskList) obj;
            if (tasks.size() != this.size()) {
                return false;
            }
            Iterator<Task> i = this.iterator();
            for (Task t : tasks) {
                Task t1 = i.next();
                boolean a = t.equals(t1);
                if (!a) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Метод clone().
     * создает копию текущей задачи
     * {@link Object#clone()}.
     * @return  копию текущей задачи.
     * @throws  CloneNotSupportedException no clone.
     */

    @Override
    protected final TaskList clone() throws CloneNotSupportedException {
        return (TaskList) super.clone();
    }
}