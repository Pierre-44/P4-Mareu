package com.pierre44.mareu.model;

/**
 * Created by pmeignen on 18/05/2021.
 */
public class User {

    private long id;
    private String name;
    private String email;

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

    /**
     * User.
     *
     * @param id    the id
     * @param name  the name
     * @param email the email
     */
    public void user (long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;

    }

}
