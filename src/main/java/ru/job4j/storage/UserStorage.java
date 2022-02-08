package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean rsl = false;
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            rsl = true;
        }
        return rsl;
    }

    public synchronized boolean update(User user) {
        return true;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User sender = users.get(fromId);
        User receiver = users.get(toId);
        if (sender != null && receiver != null && users.get(fromId).getAmount() >= amount) {
            delete(sender);
            delete(receiver);
            add(new User(fromId, sender.getAmount() - amount));
            add(new User(toId, receiver.getAmount() + amount));

        }
    }

    public synchronized Map<Integer, User> getUsers() {
        return new HashMap<>(users);
    }

    public static void main(String[] args) {
        UserStorage stoge = new UserStorage();

        stoge.add(new User(1, 100));
        stoge.add(new User(2, 200));

        stoge.transfer(1, 2, 50);
        System.out.println(stoge.getUsers().values());
    }
}
