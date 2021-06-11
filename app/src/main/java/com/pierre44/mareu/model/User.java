package com.pierre44.mareu.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by pmeignen on 10/06/2021.
 */
public class User implements Serializable {

    private long id;
    private String name;
    private String email;

    /**
     * Instantiates a new User.
     *
     * @param id    the id
     * @param name  the name
     * @param email the email
     */
    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
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
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}