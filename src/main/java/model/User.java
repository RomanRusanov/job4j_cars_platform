package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 20.01.2021
 * email roman9628@gmail.com
 * The class describe model User.
 * user - reserved sql literal, table name changed to - users.
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * The filed contain unique id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The user name.
     */
    @Column(nullable = false)
    private String name;
    /**
     * The password of user.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The method create instance of user.
     * @param name name.
     * @param password password of user.
     * @return instance of user.
     */
    public static User of(String name, String password) {
        User user = new User();
        user.name = name;
        user.password = password;
        return user;
    }

    /**
     * The getter.
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * The setter.
     * @param id id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * The getter.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * The setter.
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The getter.
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * The setter.
     * @param password password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The method override equals.
     * @param o Object to compare.
     * @return if fields equals return true, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    /**
     * The method override hashcode.
     * @return generate hash using fields.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * The override toString method.
     * @return String.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}