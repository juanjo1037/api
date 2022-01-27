package com.movies.api.entity;

import com.movies.api.enums.RolName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Table(name = "role")
@Entity
@Getter
@Setter
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false, length = 45)
    private RolName roleName;

    public Role() {

    }


}