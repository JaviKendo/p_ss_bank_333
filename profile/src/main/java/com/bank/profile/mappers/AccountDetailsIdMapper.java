package com.bank.profile.mappers;

import com.bank.profile.dto.AccountDetailsIdDTO;
import com.bank.profile.entity.AccountDetailsId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountDetailsIdMapper {
    AccountDetailsIdDTO toDTO(AccountDetailsId accountDetailsId);
    AccountDetailsId toEntity(AccountDetailsIdDTO accountDetailsIdDTO);
}
