package com.university.departmentmanagement.controllers;

import com.university.departmentmanagement.models.Department;
import com.university.departmentmanagement.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/departments")
    public String departments(Model model) {
        model.addAttribute("departments", departmentService.listDepartments());
        return "departments";
    }

    @GetMapping("/departments/{id}")
    public String departmentInfo(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            model.addAttribute("department", department);
            return "department-info";
        }
        return "redirect:/departments";
    }

    @PostMapping("/departments/create")
    public String createDepartment(Department department) {
        departmentService.addDepartment(department);
        return "redirect:/departments";
    }

    @PostMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
