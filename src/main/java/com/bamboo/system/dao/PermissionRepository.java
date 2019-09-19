package com.bamboo.system.dao;

import com.bamboo.system.domain.SelfMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019-09-11 22:03
 * @since JDK1.8
 */
@Repository
public interface PermissionRepository extends JpaRepository<SelfMenu,  Long> {

    @Query(value = "select sm.menu_url from self_menu as sm " +
            "left join role_menu as rm on sm.menu_id = rm.menu_id " +
            "left join self_role as sr on rm.role_id = sr.role_id " +
            "left join user_role as ur on sr.role_id = ur.role_id " +
            "left join self_user as su on ur.user_id = su.user_id " +
            "where su.user_account = :userAccount and sm.menu_url <> ''", nativeQuery = true)
    List<String> findAllPermissionByUserAccount(@Param("userAccount") String userAccount);
}
