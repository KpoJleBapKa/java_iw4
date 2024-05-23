package com.university.departmentmanagement;

import com.university.departmentmanagement.controllers.DepartmentController;
import com.university.departmentmanagement.models.Department;
import com.university.departmentmanagement.services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DepartmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(departmentController)
                .setViewResolvers(viewResolver())
                .build();
    }

    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Test
    public void testDepartments() throws Exception {
        Department department1 = new Department(1L, "Department1", "Title1", "123456789", "Room1");
        Department department2 = new Department(2L, "Department2", "Title2", "987654321", "Room2");
        List<Department> departments = Arrays.asList(department1, department2);

        when(departmentService.listDepartments()).thenReturn(departments);

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(view().name("departments"))
                .andExpect(model().attributeExists("departments"))
                .andExpect(model().attribute("departments", departments));

        verify(departmentService, times(1)).listDepartments();
    }

    @Test
    public void testDepartmentInfo() throws Exception {
        Department department = new Department(1L, "Department1", "Title1", "123456789", "Room1");

        when(departmentService.getDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(get("/departments/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("department-info"))
                .andExpect(model().attributeExists("department"))
                .andExpect(model().attribute("department", department));

        verify(departmentService, times(1)).getDepartmentById(1L);
    }

    @Test
    public void testDepartmentInfoNotFound() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(null);

        mockMvc.perform(get("/departments/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments"));

        verify(departmentService, times(1)).getDepartmentById(1L);
    }

    @Test
    public void testCreateDepartment() throws Exception {
        Department department = new Department(1L, "Department1", "Title1", "123456789", "Room1");

        mockMvc.perform(post("/departments/create")
                        .param("name", "Department1")
                        .param("title", "Title1")
                        .param("phone", "123456789")
                        .param("classroom", "Room1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments"));

        verify(departmentService, times(1)).addDepartment(any(Department.class));
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        Long departmentId = 1L;

        mockMvc.perform(post("/departments/delete/{id}", departmentId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments"));

        verify(departmentService, times(1)).deleteDepartment(departmentId);
    }
}
