package ua.nure.bekuzarov.SummaryTask4.entity;

import ua.nure.bekuzarov.SummaryTask4.annotation.Column;

/**
 * User class.
 *
 * @author Dmitry Bekuzarov
 */
public class User extends Entity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private int role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets user role.
     *
     * @return user role
     */
    public UserRole getRole() {
        return UserRole.define(role);
    }

    /**
     * Sets user role.
     *
     * @param role user role to set
     */
    public void setRole(UserRole role) {
        this.role = role.ordinal();
    }
}
