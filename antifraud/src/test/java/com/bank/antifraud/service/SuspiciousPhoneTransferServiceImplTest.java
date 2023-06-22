package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDTO;
import com.bank.antifraud.entity.transfer.SuspiciousPhoneTransfer;
import com.bank.antifraud.mapper.SuspiciousPhoneTransferMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransferRepository;
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
class SuspiciousPhoneTransferServiceImplTest {

    @Mock
    private SuspiciousPhoneTransferMapper suspiciousPhoneTransferMapper;

    @Mock
    private SuspiciousPhoneTransferRepository suspiciousPhoneTransferRepository;

    @InjectMocks
    private SuspiciousPhoneTransferServiceImpl suspiciousPhoneTransferService;

    @Test
    void testGetAllSuspiciousPhonesTransfers() {
        List<SuspiciousPhoneTransfer> suspiciousPhonesTransfers = this.getAllSuspiciousPhonesTransfers();
        List<SuspiciousPhoneTransferDTO> expectedResult = this.getAllSuspiciousPhonesTransfersDTO();

        when(suspiciousPhoneTransferRepository.findAll()).thenReturn(suspiciousPhonesTransfers);
        doReturn(expectedResult).when(suspiciousPhoneTransferMapper).toDTOList(suspiciousPhonesTransfers);

        List<SuspiciousPhoneTransferDTO> actualResult =
                suspiciousPhoneTransferService.getAllSuspiciousPhoneTransfers();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(2);
        assertThat(actualResult).isEqualTo(expectedResult);
        verify(suspiciousPhoneTransferRepository, times(1)).findAll();
    }

    @Test
    void testGetEmptyListSuspiciousPhonesTransfers() {
        List<SuspiciousPhoneTransfer> emptyList = Collections.emptyList();
        List<SuspiciousPhoneTransferDTO> expectedResult = Collections.emptyList();

        when(suspiciousPhoneTransferRepository.findAll()).thenReturn(emptyList);
        doReturn(expectedResult).when(suspiciousPhoneTransferMapper).toDTOList(emptyList);

        List<SuspiciousPhoneTransferDTO> actualResult =
                suspiciousPhoneTransferService.getAllSuspiciousPhoneTransfers();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(0);
        assertThat(actualResult).isEqualTo(expectedResult);
        verify(suspiciousPhoneTransferRepository, times(1)).findAll();
    }

