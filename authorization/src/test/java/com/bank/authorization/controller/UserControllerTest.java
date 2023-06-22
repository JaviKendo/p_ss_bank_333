package com.bank.authorization.controller;

import com.bank.authorization.dto.UserDTO;
import com.bank.authorization.service.AuditService;
import com.bank.authorization.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;
    @MockBean
    private AuditService auditService;

    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        userDTO = new UserDTO(1L, "Admin", 2L, "password123");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userService.save(any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.role").value("Admin"))
                .andExpect(jsonPath("$.profileId").value(2L))
                .andExpect(jsonPath("$.password").value("password123"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Создание объекта для обновления пользователя
        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.setId(1L);
        updatedUserDTO.setRole("UpdatedRole");
        updatedUserDTO.setProfileId(3L);
        updatedUserDTO.setPassword("updatedPassword");

        // Мок сервиса
        when(userService.updateUser(anyLong(), any(UserDTO.class))).thenReturn(updatedUserDTO);

        // Ожидаемый JSON-объект
        String expectedJson = objectMapper.writeValueAsString(updatedUserDTO);

        // Отправка PUT-запроса и проверка результата
        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUserDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
                .andExpect(jsonPath("$.id").value(updatedUserDTO.getId()))
                .andExpect(jsonPath("$.role").value(updatedUserDTO.getRole()))
                .andExpect(jsonPath("$.profileId").value(updatedUserDTO.getProfileId()))
                .andExpect(jsonPath("$.updatedPassword").doesNotExist());
        // Проверка вызова сервиса
        verify(userService, times(1)).updateUser(anyLong(), any(UserDTO.class));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetAllUsers() throws Exception {
        List<UserDTO> users = Arrays.asList(userDTO);
        when(userService.findAll()).thenReturn(users);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].role").value("Admin"))
                .andExpect(jsonPath("$[0].profileId").value(2L))
                .andExpect(jsonPath("$[0].password").value("password123"));
    }
    @Test
    public void testGetUserById() throws Exception {
        when(userService.findById(1L)).thenReturn(userDTO);
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.role").value("Admin"))
                .andExpect(jsonPath("$.profileId").value(2L))
                .andExpect(jsonPath("$.password").value("password123"));
    }
    @Test
    public void testGetUserByProfileId() throws Exception {
        when(userService.findByProfileId(2L)).thenReturn(userDTO);
        mockMvc.perform(get("/users/profile/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.role").value("Admin"))
                .andExpect(jsonPath("$.profileId").value(2L))
                .andExpect(jsonPath("$.password").value("password123"));
    }
}