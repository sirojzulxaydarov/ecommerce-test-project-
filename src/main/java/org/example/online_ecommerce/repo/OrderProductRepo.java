package org.example.online_ecommerce.repo;

import org.example.online_ecommerce.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepo extends JpaRepository<OrderProduct, UUID> {
    List<OrderProduct> findAllByOrderId(Integer orderId);
}
