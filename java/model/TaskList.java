package model;

import java.io.Serializable;
import java.util.Iterator;

public abstract class TaskList implements Iterable<Task>, Serializable {

    static final long serialVersionUID = 42L;

    public abstract void add(Task task);

    public abstract int size();

    public abstract Iterator<Task> iterator();

    public abstract boolean remove(Task t);

    public abstract int hashCode();

}