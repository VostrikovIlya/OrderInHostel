package viv.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import viv.hostel.entity.Department;
import viv.hostel.service.DepartmentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/depart")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/slugs")
    public List<String> getAllSlugs() {
        return departmentService.getAllSlugs();
    }

    @GetMapping("/{slug}")
    public Department getDepartmentBySlug(@PathVariable String slug) {
        return departmentService.getBySlug(slug);
    }

    @PostMapping("/save")
    public Department saveDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }
}
