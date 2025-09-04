package com.bondarev.backend.api;

import com.bondarev.backend.AbstractTest;
import com.bondarev.backend.model.entity.User;
import com.bondarev.backend.util.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskControllerTest extends AbstractTest {
    @Autowired
    private MockMvc api;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Test
    void test() throws Exception {
        String token = generateToken();
        api.perform(get("/api/tasks")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    private String generateToken() {
        return jwtTokenUtil.generateToken(User.builder()
                .username("testUser")
                .email("test@email.com")
                .build());
    }
}
