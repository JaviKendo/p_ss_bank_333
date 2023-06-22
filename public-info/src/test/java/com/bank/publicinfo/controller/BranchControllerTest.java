package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.BranchServiceImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BranchController.class)
class BranchControllerTest {
    @Autowired
    private BranchController branchController;

    @MockBean
    private BranchServiceImpl branchService;

    private BranchDTO branchDTO1;
    private BranchDTO branchDTO2;

    @Test
    void testGetAllBranchs() {
        List<BranchDTO> branchDTO = List.of(createBrancs().get(0), createBrancs().get(1));
        when(branchService.getAllBranch()).thenReturn(branchDTO.stream().map(PublicInfoMapper.INSTANCE::convertToBranch).collect(Collectors.toList()));
        List<BranchDTO> branchDTOToTest = branchController.getAllBranchs();
        assertNotNull(branchDTO);
        assertNotNull(branchDTOToTest);
        assertEquals(branchDTO.size(), branchDTOToTest.size());
    }

    @Test
    void testGetBranchById() {
        BranchDTO branchDTO = createBrancs().get(0);
        when(branchService.getBranchById(1L)).thenReturn(PublicInfoMapper.INSTANCE.convertToBranch(branchDTO));
        BranchDTO branchDTOToTest = branchController.getBranchById(1L);
        assertNotNull(branchDTO);
        assertNotNull(branchDTOToTest);
        assertEquals(branchDTO.getAddress(), branchDTOToTest.getAddress());
        assertEquals(branchDTO.getPhoneNumber(), branchDTOToTest.getPhoneNumber());
        assertEquals(branchDTO.getCity(), branchDTOToTest.getCity());
        assertEquals(branchDTO.getStartOfWork(), branchDTOToTest.getStartOfWork());
        assertEquals(branchDTO.getEndOfWork(), branchDTOToTest.getEndOfWork());
    }

    @Test
    void testCreateBranch() {
        Branch branch = new Branch(1L, "address", 123456L, "city", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"));
        branchController.createBranch(PublicInfoMapper.INSTANCE.convertToBranchDTO(branch));
        verify(branchService, times(1)).createBranch(any());
    }

    @Test
    void testUpdateBranch() {
        Branch branch = new Branch(1L, "address", 123456L, "city", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"));
        branchController.updateBranch(branch.getId(), PublicInfoMapper.INSTANCE.convertToBranchDTO(branch));
        verify(branchService, times(1)).updateBranch(any());
    }

    @Test
    void testDeleteBranch() {
        branchController.deleteBranch(any());
        verify(branchService, times(1)).deleteBranch(any());
    }

    List<BranchDTO> createBrancs() {
        branchDTO1 = new BranchDTO("address", 123456L, "city", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"));
        branchDTO2 = new BranchDTO("address", 123456L, "city", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"));
        return List.of(branchDTO1, branchDTO2);
    }
}