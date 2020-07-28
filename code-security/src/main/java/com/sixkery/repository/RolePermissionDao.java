package com.sixkery.repository;

import com.sixkery.model.RolePermission;
import com.sixkery.model.unionkey.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * 角色-权限 DAO
 * </p>
 *
 * @author sixkery

 */
public interface RolePermissionDao extends JpaRepository<RolePermission, RolePermissionKey>, JpaSpecificationExecutor<RolePermission> {
}
