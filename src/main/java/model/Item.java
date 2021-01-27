package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 20.01.2021
 * email roman9628@gmail.com
 * The class describe model Item.
 */
@Entity
public class Item {
    /**
     * The filed contain unique id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The filed contain user description for item.
     */
    @Column(nullable = false)
    private String description;
    /**
     * The filed contain status of car is sold.
     */
    @Column(nullable = false)
    private Boolean sold;
    /**
     * The filed contain instance user owner this item.
     * Unidirectional relation.
     */
    @ManyToOne(optional = false,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private User user;

    /**
     * Use FetchType.EAGER - because method Hibernate.getAllItems() not work with
     * Lazy fetch type.
     * Unidirectional relation.
     */
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Image> images = new HashSet<>();
    /**
     * The field contain set if categories.
     * The item may have many categories.
     * Unidirectional relation.
     */
    @ManyToMany(fetch = FetchType.LAZY,
           cascade = CascadeType.ALL)
    private Set<Category> category = new HashSet<>();
    /**
     * The filed contain car instance what user sell.
     */
    @ManyToOne(optional = false,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Car car;

    /**
     * The method create instance item.
     * @param description user description for item.
     * @param isSold status of car is sold.
     * @param user user owner this item.
     * @param car car instance what user sell.
     * @return Instance car.
     */
    public static Item of(String description,
                          Boolean isSold,
                          User user,
                          Car car)
    {
        Item item = new Item();
        item.description = description;
        item.sold = isSold;
        item.user = user;
        item.car = car;
        return item;
    }

    /**
     * The method add image to set.
     * @param image Image instance.
     */
    public void addImage(Image image) {
        this.images.add(image);
    }

    /**
     * The method add category to set.
     * @param category Category instance.
     */
    public void addCategory(Category category) {
        this.category.add(category);
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
     * The getter.
     * @return sold.
     */
    public Boolean getSold() {
        return sold;
    }

    /**
     * The setter.
     * @param sold sold.
     */
    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    /**
     * The getter.
     * @return Set<Category>.
     */
    public Set<Category> getCategory() {
        return category;
    }

    /**
     * The setter.
     * @param category Set<Category> category.
     */
    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    /**
     * The getter.
     * @return user.
     */
    public User getUser() {
        return user;
    }

    /**
     * The setter.
     * @param user user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * The getter.
     * @return Set<Image>.
     */
    public Set<Image> getImages() {
        return images;
    }

    /**
     * The setter.
     * @param images Set<Image>.
     */
    public void setImages(Set<Image> images) {
        this.images = images;
    }

    /**
     * The getter.
     * @return car.
     */
    public Car getCar() {
        return car;
    }

    /**
     * The setter.
     * @param car car.
     */
    public void setCar(Car car) {
        this.car = car;
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
        Item item = (Item) o;
        return id.equals(item.id);
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
        return "Item{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", sold=" + sold +
                ", user=" + user +
                ", images=" + images +
                ", category=" + category +
                ", car=" + car +
                '}';
    }
}