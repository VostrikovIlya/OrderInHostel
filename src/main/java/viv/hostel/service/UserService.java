package viv.hostel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viv.hostel.entity.Employee;
import viv.hostel.entity.Role;
import viv.hostel.entity.User;
import viv.hostel.exception.NotFoundException;
import viv.hostel.repository.EmployeeRepo;
import viv.hostel.repository.RoleRepo;
import viv.hostel.repository.UserRepo;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final EmployeeRepo employeeRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            Optional<Employee> employee = employeeRepo.findByUsername(username);
            employee.ifPresent(value -> value.getAuthorities().iterator());
            return employee.orElseThrow(() -> new UsernameNotFoundException("Not found username: " + username));
        }
    }

    public User saveUser(User user) {
        Optional<User> user1 = userRepo.findByUsername(user.getUsername());
        if(user1.isPresent()) {
            return user1.get();
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            addRoleToUser(user.getUsername(), "USER");
            return  userRepo.save(user);
        }
    }

    public Employee saveEmployee(Employee employee) {
        Optional<Employee> employee1 = employeeRepo.findByUsername(employee.getUsername());
        if(employee1.isPresent()) {
            return employee1.get();
        } else {
            employee.setEnabled(true);
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            addRoleToEmployee(employee.getUsername(),"EMPLOYEE");
            return employeeRepo.save(employee);
        }
    }

    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        Optional<User> user = userRepo.findByUsername(username);
        Optional<Role> role = roleRepo.findByName(roleName);
        user.ifPresent(value -> value.addAuthorities(role.orElseThrow(()->new NotFoundException("Not found role name:" + roleName))));
    }
    public void addRoleToEmployee(String username, String roleName) {
        Optional<Employee> employee = employeeRepo.findByUsername(username);
        Optional<Role> role = roleRepo.findByName(roleName);
        employee.ifPresent(value -> value.addAuthorities(role.orElseThrow(()->new NotFoundException("Not found role name:" + roleName))));
    }
}
