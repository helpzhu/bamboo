package com.bamboo.system.dao;

import com.bamboo.system.domain.SelfRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @date 2019/9/11 15:44
 * @since JDK1.8
 */
@Repository
public interface RoleRepository extends JpaRepository<SelfRole, Long>, JpaSpecificationExecutor {

    List<SelfRole> findAllByRoleName(String roleName);
}
