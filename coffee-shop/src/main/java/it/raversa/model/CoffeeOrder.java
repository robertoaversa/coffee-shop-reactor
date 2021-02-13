package it.raversa.model;

import it.raversa.CoffeeTypeDeserializer;

import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name = "CoffeeOrder.findAll", query = "SELECT o FROM CoffeeOrder o")
public class CoffeeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @JsonbTypeAdapter(CoffeeTypeDeserializer.class)
    @Enumerated(EnumType.STRING)
    private CoffeeType type;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PREPARING;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CoffeeOrder{" +
                "id=" + id +
                ", type=" + type +
                ", status=" + status +
                '}';
    }

}
