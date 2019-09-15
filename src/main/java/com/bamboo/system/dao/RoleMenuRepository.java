package com.bamboo.system.dao;

import com.bamboo.system.domain.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/12 11:36
 * @since JDK1.8
 */
@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {
}
