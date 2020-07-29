package com.sixkery.repository;

import com.sixkery.model.UserRole;
import com.sixkery.model.unionkey.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * 用户角色 DAO
 * </p>
 *
 */
public interface UserRoleDao extends JpaRepository<UserRole, UserRoleKey>, JpaSpecificationExecutor<UserRole> {

}
