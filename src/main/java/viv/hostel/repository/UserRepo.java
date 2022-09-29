package viv.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viv.hostel.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User getByUsername(String username);
}
