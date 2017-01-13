package model;


import java.io.Serializable;
import java.util.Iterator;

public class ArrayTaskList extends TaskList implements Cloneable, Serializable{

    static final long serialVersionUID = 42L;

    private int n=0;
    private int size = 0;

    private Task[] Array = new Task[1];

    public void add(Task task) {
        if (task == null){
            return;
        }
        if (this.n == 0){
            this.Array = new Task[10];
            this.Array[this.size] = task;
            this.n=10;
            this.size++;
        }
        else{
            if (this.size == this.n){
                this.n+=5;
                Task[]A1=new Task[n];
                System.arraycopy(this.Array, 0, A1, 0, n-5);
                this.Array = A1;
            }
            this.Array[this.size]=task;
            this.size++;
        }
    }

    public int size(){
        return size;
    }

    public boolean remove(Task task){
        int i=0;
        int res = -1;
        while(i < size){
            if(Array[i]==task){
                res = i;
                break;
            }
            i++;
        }
        if(res == -1){
            return false;
        }
        int k = 0;
        for (i = 0; i < size-1; i++){
            if(i == res) k++;
            Array[i]=Array[i+k];
        }
        Array[size-1]=null;
        size--;
        return true;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            int current = 0;
            public boolean hasNext() { return current < size; }
            public Task next() { return Array[current++]; }
            public void remove() {
                if(current == 0) throw new IllegalStateException();
                int k = 0;
                for (int i = 0; i < size; i++){
                    if(i == current-1) k++;
                    Array[i]=Array[i+k];
                }
                Array[size]=null;
                size--;
                current --;
            }
        };
    }

    @Override
    public int hashCode() {
        final int prime = 10;
        int result = 1;
        for(Task t:this){
            int res = t.hashCode();
            result = prime * result + res;
        }
        return result;
    }

    @Override
    public boolean equals(Object Tasks) {
        if(Tasks == null) return false;
        else if(Tasks == this) return true;
        else if(this.getClass() != Tasks.getClass()) return false;
        else{
            TaskList tasks = (TaskList)Tasks;
            if(tasks.size() != size()) return false;
            Iterator<Task> i = iterator();
            for(Task t:tasks){
                Task t1 = i.next();
                boolean a = t.equals(t1);
                if(!a) return false;
            }
            return true;
        }
    }

    @Override
    public String toString() {
        String s = "ArrayTaskListTaskList, size = "+ size() + ":\n";
        for(Task t:this){
            s+="Title: " + t.getStart() + " Start Time: " + t.getStart() + " End Time: " + t.getEnd() + " Interval: " + t.getInterval() + " Active: " + t.isActive();
            s+="\n";
        }
        return s;
    }

    @Override
    protected TaskList clone() throws CloneNotSupportedException {
        return super.clone();
    }
}