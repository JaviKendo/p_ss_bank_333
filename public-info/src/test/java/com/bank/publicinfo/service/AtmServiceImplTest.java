package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.repository.AtmRepository;
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
class AtmServiceImplTest {
    @Mock
    private AtmRepository atmRepository;

    @InjectMocks
    private AtmServiceImpl atmService;

    private Atm atm1;
    private Atm atm2;

    @Test
    void shouldReturnAllAtms() {
        when(atmRepository.findAll()).thenReturn(createAtms());
        List<Atm> result = atmService.getAllAtms();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(atm1, result.get(0));
        assertEquals(atm2, result.get(1));
    }

    @Test
    void shouldReturnAtmById() {
        when(atmRepository.findById(1L)).thenReturn(of(createAtms().get(0)));
        Atm result = atmService.getAtmById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void findById_notFound() {
        when(atmRepository.findById(1L)).thenReturn(empty());
        assertThrows(EntityNotFoundException.class, () -> atmService.getAtmById(1L));
    }

    @Test
    void shouldSaveToRepository() {
        atmService.createAtm(any());
        verify(atmRepository, times(1)).save(any());
    }

    @Test
    void shouldUpdateEntity() {
        Atm atm = new Atm(1L, "address", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"), true , new Branch());
        when(atmRepository.save(atm)).thenReturn(atm);
        atmService.updateAtm(atm);
        verify(atmRepository, times(1)).save(atm);
    }

    @Test
    public void shouldDeleteToRepository() {
        atmService.deleteAtm(any());
        verify(atmRepository, times(1)).deleteById(any());
    }


    List<Atm> createAtms() {
        atm1 = new Atm(1L, "address", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"), true , new Branch());
        atm2 = new Atm(2L, "address", Time.valueOf("08:00:00"), Time.valueOf("20:00:00"), true , new Branch());
        return List.of(atm1, atm2);
    }
}