package ru.kuzmina.whiskersshop.listeners;

public interface Observer {
    void update(ProductSubject subject, Object args);
}
