package viv.hostel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import viv.hostel.entity.Order;
import viv.hostel.repository.OrderRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepo orderRepo;

    public Order getByName(String name) {
        return orderRepo.getByName(name);
    }

    public List<Order> getByUserId(Long userId) {
        return orderRepo.getByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepo.getAll();
    }

    public List<Order> getByComplete(boolean complete) {
        return orderRepo.getByComplete(complete);
    }
}
