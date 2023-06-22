package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.entity.transfer.SuspiciousCardTransfer;
import com.bank.antifraud.mapper.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
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
class SuspiciousCardTransferServiceImplTest {

    @Mock
    private SuspiciousCardTransferMapper suspiciousCardTransferMapper;

    @Mock
    private SuspiciousCardTransferRepository suspiciousCardTransferRepository;

    @InjectMocks
    private SuspiciousCardTransferServiceImpl suspiciousCardTransferService;

    @Test
    void testGetAllSuspiciousCardsTransfers() {
        List<SuspiciousCardTransfer> suspiciousCardsTransfers = this.getAllSuspiciousCardsTransfers();
        List<SuspiciousCardTransferDTO> expectedResult = this.getAllSuspiciousCardsTransfersDTO();

        when(suspiciousCardTransferRepository.findAll()).thenReturn(suspiciousCardsTransfers);
        doReturn(expectedResult).when(suspiciousCardTransferMapper).toDTOList(suspiciousCardsTransfers);

        List<SuspiciousCardTransferDTO> actualResult =
                suspiciousCardTransferService.getAllSuspiciousCardTransfers();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(2);
        assertThat(actualResult).isEqualTo(expectedResult);
        verify(suspiciousCardTransferRepository, times(1)).findAll();
    }

    @Test
    void testGetEmptyListSuspiciousCardsTransfers() {
        List<SuspiciousCardTransfer> emptyList = Collections.emptyList();
        List<SuspiciousCardTransferDTO> expectedResult = Collections.emptyList();

        when(suspiciousCardTransferRepository.findAll()).thenReturn(emptyList);
        doReturn(expectedResult).when(suspiciousCardTransferMapper).toDTOList(emptyList);

        List<SuspiciousCardTransferDTO> actualResult =
                suspiciousCardTransferService.getAllSuspiciousCardTransfers();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(0);
        assertThat(actualResult).isEqualTo(expectedResult);
        verify(suspiciousCardTransferRepository, times(1)).findAll();
    }

