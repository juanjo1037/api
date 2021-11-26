package com.movies.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.movies.api.enums.RolName;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role",  schema = "cinema_manage")
@NoArgsConstructor

public class Role  {



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
     @NotNull
     @Column(name = "role_name")
     @Enumerated(EnumType.STRING)
     private RolName rolName;



    public Role(@NotNull RolName rolName) {
        this.rolName = rolName;
    }

    public RolName getRolName() {
        return rolName;
    }

    public void setRolName(RolName rolName) {
        this.rolName = rolName;
    }



}
