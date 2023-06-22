package com.bank.authorization.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDTOTest {

    @Test
    void userDTOTest() {
        UserDTO userDTO = new UserDTO(1L, "role", 100L, "password");

        userDTO.setId(2L);
        userDTO.setRole("newRole");
        userDTO.setProfileId(200L);
        userDTO.setPassword("newPassword");
        assertEquals(2L, userDTO.getId());
        assertEquals("newRole", userDTO.getRole());
        assertEquals(200L, userDTO.getProfileId());
        assertEquals("newPassword", userDTO.getPassword());
    }
}
