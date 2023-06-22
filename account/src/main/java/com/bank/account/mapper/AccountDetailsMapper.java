package com.bank.account.mapper;

import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.entity.AccountDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
/**
 * Интерфейс-маппер для преобразования объектов между классом AccountDetails и AccountDetailsDTO.
 * Использует библиотеку MapStruct для автоматического создания реализации маппера.
 */
@Mapper
public interface AccountDetailsMapper {
    AccountDetailsMapper INSTANCE = Mappers.getMapper(AccountDetailsMapper.class);
    /**
     * Преобразует объект AccountDetails в объект AccountDetailsDTO.
     * @param details объект AccountDetails для преобразования
     * @return преобразованный объект AccountDetailsDTO
     */
    AccountDetailsDTO toDTO(AccountDetails details);
    /**
     * Преобразует объект AccountDetailsDTO в объект AccountDetails.
     * @param detailsDTO объект AccountDetailsDTO для преобразования
     * @return преобразованный объект AccountDetails
     */
    AccountDetails toEntity(AccountDetailsDTO detailsDTO);
}


