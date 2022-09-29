package viv.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viv.hostel.entity.Department;
import viv.hostel.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByComplete(boolean complete);
    Optional<Order> findByName(String name);
    List<Order> findByUserId(Long userId);
    List<Order> findByEmployeeId(Long employeeId);
    List<Order> findByDepartment(Department department);
}
