package com.netcracker.java.YuliaShevchenko.lab1.model;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Class ArrayTaskList.
 * Realization of the task list.
 */

public class ArrayTaskList extends TaskList implements Cloneable, Serializable {

    /**
     * Version control for serialization.
     */

    static final long serialVersionUID = 42L;

    /**
     * Length of current array.
     */

    private int n;

    /**
     * Count tasks.
     */

    private int size;

    /**
     *
     */

    private Task[] array;

    /**
     * Constructor ArrayTaskList().
     * Fills the main variable.
     */
    
    public ArrayTaskList() {
        this.n = Constants.getNulls();
        this.size = Constants.getNulls();
        this.array = new Task[Constants.getStartSize()];
    }
    
    /**
     * Method add(Task task).
     * Add task in the list.
     * @param task current task for add.
     */

    public final void add(final Task task) {
        if (task == null) {
            return;
        }
        if (this.n == Constants.getNulls()) {
            this.array = new Task[Constants.getStartSize()];
            this.getArray()[this.size] = task;
            this.n = Constants.getStartSize();
            this.size++;
        } else {
            if (this.size == this.n) {
                this.n += this.n / 2;
                Task[] temp = new Task[this.n];
                System.arraycopy(this.getArray(), Constants.getNulls(), temp, Constants.getNulls(),
                        this.n - (Constants.getStartSize() / 2));
                this.array = temp;
            }
            this.getArray()[this.size] = task;
            this.size++;
        }
    }

    /**
     * Method size().
     * Return count tasks in Task List.
     * @return count tasks.
     */

    public final int size() {
        return this.size;
    }

    /**
     * Method remove(Task task).
     * Delete task from the list.
     * @param task current task.
     * @return true, if task removed, or false.
     */
    
    public final boolean remove(final Task task) {
        int i = 0;
        int res = -1;
        while (i < this.size) {
            if (this.getArray()[i] == task) {
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
            this.getArray()[i] = this.getArray()[i + k];
        }
        this.getArray()[this.size - 1] = null;
        this.size--;
        return true;
    }

    /**
     * Method iterator().
     * Realize iterator.
     * Specifies the basic functions to be iterated in the list.
     * @return iterator.
     */
    
    @Override
    public final Iterator<Task> iterator() {
        return new Iterator<Task>() {
            int current = 0;
            public boolean hasNext() { return current < size; }
            public Task next() { return array[current++]; }
            public void remove() {
                if(current == 0) throw new IllegalStateException();
                int k = 0;
                for (int i = 0; i < size; i++){
                    if(i == current-1) k++;
                    array[i]=array[i+k];
                }
                array[size]=null;
                size--;
                current --;
            }
        };
    }

    /**
     * Method hashCode().
     * use for hashing date: {@link Object#hashCode()}.
     * @return hashcode of the current task.
     */

    @Override
    public final int hashCode() {
        int result = Constants.getNulls();
        for (Task t : this) {
            int res = t.hashCode();
            result = Constants.getTen() * result + res;
        }
        return result;
    }

    /**
     * Method equals(Object obj).
     * equals two tasks: {@link Object#equals(Object)}.
     * @param obj the object of comparison .
     * @return true, if objects is identical, or false.
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
     * Method clone().
     * create copy of this ArrayTaskList: {@link Object#clone()}.
     * @return  copy of this ArrayTaskList.
     * @throws  CloneNotSupportedException if object can not be cloned.
     */

    @Override
    protected final TaskList clone() throws CloneNotSupportedException {
        return (TaskList) super.clone();
    }

    /**
     * An array that stores tasks.
     */
    public final Task[] getArray() {
        return this.array;
    }
}