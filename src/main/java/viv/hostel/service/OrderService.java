package viv.hostel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import viv.hostel.entity.Department;
import viv.hostel.entity.Order;
import viv.hostel.entity.User;
import viv.hostel.exception.NotFoundException;
import viv.hostel.repository.DepartmentRepo;
import viv.hostel.repository.OrderRepo;
import viv.hostel.repository.UserRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final DepartmentRepo departmentRepo;
    private final UserRepo userRepo;

    public Order getByName(String name) {
        return orderRepo.findByName(name).orElseThrow(() -> new NotFoundException("Not found with name:" + name));
    }

    public List<Order> getByUserId(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<Order> getByComplete(boolean complete) {
        return orderRepo.findByComplete(complete);
    }

    public List<Order> getByDepartment(String departmentSlug) {
        Department depart = departmentRepo.findBySlug(departmentSlug).orElseThrow(
                () -> new NotFoundException("Not found department with " + departmentSlug));
        return orderRepo.findByDepartment(depart);
    }

    public Order save(Order order, String username) {
        Optional<Order> order1 = orderRepo.findByName(order.getName());
        if(order1.isPresent()) {
            return order1.get();
        } else {
            Optional<User> user = userRepo.findByUsername(username);
            user.ifPresent(order::setUser);
            order.setDateOfApplication(new Date());
            return orderRepo.save(order);
        }
    }
}
