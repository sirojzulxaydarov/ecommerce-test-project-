package org.example.online_ecommerce.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.online_ecommerce.dto.BasketProduct;
import org.example.online_ecommerce.entity.Order;
import org.example.online_ecommerce.entity.OrderProduct;
import org.example.online_ecommerce.entity.Product;
import org.example.online_ecommerce.repo.OrderProductRepo;
import org.example.online_ecommerce.repo.OrderRepo;
import org.example.online_ecommerce.repo.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestMapping("/basket")
@Controller
@RequiredArgsConstructor
public class BasketController {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;

    @PostMapping
    public String addBasket(@RequestParam UUID productId, @RequestParam(required = false) UUID categoryId, HttpSession httpSession) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        Object object = httpSession.getAttribute("basket");
        if (object == null) {
            BasketProduct basketProduct = new BasketProduct(
                    product, 1
            );
            List<BasketProduct> basketProducts = new ArrayList<>();
            basketProducts.add(basketProduct);
            httpSession.setAttribute("basket", basketProducts);
        } else {
            List<BasketProduct> basketProducts = (List<BasketProduct>) object;
            BasketProduct basketProduct = new BasketProduct(
                    product, 1
            );
            basketProducts.add(basketProduct);
            httpSession.setAttribute("basket", basketProducts);
        }
        String backPath = null;
        if (categoryId != null) {
            backPath = "/?categoryId=" + categoryId;
        } else {
            backPath = "/";
        }
        return "redirect:%s".formatted(backPath);
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/delete")
    public String deleteBasket(@RequestParam UUID productId, HttpSession httpSession) {
        deleteFromBasket(productId, httpSession);
        return "redirect:/";
    }

    private static void deleteFromBasket(UUID productId, HttpSession httpSession) {
//        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        List<BasketProduct> basketProducts = (List<BasketProduct>) httpSession.getAttribute("basket");
        List<BasketProduct> newbp = basketProducts.stream().filter(item -> !item.getProduct().getId().equals(productId)).toList();
        httpSession.setAttribute("basket", new ArrayList<>(newbp));

    }

    @GetMapping
    public String showBasket(Model model, HttpSession httpSession) {
        List<BasketProduct> basketProducts = Objects.requireNonNullElse((List<BasketProduct>) httpSession.getAttribute("basket"), new ArrayList<>());
        if (basketProducts.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("basketProducts", basketProducts);
        int sum = basketProducts.stream().mapToInt(item -> item.getProduct().getPrice() * item.getAmount()).sum();
        model.addAttribute("totalAmount", sum);
        return "basket";
    }

    @PostMapping("amount")
    public String changeBasketProductAmount(HttpSession session, @RequestParam UUID productId, @RequestParam String operation) {
        List<BasketProduct> basketProducts = (List<BasketProduct>) session.getAttribute("basket");
        for (BasketProduct basketProduct : basketProducts) {
            if (basketProduct.getProduct().getId().equals(productId)) {
                if (operation.equals("++")) {
                    basketProduct.setAmount(basketProduct.getAmount() + 1);
                    session.setAttribute("basket", basketProducts);
                } else {
                    if (basketProduct.getAmount() == 1) {
                        deleteBasket(productId, session);
                    } else {
                        basketProduct.setAmount(basketProduct.getAmount() - 1);
                        session.setAttribute("basket", basketProducts);
                    }
                }
                return "redirect:/basket";
            }
        }
        return "redirect:/basket";

    }

    @Transactional
    @PostMapping("/checkout")
    public String checkout(HttpSession session) {
        List<BasketProduct> basketProducts = Objects.requireNonNullElse((List<BasketProduct>) session.getAttribute("basket"), new ArrayList<>());
        Order order = new Order();
        orderRepo.save(order);

        for (BasketProduct basketProduct : basketProducts) {
            OrderProduct orderProduct = new OrderProduct(
                    order,
                    basketProduct.getProduct(),
                    basketProduct.getAmount()
            );
            orderProductRepo.save(orderProduct);
        }
        session.removeAttribute("basket");
        return "redirect:/";
    }


}
