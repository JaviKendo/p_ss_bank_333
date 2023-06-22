package com.bank.publicinfo.entity;

import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class BranchTest {
    Branch branch = new Branch(1L, "address", 123456L, "city", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"));

    @Test
    void getId() {
        assertEquals(1L, branch.getId(), "id не совпали");
    }

    @Test
    void getAddress() {
        assertEquals("address", branch.getAddress(), "address не совпали");
    }

    @Test
    void getPhoneNumber() {
        assertEquals(123456L, branch.getPhoneNumber(), "phoneNumber не совпали");
    }

    @Test
    void getCity() {
        assertEquals("city", branch.getCity(), "city не совпали");
    }

    @Test
    void getStartOfWork() {
        assertEquals(Time.valueOf("08:00:00"), branch.getStartOfWork(), "startOfWork не совпали");
    }

    @Test
    void getEndOfWork() {
        assertEquals(Time.valueOf("20:00:00"), branch.getEndOfWork(), "endOfWork не совпали");
    }

    @Test
    void setId() {
        Branch branch = new Branch();
        branch.setId(1L);
        assertEquals(1L, branch.getId(), "setId отработал не верно");
    }

    @Test
    void setAddress() {
        Branch branch = new Branch();
        branch.setAddress("address");
        assertEquals("address", branch.getAddress(), "setAddress отработал не верно");
    }

    @Test
    void setPhoneNumber() {
        Branch branch = new Branch();
        branch.setPhoneNumber(123456L);
        assertEquals(123456L, branch.getPhoneNumber(), "setPhoneNumber отработал не верно");
    }

    @Test
    void setCity() {
        Branch branch = new Branch();
        branch.setCity("city");
        assertEquals("city", branch.getCity(), "setCity отработал не верно");
    }

    @Test
    void setStartOfWork() {
        Branch branch = new Branch();
        branch.setStartOfWork(Time.valueOf("08:00:00"));
        assertEquals(Time.valueOf("08:00:00"), branch.getStartOfWork(), "setStartOfWork отработал не верно");
    }

    @Test
    void setEndOfWork() {
        Branch branch = new Branch();
        branch.setEndOfWork(Time.valueOf("20:00:00"));
        assertEquals(Time.valueOf("20:00:00"), branch.getEndOfWork(), "setEndOfWork отработал не верно");
    }
}