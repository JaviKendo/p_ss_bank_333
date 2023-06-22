package com.bank.profile.mappers;

import com.bank.profile.dto.RegistrationDTO;
import com.bank.profile.entity.Registration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    RegistrationDTO toDTO(Registration registration);
    Registration toEntity(RegistrationDTO registrationDTO);
}
