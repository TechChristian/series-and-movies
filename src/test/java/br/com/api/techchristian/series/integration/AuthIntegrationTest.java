package br.com.api.techchristian.series.integration;

import br.com.api.techchristian.series.database.repository.IUserRepository;
import br.com.api.techchristian.series.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ResultActions performPostRegister(UserDto.UserRegisterDto registerCredentials) throws Exception {
        return mockMvc.perform(
                post("/v1/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerCredentials))
        );
    }

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        UserDto.UserRegisterDto userRegisterCredentials = new UserDto.UserRegisterDto(
                "Christian",
                "chris.lopes@gmail.com",
                "12345678"
        );
        ResultActions response = performPostRegister(userRegisterCredentials)
                .andExpect(status().isCreated());

        response.andExpect(jsonPath("$.id").isNotEmpty()).
                andExpect(jsonPath("$.name").value("Christian"))
                .andExpect(jsonPath("$.email").value("chris.lopes@gmail.com"));
    }

}
