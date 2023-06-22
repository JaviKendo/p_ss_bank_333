package com.bank.authorization.entity;

import com.bank.authorization.util.AuditUserListener;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@EntityListeners(AuditUserListener.class)
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role")
    @Size(min = 4, max = 40)
    @NotEmpty
    private String role;
    @Column(name = "profile_id")
    @Size(min = 2, max = 8)
    @NotEmpty
    private Long profileId;
    @Column(name = "password", unique = true)
    @Size(min = 2, max = 500)
    private String password;
}
