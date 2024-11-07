package bg.soft_uni.mobilelelele.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity{
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime modified;
    @Column(unique = true)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Model> models;

    public Brand(LocalDateTime created, LocalDateTime modified, String name) {
        this.created = created;
        this.modified = modified;
        this.name = name;
    }

    public Brand() {

    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
