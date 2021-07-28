package com.pierre44.mareu.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private long userId;
    private String name;
    private String email;

    /**
     * Instantiates a new User.
     *
     * @param userId the user id
     * @param name   the name
     * @param email  the email
     */
    public User(long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return userId;
    }


    /**
     * Sets id.
     *
     * @param userId the user id
     */
    public void setId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}