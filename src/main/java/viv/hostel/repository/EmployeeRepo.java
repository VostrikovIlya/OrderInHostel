package viv.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viv.hostel.entity.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepo  extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
}
