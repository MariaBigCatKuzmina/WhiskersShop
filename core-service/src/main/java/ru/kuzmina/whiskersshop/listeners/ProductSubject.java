package ru.kuzmina.whiskersshop.listeners;

import java.util.ArrayList;
import java.util.List;

public abstract class ProductSubject {

    private List<Observer> observers = new ArrayList<>();

    public ProductSubject() {
        observers = new ArrayList<>();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notify(Object args) {
        for (Observer observer : observers) {
            observer.update(this, args);
        }
    }
}
