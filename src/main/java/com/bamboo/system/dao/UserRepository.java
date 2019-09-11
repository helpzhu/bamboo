package com.bamboo.system.dao;

import com.bamboo.system.domain.SelfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/4 15:06
 * @since JDK1.8
 */
@Repository
public interface UserRepository extends JpaRepository<SelfUser, Long>, JpaSpecificationExecutor{

    List<SelfUser> getUsersByUserAccount(String userAccount);
}
