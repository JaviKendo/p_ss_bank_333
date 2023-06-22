package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousAccountTransferDTO;
import com.bank.antifraud.entity.transfer.SuspiciousAccountTransfer;
import com.bank.antifraud.mapper.SuspiciousAccountTransferMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SuspiciousAccountTransferServiceImplTest {

    @Mock
    private SuspiciousAccountTransferMapper suspiciousAccountTransferMapper;

    @Mock
    private SuspiciousAccountTransferRepository suspiciousAccountTransferRepository;

    @InjectMocks
    private SuspiciousAccountTransferServiceImpl suspiciousAccountTransferService;

    @Test
    void testGetAllSuspiciousAccountsTransfers() {
        List<SuspiciousAccountTransfer> suspiciousAccountsTransfers = this.getAllSuspiciousAccountsTransfers();
        List<SuspiciousAccountTransferDTO> expectedResult = this.getAllSuspiciousAccountsTransfersDTO();

        when(suspiciousAccountTransferRepository.findAll()).thenReturn(suspiciousAccountsTransfers);
        doReturn(expectedResult).when(suspiciousAccountTransferMapper).toDTOList(suspiciousAccountsTransfers);

        List<SuspiciousAccountTransferDTO> actualResult =
                suspiciousAccountTransferService.getAllSuspiciousAccountTransfers();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(2);
        assertThat(actualResult).isEqualTo(expectedResult);
        verify(suspiciousAccountTransferRepository, times(1)).findAll();
    }

    @Test
    void testGetEmptyListSuspiciousAccountsTransfers() {
        List<SuspiciousAccountTransfer> emptyList = Collections.emptyList();
        List<SuspiciousAccountTransferDTO> expectedResult = Collections.emptyList();

        when(suspiciousAccountTransferRepository.findAll()).thenReturn(emptyList);
        doReturn(expectedResult).when(suspiciousAccountTransferMapper).toDTOList(emptyList);

        List<SuspiciousAccountTransferDTO> actualResult =
                suspiciousAccountTransferService.getAllSuspiciousAccountTransfers();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(0);
        assertThat(actualResult).isEqualTo(expectedResult);
        verify(suspiciousAccountTransferRepository, times(1)).findAll();
    }

    @Test
    void testGetSuspiciousAccountTransferById() {
        SuspiciousAccountTransfer suspiciousAccountTransfer = this.getFirstSuspiciousAccountTransfer();
        SuspiciousAccountTransferDTO expectedSuspiciousAccountTransferDTO = this.getFirstSuspiciousAccountTransferDTO();

        when(suspiciousAccountTransferRepository.findById(1L))
                .thenReturn(Optional.ofNullable(suspiciousAccountTransfer));
        doReturn(expectedSuspiciousAccountTransferDTO)
                .when(suspiciousAccountTransferMapper).toDTO(suspiciousAccountTransfer);

        SuspiciousAccountTransferDTO actualSuspiciousAccountTransferDTO =
                suspiciousAccountTransferService.getSuspiciousAccountTransferById(1L);

        assertThat(actualSuspiciousAccountTransferDTO).isNotNull();
        assertThat(actualSuspiciousAccountTransferDTO).isEqualTo(expectedSuspiciousAccountTransferDTO);
        verify(suspiciousAccountTransferRepository, times(1)).findById(1L);
    }

//    @Test
//    void testIfSuspiciousAccountTransferByIdNotFound() {
//        SuspiciousAccountTransfer suspiciousAccountTransfer = this.getFirstSuspiciousAccountTransfer();
//        SuspiciousAccountTransferDTO expectedSuspiciousAccountTransferDTO = this.getFirstSuspiciousAccountTransferDTO();
//
//        when(suspiciousAccountTransferRepository.findById(anyLong()))
//                .thenReturn(Optional.empty());
////        doReturn(expectedSuspiciousAccountTransferDTO)
////                .when(suspiciousAccountTransferMapper).toDTO(suspiciousAccountTransfer);
//
//        org.junit.jupiter.api.Assertions.assertThrows(NoSuchTransferException.class, () -> {
//            suspiciousAccountTransferService.addSuspiciousAccountTransfer(expectedSuspiciousAccountTransferDTO);
//        });
//
//        verify(suspiciousAccountTransferRepository, never()).findById(anyLong());
//
////        given(employeeRepository.findByEmail(employee.getEmail()))
////                .willReturn(Optional.of(employee));
////
////        System.out.println(employeeRepository);
////        System.out.println(employeeService);
////
////        // when -  action or the behaviour that we are going test
////        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
////            employeeService.saveEmployee(employee);
////        });
////
////        // then
////        verify(employeeRepository, never()).save(any(Employee.class));
//
//
//
////        SuspiciousAccountTransfer suspiciousAccountTransfer = this.getFirstSuspiciousAccountTransfer();
////        SuspiciousAccountTransferDTO expectedSuspiciousAccountTransferDTO = this.getFirstSuspiciousAccountTransferDTO();
////
////        when(suspiciousAccountTransferRepository.findById(1L))
////                .thenReturn(Optional.ofNullable(suspiciousAccountTransfer));
////        doReturn(expectedSuspiciousAccountTransferDTO)
////                .when(suspiciousAccountTransferMapper).toDTO(suspiciousAccountTransfer);
////
////        SuspiciousAccountTransferDTO actualSuspiciousAccountTransferDTO =
////                suspiciousAccountTransferService.getSuspiciousAccountTransferById(1L);
////
////        assertThat(actualSuspiciousAccountTransferDTO).isNotNull();
////        assertThat(actualSuspiciousAccountTransferDTO).isEqualTo(expectedSuspiciousAccountTransferDTO);
////        verify(suspiciousAccountTransferRepository, times(1)).findById(1L);
//    }

    @Test
    void testAddSuspiciousAccountTransfer() {
        SuspiciousAccountTransfer suspiciousAccountTransfer = this.getFirstSuspiciousAccountTransfer();
        SuspiciousAccountTransfer savedSuspiciousAccountTransfer = this.getFirstSuspiciousAccountTransfer();
        SuspiciousAccountTransferDTO expectedSuspiciousAccountTransferDTO = this.getFirstSuspiciousAccountTransferDTO();
        SuspiciousAccountTransferDTO savedExpectedSuspiciousAccountTransferDTO = this.getFirstSuspiciousAccountTransferDTO();
        savedSuspiciousAccountTransfer.setId(null);
        savedExpectedSuspiciousAccountTransferDTO.setId(null);

        when(suspiciousAccountTransferRepository.save(savedSuspiciousAccountTransfer))
                .thenReturn(suspiciousAccountTransfer);
        doReturn(savedSuspiciousAccountTransfer)
                .when(suspiciousAccountTransferMapper).toEntity(savedExpectedSuspiciousAccountTransferDTO);
        doReturn(expectedSuspiciousAccountTransferDTO)
                .when(suspiciousAccountTransferMapper).toDTO(suspiciousAccountTransfer);

        SuspiciousAccountTransferDTO actualSuspiciousAccountTransferDTO =
                suspiciousAccountTransferService.addSuspiciousAccountTransfer(savedExpectedSuspiciousAccountTransferDTO);

        assertThat(actualSuspiciousAccountTransferDTO).isNotNull();
        assertThat(actualSuspiciousAccountTransferDTO.getId()).isEqualTo(1L);
        assertThat(actualSuspiciousAccountTransferDTO).isEqualTo(expectedSuspiciousAccountTransferDTO);
        verify(suspiciousAccountTransferRepository, times(1)).save(savedSuspiciousAccountTransfer);
    }

    @Test
    void testUpdateSuspiciousAccountTransfer() {
        SuspiciousAccountTransfer suspiciousAccountTransfer = this.getFirstSuspiciousAccountTransfer();
        SuspiciousAccountTransferDTO expectedSuspiciousAccountTransferDTO = this.getFirstSuspiciousAccountTransferDTO();
        suspiciousAccountTransfer.setIsBlocked(true);
        suspiciousAccountTransfer.setBlockedReason("Что-то подозрительное");
        suspiciousAccountTransfer.setSuspiciousReason("Слишком большая сумма");
        expectedSuspiciousAccountTransferDTO.setIsBlocked(true);
        expectedSuspiciousAccountTransferDTO.setBlockedReason("Что-то подозрительное");
        expectedSuspiciousAccountTransferDTO.setSuspiciousReason("Слишком большая сумма");

        when(suspiciousAccountTransferRepository.save(suspiciousAccountTransfer))
                .thenReturn(suspiciousAccountTransfer);
        when(suspiciousAccountTransferRepository.findById(1L))
                .thenReturn(Optional.of(suspiciousAccountTransfer));
        doReturn(suspiciousAccountTransfer)
                .when(suspiciousAccountTransferMapper).toEntity(expectedSuspiciousAccountTransferDTO);
        doReturn(expectedSuspiciousAccountTransferDTO)
                .when(suspiciousAccountTransferMapper).toDTO(suspiciousAccountTransfer);

        SuspiciousAccountTransferDTO actualSuspiciousAccountTransferDTO =
                suspiciousAccountTransferService.updateSuspiciousAccountTransfer(expectedSuspiciousAccountTransferDTO);

        assertThat(actualSuspiciousAccountTransferDTO).isNotNull();
        assertThat(actualSuspiciousAccountTransferDTO.getIsBlocked()).isEqualTo(true);
        assertThat(actualSuspiciousAccountTransferDTO.getBlockedReason()).isEqualTo("Что-то подозрительное");
        assertThat(actualSuspiciousAccountTransferDTO.getSuspiciousReason()).isEqualTo("Слишком большая сумма");
        assertThat(actualSuspiciousAccountTransferDTO).isEqualTo(expectedSuspiciousAccountTransferDTO);
        verify(suspiciousAccountTransferRepository, times(1)).save(suspiciousAccountTransfer);
    }

    @Test
    void testDeleteSuspiciousAccountTransferById() {
        SuspiciousAccountTransfer suspiciousAccountTransfer = this.getFirstSuspiciousAccountTransfer();
        when(suspiciousAccountTransferRepository.findById(1L))
                .thenReturn(Optional.of(suspiciousAccountTransfer));

        suspiciousAccountTransferService.deleteSuspiciousAccountTransferById(1L);

        verify(suspiciousAccountTransferRepository, times(1)).deleteById(1L);
    }

    private List<SuspiciousAccountTransfer> getAllSuspiciousAccountsTransfers() {
        return List.of(this.getFirstSuspiciousAccountTransfer(), this.getSecondSuspiciousAccountTransfer());
    }

    private SuspiciousAccountTransfer getFirstSuspiciousAccountTransfer() {
        return SuspiciousAccountTransfer.builder()
                .id(1L)
                .accountTransferId(100L)
                .isBlocked(false)
                .isSuspicious(true)
                .blockedReason(null)
                .suspiciousReason("Почему перевод по счёту попал в antifraud")
                .build();
    }

    private SuspiciousAccountTransfer getSecondSuspiciousAccountTransfer() {
        return SuspiciousAccountTransfer.builder()
                .id(2L)
                .accountTransferId(200L)
                .isBlocked(false)
                .isSuspicious(true)
                .blockedReason("Подозрительный перевод")
                .suspiciousReason("Причина, почему перевод по счёту попал в antifraud")
                .build();
    }

    private List<SuspiciousAccountTransferDTO> getAllSuspiciousAccountsTransfersDTO() {
        return Mappers.getMapper(SuspiciousAccountTransferMapper.class)
                .toDTOList(this.getAllSuspiciousAccountsTransfers());
    }

    private SuspiciousAccountTransferDTO getFirstSuspiciousAccountTransferDTO() {
        return Mappers.getMapper(SuspiciousAccountTransferMapper.class)
                .toDTO(this.getFirstSuspiciousAccountTransfer());
    }

}