    @Test
    void testGetSuspiciousAccountTransferById() {
        SuspiciousPhoneTransfer suspiciousPhoneTransfer = this.getFirstSuspiciousPhoneTransfer();
        SuspiciousPhoneTransferDTO expectedSuspiciousPhoneTransferDTO = this.getFirstSuspiciousPhoneTransferDTO();

        when(suspiciousPhoneTransferRepository.findById(1L))
                .thenReturn(Optional.ofNullable(suspiciousPhoneTransfer));
        doReturn(expectedSuspiciousPhoneTransferDTO)
                .when(suspiciousPhoneTransferMapper).toDTO(suspiciousPhoneTransfer);

        SuspiciousPhoneTransferDTO actualSuspiciousPhoneTransferDTO =
                suspiciousPhoneTransferService.getSuspiciousPhoneTransferById(1L);

        assertThat(actualSuspiciousPhoneTransferDTO).isNotNull();
        assertThat(actualSuspiciousPhoneTransferDTO).isEqualTo(expectedSuspiciousPhoneTransferDTO);
        verify(suspiciousPhoneTransferRepository, times(1)).findById(1L);
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
    void testAddSuspiciousPhoneTransfer() {
        SuspiciousPhoneTransfer suspiciousPhoneTransfer = this.getFirstSuspiciousPhoneTransfer();
        SuspiciousPhoneTransfer savedSuspiciousPhoneTransfer = this.getFirstSuspiciousPhoneTransfer();
        SuspiciousPhoneTransferDTO expectedSuspiciousPhoneTransferDTO = this.getFirstSuspiciousPhoneTransferDTO();
        SuspiciousPhoneTransferDTO savedExpectedSuspiciousPhoneTransferDTO = this.getFirstSuspiciousPhoneTransferDTO();
        savedSuspiciousPhoneTransfer.setId(null);
        savedExpectedSuspiciousPhoneTransferDTO.setId(null);

        when(suspiciousPhoneTransferRepository.save(savedSuspiciousPhoneTransfer))
                .thenReturn(suspiciousPhoneTransfer);
        doReturn(savedSuspiciousPhoneTransfer)
                .when(suspiciousPhoneTransferMapper).toEntity(savedExpectedSuspiciousPhoneTransferDTO);
        doReturn(expectedSuspiciousPhoneTransferDTO)
                .when(suspiciousPhoneTransferMapper).toDTO(suspiciousPhoneTransfer);

        SuspiciousPhoneTransferDTO actualSuspiciousPhoneTransferDTO =
                suspiciousPhoneTransferService.addSuspiciousPhoneTransfer(savedExpectedSuspiciousPhoneTransferDTO);

        assertThat(actualSuspiciousPhoneTransferDTO).isNotNull();
        assertThat(actualSuspiciousPhoneTransferDTO.getId()).isEqualTo(1L);
        assertThat(actualSuspiciousPhoneTransferDTO).isEqualTo(expectedSuspiciousPhoneTransferDTO);
        verify(suspiciousPhoneTransferRepository, times(1)).save(savedSuspiciousPhoneTransfer);
    }

    @Test
    void testUpdateSuspiciousPhoneTransfer() {
        SuspiciousPhoneTransfer suspiciousPhoneTransfer = this.getFirstSuspiciousPhoneTransfer();
        SuspiciousPhoneTransferDTO expectedSuspiciousPhoneTransferDTO = this.getFirstSuspiciousPhoneTransferDTO();
        suspiciousPhoneTransfer.setIsBlocked(true);
        suspiciousPhoneTransfer.setBlockedReason("Что-то подозрительное");
        suspiciousPhoneTransfer.setSuspiciousReason("Слишком большая сумма");
        expectedSuspiciousPhoneTransferDTO.setIsBlocked(true);
        expectedSuspiciousPhoneTransferDTO.setBlockedReason("Что-то подозрительное");
        expectedSuspiciousPhoneTransferDTO.setSuspiciousReason("Слишком большая сумма");

        when(suspiciousPhoneTransferRepository.save(suspiciousPhoneTransfer))
                .thenReturn(suspiciousPhoneTransfer);
        when(suspiciousPhoneTransferRepository.findById(1L))
                .thenReturn(Optional.of(suspiciousPhoneTransfer));
        doReturn(suspiciousPhoneTransfer)
                .when(suspiciousPhoneTransferMapper).toEntity(expectedSuspiciousPhoneTransferDTO);
        doReturn(expectedSuspiciousPhoneTransferDTO)
                .when(suspiciousPhoneTransferMapper).toDTO(suspiciousPhoneTransfer);

        SuspiciousPhoneTransferDTO actualSuspiciousPhoneTransferDTO =
                suspiciousPhoneTransferService.updateSuspiciousPhoneTransfer(expectedSuspiciousPhoneTransferDTO);

        assertThat(actualSuspiciousPhoneTransferDTO).isNotNull();
        assertThat(actualSuspiciousPhoneTransferDTO.getIsBlocked()).isEqualTo(true);
        assertThat(actualSuspiciousPhoneTransferDTO.getBlockedReason()).isEqualTo("Что-то подозрительное");
        assertThat(actualSuspiciousPhoneTransferDTO.getSuspiciousReason()).isEqualTo("Слишком большая сумма");
        assertThat(actualSuspiciousPhoneTransferDTO).isEqualTo(expectedSuspiciousPhoneTransferDTO);
        verify(suspiciousPhoneTransferRepository, times(1)).save(suspiciousPhoneTransfer);
    }

    @Test
    void testDeleteSuspiciousPhoneTransferById() {
        SuspiciousPhoneTransfer suspiciousPhoneTransfer = this.getFirstSuspiciousPhoneTransfer();
        when(suspiciousPhoneTransferRepository.findById(1L))
                .thenReturn(Optional.of(suspiciousPhoneTransfer));

        suspiciousPhoneTransferService.deleteSuspiciousPhoneTransferById(1L);

        verify(suspiciousPhoneTransferRepository, times(1)).deleteById(1L);
    }

    private List<SuspiciousPhoneTransfer> getAllSuspiciousPhonesTransfers() {
        return List.of(this.getFirstSuspiciousPhoneTransfer(), this.getSecondSuspiciousAccountTransfer());
    }

    private SuspiciousPhoneTransfer getFirstSuspiciousPhoneTransfer() {
        return SuspiciousPhoneTransfer.builder()
                .id(1L)
                .phoneTransferId(100L)
                .isBlocked(false)
                .isSuspicious(true)
                .blockedReason(null)
                .suspiciousReason("Почему перевод по карте попал в antifraud")
                .build();
    }

    private SuspiciousPhoneTransfer getSecondSuspiciousAccountTransfer() {
        return SuspiciousPhoneTransfer.builder()
                .id(2L)
                .phoneTransferId(200L)
                .isBlocked(false)
                .isSuspicious(true)
                .blockedReason("Подозрительный перевод")
                .suspiciousReason("Причина, почему перевод по карте попал в antifraud")
                .build();
    }

    private List<SuspiciousPhoneTransferDTO> getAllSuspiciousPhonesTransfersDTO() {
        return Mappers.getMapper(SuspiciousPhoneTransferMapper.class)
                .toDTOList(this.getAllSuspiciousPhonesTransfers());
    }

    private SuspiciousPhoneTransferDTO getFirstSuspiciousPhoneTransferDTO() {
        return Mappers.getMapper(SuspiciousPhoneTransferMapper.class)
                .toDTO(this.getFirstSuspiciousPhoneTransfer());
    }

}