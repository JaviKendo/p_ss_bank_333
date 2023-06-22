package com.bank.authorization.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "UsersDTO", description = "DTO объект для сущности UsersDetails")
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"password"})
        }
)

public class UserDTO {
    private Long id;
    private String role;
    private Long profileId;
    private String password;
}
