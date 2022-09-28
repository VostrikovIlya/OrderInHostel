package viv.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viv.hostel.entity.Employee;

public interface EmployeeRepo  extends JpaRepository<Employee, Long> {
    Employee getByUsername(String username);
}
