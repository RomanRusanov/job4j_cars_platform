package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 21.01.2021
 * email roman9628@gmail.com
 * The class describe category of item. Item may have many categories.
 */
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The name of category.
     */
    private String description;

    /**
     * The method create instance of category.
     * @param description name.
     * @return Instance of category.
     */
    public static Category of(String description) {
        Category category = new Category();
        category.description = description;
        return category;
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
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * The setter.
     * @param description description.
     */
    public void setDescription(String description) {
        this.description = description;
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
        Category category = (Category) o;
        return id.equals(category.id) && description.equals(category.description);
    }

    /**
     * The method override hashcode.
     * @return generate hash using fields.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    /**
     * The override toString method.
     * @return String.
     */
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}