package ru.job4j.executor.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        pool.submit(() -> {
            String userName = user.getUserName();
            String subject = String.format("Notification %s to email %s.", userName, user.getEmail());
            String body = String.format("Add a new event to %s.", userName);
            send(subject, body, userName);
        });
    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {

    }

    private class User {
        private String userName;
        private String email;

        public User(String userName, String email) {
            this.userName = userName;
            this.email = email;
        }

        public String getUserName() {
            String userName = this.userName;
            return userName;
        }

        public String getEmail() {
            String email = this.email;
            return email;
        }

        @Override
        public String toString() {
            return "User{"
                    + "userName='" + userName + '\''
                    + ", email='" + email + '\''
                    + '}';
        }
    }
}
