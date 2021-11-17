package com.movies.api.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Table(name = "permission", indexes = {
        @Index(name = "fk_user_has_role_role1_idx", columnList = "role_id"),
        @Index(name = "fk_user_has_role_user1_idx", columnList = "user_id")
})
@Entity
public class Permission {
    @EmbeddedId
    private PermissionId id;

    public PermissionId getId() {
        return id;
    }

    public void setId(PermissionId id) {
        this.id = id;
    }
}