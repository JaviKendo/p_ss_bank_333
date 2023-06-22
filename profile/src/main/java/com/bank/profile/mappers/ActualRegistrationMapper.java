package com.bank.profile.mappers;

import com.bank.profile.dto.ActualRegistrationDTO;
import com.bank.profile.entity.ActualRegistration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActualRegistrationMapper {
    ActualRegistrationDTO toDTO(ActualRegistration actualRegistration);
    ActualRegistration toEntity(ActualRegistrationDTO actualRegistrationDTO);
}
