package model;

import java.util.ArrayList;


public class Observable {

    private final ArrayList<Observer>  observers = new ArrayList<>();

    public void add(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){
        observers.remove(o);
    }

    public void notifyObservers(Object obj){
        for (Observer o: observers){
            o.update(obj);
        }
    }
}
