package viv.hostel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import viv.hostel.entity.Department;
import viv.hostel.entity.Order;
import viv.hostel.repository.DepartmentRepo;
import viv.hostel.repository.OrderRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final DepartmentRepo departmentRepo;

    public Order getByName(String name) {
        return orderRepo.findByName(name).orElseThrow(() -> new RuntimeException("Not found with name:" + name));
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
                () -> new RuntimeException("Not found department with " + departmentSlug));
        return orderRepo.findByDepartment(depart);
    }
}