    @Test
    void testGetSuspiciousAccountTransferById() {
        SuspiciousCardTransfer suspiciousCardTransfer = this.getFirstSuspiciousCardTransfer();
        SuspiciousCardTransferDTO expectedSuspiciousCardTransferDTO = this.getFirstSuspiciousCardTransferDTO();

        when(suspiciousCardTransferRepository.findById(1L))
                .thenReturn(Optional.ofNullable(suspiciousCardTransfer));
        doReturn(expectedSuspiciousCardTransferDTO)
                .when(suspiciousCardTransferMapper).toDTO(suspiciousCardTransfer);

        SuspiciousCardTransferDTO actualSuspiciousCardTransferDTO =
                suspiciousCardTransferService.getSuspiciousCardTransferById(1L);

        assertThat(actualSuspiciousCardTransferDTO).isNotNull();
        assertThat(actualSuspiciousCardTransferDTO).isEqualTo(expectedSuspiciousCardTransferDTO);
        verify(suspiciousCardTransferRepository, times(1)).findById(1L);
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
    void testAddSuspiciousCardTransfer() {
        SuspiciousCardTransfer suspiciousCardTransfer = this.getFirstSuspiciousCardTransfer();
        SuspiciousCardTransfer savedSuspiciousCardTransfer = this.getFirstSuspiciousCardTransfer();
        SuspiciousCardTransferDTO expectedSuspiciousCardTransferDTO = this.getFirstSuspiciousCardTransferDTO();
        SuspiciousCardTransferDTO savedExpectedSuspiciousCardTransferDTO = this.getFirstSuspiciousCardTransferDTO();
        savedSuspiciousCardTransfer.setId(null);
        savedExpectedSuspiciousCardTransferDTO.setId(null);

        when(suspiciousCardTransferRepository.save(savedSuspiciousCardTransfer))
                .thenReturn(suspiciousCardTransfer);
        doReturn(savedSuspiciousCardTransfer)
                .when(suspiciousCardTransferMapper).toEntity(savedExpectedSuspiciousCardTransferDTO);
        doReturn(expectedSuspiciousCardTransferDTO)
                .when(suspiciousCardTransferMapper).toDTO(suspiciousCardTransfer);

        SuspiciousCardTransferDTO actualSuspiciousCardTransferDTO =
                suspiciousCardTransferService.addSuspiciousCardTransfer(savedExpectedSuspiciousCardTransferDTO);

        assertThat(actualSuspiciousCardTransferDTO).isNotNull();
        assertThat(actualSuspiciousCardTransferDTO.getId()).isEqualTo(1L);
        assertThat(actualSuspiciousCardTransferDTO).isEqualTo(expectedSuspiciousCardTransferDTO);
        verify(suspiciousCardTransferRepository, times(1)).save(savedSuspiciousCardTransfer);
    }

    @Test
    void testUpdateSuspiciousCardTransfer() {
        SuspiciousCardTransfer suspiciousCardTransfer = this.getFirstSuspiciousCardTransfer();
        SuspiciousCardTransferDTO expectedSuspiciousCardTransferDTO = this.getFirstSuspiciousCardTransferDTO();
        suspiciousCardTransfer.setIsBlocked(true);
        suspiciousCardTransfer.setBlockedReason("Что-то подозрительное");
        suspiciousCardTransfer.setSuspiciousReason("Слишком большая сумма");
        expectedSuspiciousCardTransferDTO.setIsBlocked(true);
        expectedSuspiciousCardTransferDTO.setBlockedReason("Что-то подозрительное");
        expectedSuspiciousCardTransferDTO.setSuspiciousReason("Слишком большая сумма");

        when(suspiciousCardTransferRepository.save(suspiciousCardTransfer))
                .thenReturn(suspiciousCardTransfer);
        when(suspiciousCardTransferRepository.findById(1L))
                .thenReturn(Optional.of(suspiciousCardTransfer));
        doReturn(suspiciousCardTransfer)
                .when(suspiciousCardTransferMapper).toEntity(expectedSuspiciousCardTransferDTO);
        doReturn(expectedSuspiciousCardTransferDTO)
                .when(suspiciousCardTransferMapper).toDTO(suspiciousCardTransfer);

        SuspiciousCardTransferDTO actualSuspiciousCardTransferDTO =
                suspiciousCardTransferService.updateSuspiciousCardTransfer(expectedSuspiciousCardTransferDTO);

        assertThat(actualSuspiciousCardTransferDTO).isNotNull();
        assertThat(actualSuspiciousCardTransferDTO.getIsBlocked()).isEqualTo(true);
        assertThat(actualSuspiciousCardTransferDTO.getBlockedReason()).isEqualTo("Что-то подозрительное");
        assertThat(actualSuspiciousCardTransferDTO.getSuspiciousReason()).isEqualTo("Слишком большая сумма");
        assertThat(actualSuspiciousCardTransferDTO).isEqualTo(expectedSuspiciousCardTransferDTO);
        verify(suspiciousCardTransferRepository, times(1)).save(suspiciousCardTransfer);
    }

    @Test
    void testDeleteSuspiciousCardTransferById() {
        SuspiciousCardTransfer suspiciousCardTransfer = this.getFirstSuspiciousCardTransfer();
        when(suspiciousCardTransferRepository.findById(1L))
                .thenReturn(Optional.of(suspiciousCardTransfer));

        suspiciousCardTransferService.deleteSuspiciousCardTransferById(1L);

        verify(suspiciousCardTransferRepository, times(1)).deleteById(1L);
    }

    private List<SuspiciousCardTransfer> getAllSuspiciousCardsTransfers() {
        return List.of(this.getFirstSuspiciousCardTransfer(), this.getSecondSuspiciousAccountTransfer());
    }

    private SuspiciousCardTransfer getFirstSuspiciousCardTransfer() {
        return SuspiciousCardTransfer.builder()
                .id(1L)
                .cardTransferId(100L)
                .isBlocked(false)
                .isSuspicious(true)
                .blockedReason(null)
                .suspiciousReason("Почему перевод по карте попал в antifraud")
                .build();
    }

    private SuspiciousCardTransfer getSecondSuspiciousAccountTransfer() {
        return SuspiciousCardTransfer.builder()
                .id(2L)
                .cardTransferId(200L)
                .isBlocked(false)
                .isSuspicious(true)
                .blockedReason("Подозрительный перевод")
                .suspiciousReason("Причина, почему перевод по карте попал в antifraud")
                .build();
    }

    private List<SuspiciousCardTransferDTO> getAllSuspiciousCardsTransfersDTO() {
        return Mappers.getMapper(SuspiciousCardTransferMapper.class)
                .toDTOList(this.getAllSuspiciousCardsTransfers());
    }

    private SuspiciousCardTransferDTO getFirstSuspiciousCardTransferDTO() {
        return Mappers.getMapper(SuspiciousCardTransferMapper.class)
                .toDTO(this.getFirstSuspiciousCardTransfer());
    }

}