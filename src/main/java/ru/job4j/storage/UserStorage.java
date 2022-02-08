package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> users = new ArrayList<>();

    public synchronized boolean add(User user) {
        return users.add(user);
    }

    public synchronized boolean update(User user) {
        return true;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        for (User sender : users) {
            if (sender.getId() == fromId && sender.getAmount() >= amount) {
                for (User receiver : users) {
                    if (receiver.getId() == toId) {
                        add(new User(fromId, sender.getAmount() - amount));
                        add(new User(toId, receiver.getAmount() + amount));
                        delete(sender);
                        delete(receiver);
                        break;
                    }
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        UserStorage stoge = new UserStorage();

        stoge.add(new User(1, 100));
        stoge.add(new User(2, 200));

        stoge.transfer(1, 2, 50);
        stoge.users.forEach(System.out::println);
    }
}
