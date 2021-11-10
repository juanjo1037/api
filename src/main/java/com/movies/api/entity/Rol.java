package com.movies.api.entity;

import com.movies.api.enums.RolName;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role",  schema = "cinema_manage")
@NoArgsConstructor
@Getter
@Setter
public class Rol {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
     @NotNull
     @Enumerated(EnumType.STRING)
    private RolName rolName;

    public Rol(@NotNull RolName rolName) {
        this.rolName = rolName;
    }
}
