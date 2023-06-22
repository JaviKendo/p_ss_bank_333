package com.bank.profile.mappers;

import com.bank.profile.dto.ProfileDTO;
import com.bank.profile.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDTO toDTO(Profile profile);
    Profile toEntity(ProfileDTO profileDTO);
}
