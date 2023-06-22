package com.bank.profile.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AccountDetailsIdDTO {

    private Long id;

    @NotNull(message = "Поле не должно быть пустым")
    private Long accountId;

    @NotNull(message = "Поле не должно быть пустым")
    private ProfileDTO profileId;
}
