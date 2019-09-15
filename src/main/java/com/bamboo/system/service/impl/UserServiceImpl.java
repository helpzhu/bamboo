package com.bamboo.system.service.impl;

import com.bamboo.constant.SelfConstant;
import com.bamboo.system.condition.SelfUserCondition;
import com.bamboo.system.dao.UserRepository;
import com.bamboo.system.domain.SelfUser;
import com.bamboo.system.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/5 13:44
 * @since JDK1.8
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertUser(SelfUser user) throws Exception {
        try {
            SelfUser selfUser = this.getUserByUserAccount(user.getUserAccount());
            if (selfUser != null) {
                return "用户账号：" + user.getUserAccount() + "已经存在，不能重复添加";
            }
            user.setUuId(UUID.randomUUID().toString());
            user.setStatus(SelfConstant.NORMAL);
            user.setCreateTime(new Date());
            this.userRepository.save(user);
            return SelfConstant.SUCCESS;
        } catch (Exception e) {
            logger.error("添加用户出错", e);
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateUser(SelfUser user) throws Exception {
        try {
            SelfUser selfUser = this.userRepository.getOne(user.getUserId());
            if (selfUser == null) {
                return "用户账号：" + user.getUserAccount() + "不存在，无法修改";
            }
            user.setModifyTime(new Date());
            this.userRepository.save(user);
            return SelfConstant.SUCCESS;
        } catch (Exception e) {
            logger.error("修改用户出错", e);
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteUser(Long userId) throws Exception {
        try {
            SelfUser selfUser = this.userRepository.getOne(userId);
            if (selfUser == null) {
                return "用户账号不存在，请重新操作";
            }
            this.userRepository.deleteById(userId);
            return SelfConstant.SUCCESS;
        } catch (Exception e) {
            logger.error("删除用户出错", e);
            throw e;
        }
    }

    @Override
    public SelfUser getUserByUserAccount(String userAccount) {
        List<SelfUser> userList = this.userRepository.getUsersByUserAccount(userAccount);
        if (CollectionUtils.isNotEmpty(userList)) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public List<SelfUser> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Page<SelfUser> getUserPaging(SelfUserCondition condition) {
        // 封装查询条件
        Specification<SelfUser> specification = this.getSpecification(condition);
        return this.userRepository.findAll(specification, PageRequest.of(condition.getPageNum() - 1, condition.getPageSize()));
    }

    /**
     * 封装查询条件
     *
     * @param condition
     * @return
     */
    private Specification<SelfUser> getSpecification(SelfUserCondition condition) {
        Specification<SelfUser> specification = new Specification<SelfUser>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<SelfUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();

                if (StringUtils.isNotBlank(condition.getUserName())) {
                    predicateList.add(criteriaBuilder.equal(root.get("userName"), condition.getUserName()));
                }
                if (StringUtils.isNotBlank(condition.getUserAccount())) {
                    predicateList.add(criteriaBuilder.equal(root.get("userAccount"), condition.getUserAccount()));
                }
                if (StringUtils.isNotBlank(condition.getDeptName())) {
                    predicateList.add(criteriaBuilder.equal(root.get("deptName"), condition.getDeptName()));
                }
                if (StringUtils.isNotBlank(condition.getPhoneNumber())) {
                    predicateList.add(criteriaBuilder.equal(root.get("phoneNumber"), condition.getPhoneNumber()));
                }
                if (StringUtils.isNotBlank(condition.getEmail())) {
                    predicateList.add(criteriaBuilder.equal(root.get("email"), condition.getEmail()));
                }
                if (StringUtils.isNotBlank(condition.getStatus())) {
                    predicateList.add(criteriaBuilder.equal(root.get("status"), condition.getStatus()));
                }
                /*if (null != condition.getCreateTime()) {
                    predicateList.add(criteriaBuilder.equal(root.get("createTime"), condition.getCreateTime()));
                }
                if (null != condition.getModifyTime()) {
                    predicateList.add(criteriaBuilder.equal(root.get("modifyTime"), condition.getModifyTime()));
                }*/

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return specification;
    }
}
