package viv.hostel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import viv.hostel.entity.Department;
import viv.hostel.repository.DepartmentRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentService {
    private final DepartmentRepo departmentRepo;

    public List<String> getAllSlugs() {
        return departmentRepo.findAll().stream().map(Department::getSlug).collect(Collectors.toList());
    }

    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    public Department getBySlug(String slug) {
        return departmentRepo.findBySlug(slug).orElseThrow(()->new RuntimeException("NO"));
    }

}
