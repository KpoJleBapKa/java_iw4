package com.university.departmentmanagement;

import com.university.departmentmanagement.models.Department;
import com.university.departmentmanagement.repositories.DepartmentRepository;
import com.university.departmentmanagement.services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListDepartments() {
        Department department1 = new Department(1L, "Department1", "Title1", "123456789", "Room1");
        Department department2 = new Department(2L, "Department2", "Title2", "987654321", "Room2");
        List<Department> departments = Arrays.asList(department1, department2);

        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> result = departmentService.listDepartments();

        assertEquals(2, result.size());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    public void testAddDepartment() {
        Department department = new Department(1L, "Department1", "Title1", "123456789", "Room1");

        departmentService.addDepartment(department);

        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    public void testDeleteDepartment() {
        Long departmentId = 1L;

        departmentService.deleteDepartment(departmentId);

        verify(departmentRepository, times(1)).deleteById(departmentId);
    }

    @Test
    public void testGetDepartmentById() {
        Department department = new Department(1L, "Department1", "Title1", "123456789", "Room1");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Department result = departmentService.getDepartmentById(1L);

        assertNotNull(result);
        assertEquals("Department1", result.getName());
        verify(departmentRepository, times(1)).findById(1L);
    }
}
