package viv.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import viv.hostel.entity.Order;
import viv.hostel.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/not-complete")
    public List<Order> getNotComplete() {
        return orderService.getByComplete(false);
    }

    @GetMapping("/complete")
    public List<Order> getComplete() {
        return orderService.getByComplete(true);
    }

    @GetMapping("/{departmentSlug}")
    public List<Order> getOrderByDepartment(@PathVariable String departmentSlug) {
        return orderService.getByDepartment(departmentSlug);
    }

    @PostMapping("/new")
    public Order addOrder(@RequestBody Order order, @RequestBody String username) {
        return orderService.save(order, username);
    }
}
