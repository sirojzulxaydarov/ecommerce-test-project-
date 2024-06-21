package org.example.online_ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="orders")
public class Order{

    @Id
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq" )
    private Integer id;

    @CreationTimestamp
    private LocalDateTime orderedAt;

}
