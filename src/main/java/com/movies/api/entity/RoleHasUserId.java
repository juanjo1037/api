package com.movies.api.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoleHasUserId implements Serializable {
    private static final long serialVersionUID = 1923851130068691164L;
    @Column(name = "role_id_role", nullable = false)
    private Long roleIdRole;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleIdRole() {
        return roleIdRole;
    }

    public void setRoleIdRole(Long roleIdRole) {
        this.roleIdRole = roleIdRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleIdRole, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoleHasUserId entity = (RoleHasUserId) o;
        return Objects.equals(this.roleIdRole, entity.roleIdRole) &&
                Objects.equals(this.userId, entity.userId);
    }
}