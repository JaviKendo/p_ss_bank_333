package com.bank.authorization.service;

import com.bank.authorization.dto.UserDTO;
import com.bank.authorization.entity.User;
import com.bank.authorization.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AuditService auditService;

    @Test
    public void findByIdTest() {
        // Arrange
        User user = new User(1L, "ROLE_ADMIN", 101L, "password");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        UserDTO result = userService.findById(1L);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("ROLE_ADMIN", result.getRole());
        assertEquals(101L, result.getProfileId());
        assertEquals("password", result.getPassword());
    }

    @Test
    public void findByIdNotFoundTest() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> userService.findById(1L));
    }

    @Test
    public void findAllTest() {
        // Arrange
        User user1 = new User(1L, "ROLE_ADMIN", 101L, "password");
        User user2 = new User(2L, "ROLE_USER", 102L, "password");
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserDTO> result = userService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("ROLE_ADMIN", result.get(0).getRole());
        assertEquals(101L, result.get(0).getProfileId());
        assertEquals("password", result.get(0).getPassword());
        assertEquals(2L, result.get(1).getId());
        assertEquals("ROLE_USER", result.get(1).getRole());
        assertEquals(102L, result.get(1).getProfileId());
        assertEquals("password", result.get(1).getPassword());
    }
}
