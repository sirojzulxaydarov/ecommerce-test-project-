package org.example.online_ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
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
public class Product extends BaseEntity {

    private String name;
    private Integer price;

    @ManyToOne
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment attachment;

    @Transient
    private boolean hasInBasket;
}
