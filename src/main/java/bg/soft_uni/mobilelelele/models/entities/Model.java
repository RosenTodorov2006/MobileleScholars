package bg.soft_uni.mobilelelele.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class Model extends BaseEntity{
    @Column
    private int category;
    @Column
    private LocalDateTime created;
    @Column(name = "end_year")
    private int endYear;
    @Column(name = "image_url", columnDefinition = "VARCHAR(512)")
    private String imageUrl;
    @Column
    private LocalDateTime modified;
    @Column
    @NotNull
    private String name;
    @Column(name = "start_year")
    private int startYear;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    public Model(int category, LocalDateTime created, int endYear, String imageUrl, LocalDateTime modified, String name, int startYear, Brand brand) {
        this.category = category;
        this.created = created;
        this.endYear = endYear;
        this.imageUrl = imageUrl;
        this.modified = modified;
        this.name = name;
        this.startYear = startYear;
        this.brand = brand;
    }

    public Model() {

    }


    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
