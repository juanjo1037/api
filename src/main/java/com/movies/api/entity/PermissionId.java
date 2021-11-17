package com.movies.api.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PermissionId implements Serializable {
    private static final long serialVersionUID = 6489021462409984216L;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "role_id", nullable = false)
    private Long roleIdRole;

    public Long getRoleIdRole() {
        return roleIdRole;
    }

    public void setRoleIdRole(Long roleIdRole) {
        this.roleIdRole = roleIdRole;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleIdRole, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PermissionId entity = (PermissionId) o;
        return Objects.equals(this.roleIdRole, entity.roleIdRole) &&
                Objects.equals(this.userId, entity.userId);
    }
}