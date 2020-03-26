package ru.denisch.department;

public interface Observable {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

}
