package viv.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viv.hostel.entity.Order;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> getByComplete(boolean complete);
    Order getByName(String name);
    List<Order> getByUserId(Long userId);
    List<Order> getByEmployeeId(Long employeeId);
    List<Order> getAll();
}
