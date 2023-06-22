package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.repository.BranchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.sql.Time;
import java.util.List;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BranchServiceImplTest {
    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchServiceImpl branchService;

    private Branch branch1;
    private Branch branch2;

    @Test
    void shouldReturnAllBranch() {
        when(branchRepository.findAll()).thenReturn(createBrancs());
        List<Branch> result = branchService.getAllBranch();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(branch1, result.get(0));
        assertEquals(branch2, result.get(1));
    }

    @Test
    void shouldReturnBranchById() {
        when(branchRepository.findById(1L)).thenReturn(of(createBrancs().get(0)));
        Branch result = branchService.getBranchById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void findById_notFound() {
        when(branchRepository.findById(1L)).thenReturn(empty());
        assertThrows(EntityNotFoundException.class, () -> branchService.getBranchById(1L));
    }

    @Test
    void shouldSaveToRepository() {
        branchService.createBranch(any());
        verify(branchRepository, times(1)).save(any());
    }

    @Test
    void shouldUpdateEntity() {
        Branch branch = new Branch(1L, "address", 123456L, "city", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"));
        when(branchRepository.save(branch)).thenReturn(branch);
        branchService.updateBranch(branch);
        verify(branchRepository, times(1)).save(branch);
    }

    @Test
    void shouldDeleteToRepository() {
        branchService.deleteBranch(any());
        verify(branchRepository, times(1)).deleteById(any());
    }

    List<Branch> createBrancs() {
        branch1 = new Branch(1L, "address", 123456L, "city", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"));
        branch2 = new Branch(2L, "address", 123456L, "city", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"));
        return List.of(branch1, branch2);
    }
}