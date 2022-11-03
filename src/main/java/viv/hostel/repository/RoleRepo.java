package viv.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viv.hostel.entity.Role;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
