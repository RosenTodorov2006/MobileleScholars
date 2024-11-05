package bg.soft_uni.mobilelelele.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "exRates")
@Entity
public class ExRate extends BaseEntity{
    private String name;
    private double currency;

    public ExRate(String name, double currency) {
        this.name = name;
        this.currency = currency;
    }

    public ExRate() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrency() {
        return currency;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }
}
