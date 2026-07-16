package br.com.api.techchristian.series.integration;

import br.com.api.techchristian.series.database.models.User;
import br.com.api.techchristian.series.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    private ResultActions performPostLogin(UserDto.UserLoginDto loginCredentials) throws Exception {
        return mockMvc.perform(
                post("/v1/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginCredentials))
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

    @Test
    void shouldReturnBadRequestStatus() throws Exception {
        UserDto.UserRegisterDto userRegisterCredentials = new UserDto.UserRegisterDto(
                "",
                "christian@",
                "12345678"
        );
        ResultActions responseBadRequest = performPostRegister(userRegisterCredentials)
                .andDo(print())
                .andExpect(status().isBadRequest());

        responseBadRequest.andExpect(jsonPath("$.status").value(400));
        responseBadRequest.andExpect(jsonPath("$.message").value("Validation failed"));
        responseBadRequest.andExpect(jsonPath("$.errors.name").value("name is required."));
        responseBadRequest.andExpect(jsonPath("$.errors.email").value("Invalid email format"));

    }

    @Test
    void shouldLoginUserSuccessfully() throws Exception {
        UserDto.UserRegisterDto userRegisterCredentials = new UserDto.UserRegisterDto(
                "christian",
                "chris@hotmail.com",
                "12345678"
        );
        UserDto.UserLoginDto userLoginCredentials = new UserDto.UserLoginDto(
                "chris@hotmail.com",
                "12345678"
        );
        performPostRegister(userRegisterCredentials)
                .andExpect(status().isCreated());

    ResultActions response = performPostLogin(userLoginCredentials).
                andDo(print())
                .andExpect(status().isOk());

    response.andExpect(jsonPath("$.token").isNotEmpty());
    response.andExpect(jsonPath("$.expirationTime").isNumber());
    }

    @Test
    void shouldReturnLoginUnauthorized() throws Exception {
        UserDto.UserRegisterDto userRegisterCredentials = new UserDto.UserRegisterDto(
                "christian",
                "chris@hotmail.com",
                "12345678"
        );
        UserDto.UserLoginDto userLoginCredentials = new UserDto.UserLoginDto(
                "shahs@hotmail.com",
                "12345678"
        );

        performPostRegister(userRegisterCredentials)
        .andExpect(status().isCreated());

        performPostLogin(userLoginCredentials)
                .andExpect(status().isUnauthorized());
    }
}

