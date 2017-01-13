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

    @Override
    public boolean equals(Object taskList) {
        if(taskList == null) return false;
        else if(taskList == this) return true;
        else if(this.getClass() != taskList.getClass()) return false;
        else{
            TaskList tasks = (TaskList)taskList;
            if(tasks.size() != size()) return false;
            Iterator<Task> i = this.iterator();
            for(Task t:tasks){
                Task t1 = i.next();
                boolean a = t.equals(t1);
                if(!a) return false;
            }
            return true;
        }
    }

    public abstract String toString();

    @Override
    protected TaskList clone() throws CloneNotSupportedException {
        return (TaskList)super.clone();
    }
}