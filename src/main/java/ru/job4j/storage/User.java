package ru.job4j.storage;

import java.util.Iterator;
import java.util.Objects;

public class User implements Iterable {

    private final int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        int idCopy = id;
        return idCopy;
    }

    public int getAmount() {
        int amountCopy = amount;
        return amountCopy;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && amount == user.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", amount=" + amount
                + '}';
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}