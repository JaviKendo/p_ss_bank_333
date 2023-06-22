package com.bank.profile.mappers;

import com.bank.profile.dto.PassportDTO;
import com.bank.profile.entity.Passport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    PassportDTO toDTO(Passport passport);
    Passport toEntity(PassportDTO passportDTO);
}
