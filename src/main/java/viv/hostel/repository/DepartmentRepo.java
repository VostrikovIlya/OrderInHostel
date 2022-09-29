package viv.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viv.hostel.entity.Department;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
    Optional<Department> findBySlug(String slug);
}
