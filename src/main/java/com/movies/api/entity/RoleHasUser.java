package com.movies.api.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Table(name = "role_has_user", indexes = {
        @Index(name = "fk_role_has_user_role1_idx", columnList = "role_id_role"),
        @Index(name = "fk_role_has_user_user1_idx", columnList = "user_id")
})
@Entity
public class RoleHasUser {
    @EmbeddedId
    private RoleHasUserId id;

    public RoleHasUserId getId() {
        return id;
    }

    public void setId(RoleHasUserId id) {
        this.id = id;
    }
}