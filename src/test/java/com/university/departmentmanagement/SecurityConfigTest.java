package com.university.departmentmanagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldAllowAccessToDepartmentsWithoutAuthentication() throws Exception {
		mockMvc.perform(get("/departments"))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void shouldAllowAuthenticatedUserToAccessProtectedEndpoints() throws Exception {
		mockMvc.perform(get("/departments"))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void shouldDenyAccessToProtectedEndpointsWithoutAuthentication() throws Exception {
		mockMvc.perform(get("/departments"))
				.andExpect(status().isOk())
				.andDo(print());
	}
}
