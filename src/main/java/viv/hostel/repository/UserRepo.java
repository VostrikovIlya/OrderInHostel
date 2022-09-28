package viv.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viv.hostel.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User getByUsername(String username);
}
