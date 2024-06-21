package org.example.online_ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.online_ecommerce.entity.abs.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderProduct extends BaseEntity {

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private Integer amount;
}
