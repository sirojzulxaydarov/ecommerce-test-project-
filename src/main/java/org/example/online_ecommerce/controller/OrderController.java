package org.example.online_ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.online_ecommerce.entity.Order;
import org.example.online_ecommerce.entity.OrderProduct;
import org.example.online_ecommerce.repo.OrderProductRepo;
import org.example.online_ecommerce.repo.OrderRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;

    @GetMapping
    public String orders(Model model) {
        List<Order> orders = orderRepo.findAll();
        model.addAttribute("orders", orders);
        return "order";
    }

    @GetMapping("/info/{id}")
    public String orderInfo(@PathVariable Integer id, Model model) {
        List<OrderProduct> allByOrderId = orderProductRepo.findAllByOrderId(id);
        model.addAttribute("orderProducts", allByOrderId);
        return "info";

    }
}
