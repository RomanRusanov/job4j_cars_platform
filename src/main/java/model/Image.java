package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 20.01.2021
 * email roman9628@gmail.com
 * The class describe instance image that contain filename, relative path with filename
 * that user add for item.
 */
@Entity
public class Image {
    /**
     * The unique id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The filed contain relative path with filename.
     */
    @Column(nullable = false)
    private String path;
    /**
     * The filename.
     */
    @Column(nullable = false)
    private String fileName;

    /**
     * The method create instance image.
     * @param path relative path with filename.
     * @param fileName filename.
     * @return Instance image.
     */
    public static Image of(String path, String fileName) {
        Image image = new Image();
        image.path = path;
        image.fileName = fileName;
        return image;
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
     * @return path.
     */
    public String getPath() {
        return path;
    }

    /**
     * The setter.
     * @param path path.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * The getter.
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * The setter.
     * @param fileName fileName.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
        Image image = (Image) o;
        return id.equals(image.id)
                && path.equals(image.path)
                && fileName.equals(image.fileName);
    }

    /**
     * The method override hashcode.
     * @return generate hash using fields.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, path, fileName);
    }

    /**
     * The override toString method.
     * @return String.
     */
    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}