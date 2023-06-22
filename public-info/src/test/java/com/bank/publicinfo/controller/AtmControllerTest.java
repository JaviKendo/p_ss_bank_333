package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.AtmServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AtmController.class)
class AtmControllerTest {
    @Autowired
    private AtmController atmController;

    @MockBean
    private AtmServiceImpl atmService;

    private Branch branch = new Branch();
    private AtmDTO atmDTO1;
    private AtmDTO atmDTO2;

    @Test
    void testGetAllAtms() {
        List<AtmDTO> atmDTO = List.of(createAtms().get(0), createAtms().get(1));
        when(atmService.getAllAtms()).thenReturn(atmDTO.stream().map(PublicInfoMapper.INSTANCE::convertToAtm).collect(Collectors.toList()));
        List<AtmDTO> atmDTOToTest = atmController.getAllAtms();
        assertNotNull(atmDTO);
        assertNotNull(atmDTOToTest);
        assertEquals(atmDTO.size(), atmDTOToTest.size());
    }

    @Test
    void testGetAtmById() {
        AtmDTO atmDTO = createAtms().get(0);
        when(atmService.getAtmById(1L)).thenReturn(PublicInfoMapper.INSTANCE.convertToAtm(atmDTO));
        AtmDTO atmDTOToTest = atmController.getAtmById(1L);
        assertNotNull(atmDTO);
        assertNotNull(atmDTOToTest);
        assertEquals(atmDTO.getAddress(), atmDTOToTest.getAddress());
        assertEquals(atmDTO.getStartOfWork(), atmDTOToTest.getStartOfWork());
        assertEquals(atmDTO.getEndOfWork(), atmDTOToTest.getEndOfWork());
        assertEquals(atmDTO.getAllHours(), atmDTOToTest.getAllHours());
        assertEquals(atmDTO.getAtm(), atmDTOToTest.getAtm());
    }

    @Test
    void testCreateAtm() {
        Atm atm = new Atm(1L, "address", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"), true , branch);
        atmController.createAtm(PublicInfoMapper.INSTANCE.convertToAtmDTO(atm));
        verify(atmService, times(1)).createAtm(any());
    }

    @Test
    void testUpdateAtm() {
        Atm atm = new Atm(1L, "address", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"), true , new Branch());
        atmController.updateAtm(atm.getId(), PublicInfoMapper.INSTANCE.convertToAtmDTO(atm));
        verify(atmService, times(1)).updateAtm(any());
    }

    @Test
    void testDeleteAtm() {
        atmController.deleteAtm(any());
        verify(atmService, times(1)).deleteAtm(any());
    }

    List<AtmDTO> createAtms() {
        atmDTO1 = new AtmDTO("address", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"), true , branch);
        atmDTO2 = new AtmDTO("address", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"), true , branch);
        return List.of(atmDTO1, atmDTO2);
    }
}

