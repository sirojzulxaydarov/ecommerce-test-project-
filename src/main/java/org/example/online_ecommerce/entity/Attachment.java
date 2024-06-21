package org.example.online_ecommerce.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.online_ecommerce.entity.abs.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends BaseEntity {
    
    private byte[] content;
}
