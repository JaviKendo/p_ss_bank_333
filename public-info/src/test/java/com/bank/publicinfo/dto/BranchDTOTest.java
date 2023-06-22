package com.bank.publicinfo.dto;

import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class BranchDTOTest {
    BranchDTO branchDTO = new BranchDTO("address", 123456L, "city", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"));

    @Test
    void getAddress() {
        assertEquals("address", branchDTO.getAddress(), "address не совпали");
    }

    @Test
    void getPhoneNumber() {
        assertEquals(123456L, branchDTO.getPhoneNumber(), "phoneNumber не совпали");
    }

    @Test
    void getCity() {
        assertEquals("city", branchDTO.getCity(), "city не совпали");
    }

    @Test
    void getStartOfWork() {
        assertEquals(Time.valueOf("08:00:00"), branchDTO.getStartOfWork(), "startOfWork не совпали");
    }

    @Test
    void getEndOfWork() {
        assertEquals(Time.valueOf("20:00:00"), branchDTO.getEndOfWork(), "endOfWork не совпали");
    }

    @Test
    void setAddress() {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setAddress("address");
        assertEquals("address", branchDTO.getAddress(), "setAddress отработал не верно");
    }

    @Test
    void setPhoneNumber() {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setPhoneNumber(123456L);
        assertEquals(123456L, branchDTO.getPhoneNumber(), "setPhoneNumber отработал не верно");
    }

    @Test
    void setCity() {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setCity("city");
        assertEquals("city", branchDTO.getCity(), "setCity отработал не верно");
    }

    @Test
    void setStartOfWork() {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setStartOfWork(Time.valueOf("08:00:00"));
        assertEquals(Time.valueOf("08:00:00"), branchDTO.getStartOfWork(), "setStartOfWork отработал не верно");
    }

    @Test
    void setEndOfWork() {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setEndOfWork(Time.valueOf("20:00:00"));
        assertEquals(Time.valueOf("20:00:00"), branchDTO.getEndOfWork(), "setEndOfWork отработал не верно");
    }
}