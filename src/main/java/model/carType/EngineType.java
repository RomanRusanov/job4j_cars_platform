package model.carType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 23.01.2021
 * email roman9628@gmail.com
 * The class describe car type model.
 */
@Entity
public class EngineType {
    /**
     * The field contain id in DB.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The field contain description model.
     */
    @Column(nullable = false)
    private String description;

    /**
     * The method create model instance
     * @param desc Description.
     * @return Model instance.
     */
    public static EngineType of(String desc) {
        EngineType EngineType = new EngineType();
        EngineType.description = desc;
        return EngineType;
    }

    /**
     * The getter for id.
     * @return Long id.
     */
    public Long getId() {
        return id;
    }

    /**
     * The setter for id.
     * @param id Long id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * The getter for description.
     * @return Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * The setter for description.
     * @param desc Description.
     */
    public void setDescription(String desc) {
        this.description = desc;
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
        EngineType EngineType = (EngineType) o;
        return id.equals(EngineType.id);
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
        return "EngineType{" +
                "id=" + id +
                ", desc='" + description + '\'' +
                '}';
    }
}