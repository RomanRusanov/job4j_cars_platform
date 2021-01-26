package model;

import model.carType.BodyType;
import model.carType.Brand;
import model.carType.EngineType;
import model.carType.Transmission;
import model.carType.WheelDriveType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 20.01.2021
 * email roman9628@gmail.com
 * The class describe model car.
 */
@Entity
public class Car {
    /**
     * The filed contain unique id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The brand of car.
     */
    @ManyToOne(optional = false,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Brand brand;
    /**
     * The filed contain model name.
     */
    private String model;
    /**
     * The car price.
     */
    private Integer price;
    /**
     * The date of car production.
     */
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    /**
     * The transmission type.
     */
    @ManyToOne(optional = false,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Transmission transmission;
    /**
     * The body type of car.
     */
    @ManyToOne(optional = false,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private BodyType bodyType;
    /**
     * The engine type of car.
     */
    @ManyToOne(optional = false,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private EngineType engineType;
    /**
     * The car mileage.
     */
    private Integer mileage;
    /**
     * The wheel drive type od car.
     */
    @ManyToOne(optional = false,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private WheelDriveType wheelDriveType;

    /**
     * The method create instance of car.
     * @param brand Brand of car.
     * @param model Model of car.
     * @param price Car price.
     * @param creationDate Date of car production.
     * @param transmission Transmission type.
     * @param bodyType Body type of car.
     * @param engineType Engine type of car.
     * @param mileage Car mileage.
     * @param wheelDriveType Wheel drive type od car.
     * @return Instance of car class.
     */
    public static Car of(Brand brand,
                         String model,
                         Integer price,
                         Date creationDate,
                         Transmission transmission,
                         BodyType bodyType,
                         EngineType engineType,
                         Integer mileage,
                         WheelDriveType wheelDriveType) {
        Car car = new Car();
        car.brand = brand;
        car.model = model;
        car.price = price;
        car.creationDate = creationDate;
        car.transmission = transmission;
        car.bodyType = bodyType;
        car.engineType = engineType;
        car.mileage = mileage;
        car.wheelDriveType = wheelDriveType;
        return car;
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
     * @return Brand.
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * The setter.
     * @param brand brand.
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * The getter.
     * @return model.
     */
    public String getModel() {
        return model;
    }

    /**
     * The setter.
     * @param model model.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * The getter.
     * @return price.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * The setter.
     * @param price price.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * The getter.
     * @return creationDate.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * The setter.
     * @param creationDate creationDate.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * The getter.
     * @return transmission.
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * The setter.
     * @param transmission transmission.
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * The getter.
     * @return bodyType.
     */
    public BodyType getBodyType() {
        return bodyType;
    }

    /**
     * The setter.
     * @param bodyType bodyType.
     */
    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * The getter.
     * @return engineType.
     */
    public EngineType getEngineType() {
        return engineType;
    }

    /**
     * The setter.
     * @param engineType engineType.
     */
    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    /**
     * The getter.
     * @return mileage.
     */
    public Integer getMileage() {
        return mileage;
    }

    /**
     * The setter.
     * @param mileage mileage.
     */
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    /**
     * The getter.
     * @return wheelDriveType.
     */
    public WheelDriveType getWheelDriveType() {
        return wheelDriveType;
    }

    /**
     * The setter.
     * @param wheelDriveType wheelDriveType.
     */
    public void setWheelDriveType(WheelDriveType wheelDriveType) {
        this.wheelDriveType = wheelDriveType;
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
        Car car = (Car) o;
        return id.equals(car.id);
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
        return "Car{" +
                "id=" + id +
                ", brand=" + brand +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", creationDate=" + creationDate +
                ", transmission=" + transmission +
                ", bodyType=" + bodyType +
                ", engineType=" + engineType +
                ", mileage=" + mileage +
                ", wheelDriveType=" + wheelDriveType +
                '}';
    }
}