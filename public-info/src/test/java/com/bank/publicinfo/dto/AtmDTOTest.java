package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.Branch;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class AtmDTOTest {
    Branch branch = new Branch();
    AtmDTO atmDTO = new AtmDTO("address",Time.valueOf("08:00:00"), Time.valueOf("20:00:00"), true , branch);

    @Test
    void getAddress() {
        assertEquals("address", atmDTO.getAddress(), "address не совпали");
    }

    @Test
    void getStartOfWork() {
        assertEquals(Time.valueOf("08:00:00"), atmDTO.getStartOfWork(), "StartOfWork не совпали");
    }

    @Test
    void getEndOfWork() {
        assertEquals(Time.valueOf("20:00:00"), atmDTO.getEndOfWork(), "EndOfWork не совпали");
    }

    @Test
    void getAllHours() {
        assertEquals(true, atmDTO.getAllHours(), "AllHours не совпали");
    }

    @Test
    void getAtm() {
        assertEquals(branch, atmDTO.getAtm(), "Branch не совпали");
    }

    @Test
    void setAddress() {
        AtmDTO atmDTO = new AtmDTO();
        atmDTO.setAddress("address");
        assertEquals("address", atmDTO.getAddress(), "setAddress отработал не верно");
    }

    @Test
    void setStartOfWork() {
        AtmDTO atmDTO = new AtmDTO();
        atmDTO.setStartOfWork(Time.valueOf("08:00:00"));
        assertEquals(Time.valueOf("08:00:00"), atmDTO.getStartOfWork(), "setStartOfWork отработал не верно");
    }

    @Test
    void setEndOfWork() {
        AtmDTO atmDTO = new AtmDTO();
        atmDTO.setEndOfWork(Time.valueOf("20:00:00"));
        assertEquals(Time.valueOf("20:00:00"), atmDTO.getEndOfWork(), "setEndOfWork отработал не верно");
    }

    @Test
    void setAllHours() {
        AtmDTO atmDTO = new AtmDTO();
        atmDTO.setAllHours(true);
        assertEquals(true, atmDTO.getAllHours(), "setAllHours отработал не верно");
    }

    @Test
    void setAtm() {
        AtmDTO atmDTO = new AtmDTO();
        atmDTO.setAtm(branch);
        assertEquals(branch, atmDTO.getAtm(), "setAtm отработал не верно");
    }
}