package com.example.employeeservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        EmployeeController controller = new EmployeeController();
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getEmployees_returnsInitialList() throws Exception {
        mockMvc.perform(get("/employees").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Employees", hasSize(5)));
    }

    @Test
    public void postEmployee_addsEmployeeAndReturnsCreated() throws Exception {
        String newEmployeeJson = "{\n" +
                "  \"employee_id\": \"E999\",\n" +
                "  \"first_name\": \"Alice\",\n" +
                "  \"last_name\": \"Green\",\n" +
                "  \"email\": \"alice.green@example.com\",\n" +
                "  \"title\": \"Engineer\"\n" +
                "}";

        // POST the new employee
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newEmployeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employee_id", is("E999")))
                .andExpect(jsonPath("$.first_name", is("Alice")));

        // Verify it was added (now size should be 6)
        mockMvc.perform(get("/employees").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Employees", hasSize(6)))
                .andExpect(jsonPath("$.Employees[5].employee_id", is("E999")));
    }

    @Test
    public void postEmployee_malformedJson_returnsBadRequest() throws Exception {
        String badJson = "{ \"employee_id\": \"E123\", \"first_name\": \"Bob\" "; // missing closing brace

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postEmployee_emptyBody_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }
}
