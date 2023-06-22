package com.bank.publicinfo.entity;

import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class AtmTest {
    Branch branch = new Branch();
    Atm atm = new Atm(1L, "address", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"), true , branch);
    @Test
    void getId() {
        assertEquals(1L, atm.getId(), "id не совпали");
    }

    @Test
    void getAddress() {
        assertEquals("address", atm.getAddress(), "address не совпали");
    }

    @Test
    void getStartOfWork() {
        assertEquals(Time.valueOf("08:00:00"), atm.getStartOfWork(), "StartOfWork не совпали");
    }

    @Test
    void getEndOfWork() {
        assertEquals(Time.valueOf("20:00:00"), atm.getEndOfWork(), "EndOfWork не совпали");
    }

    @Test
    void getAllHours() {
        assertEquals(true, atm.getAllHours(), "AllHours не совпали");
    }

    @Test
    void getAtm() {
        assertEquals(branch, atm.getAtm(), "Branch не совпали");
    }

    @Test
    void setId() {
        Atm atm = new Atm();
        atm.setId(1L);
        assertEquals(1L, atm.getId(), "setId отработал не верно");
    }

    @Test
    void setAddress() {
        Atm atm = new Atm();
        atm.setAddress("address");
        assertEquals("address", atm.getAddress(), "setAddress отработал не верно");
    }

    @Test
    void setStartOfWork() {
        Atm atm = new Atm();
        atm.setStartOfWork(Time.valueOf("08:00:00"));
        assertEquals(Time.valueOf("08:00:00"), atm.getStartOfWork(), "setStartOfWork отработал не верно");
    }

    @Test
    void setEndOfWork() {
        Atm atm = new Atm();
        atm.setEndOfWork(Time.valueOf("20:00:00"));
        assertEquals(Time.valueOf("20:00:00"), atm.getEndOfWork(), "setEndOfWork отработал не верно");
    }

    @Test
    void setAllHours() {
        Atm atm = new Atm();
        atm.setAllHours(true);
        assertEquals(true, atm.getAllHours(), "setAllHours отработал не верно");
    }

    @Test
    void setAtm() {
        Atm atm = new Atm();
        atm.setAtm(branch);
        assertEquals(branch, atm.getAtm(), "setAtm отработал не верно");
    }
}