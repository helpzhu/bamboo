package com.bamboo.system.service.impl;

import com.bamboo.constant.SelfConstant;
import com.bamboo.system.condition.SelfRoleCondition;
import com.bamboo.system.dao.RoleRepository;
import com.bamboo.system.domain.SelfRole;
import com.bamboo.system.service.RoleService;
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
import java.util.List;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/11 15:56
 * @since JDK1.8
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertRole(SelfRole role) throws Exception {
        try {
            List<SelfRole> selfRoleList = this.roleRepository.findAllByRoleName(role.getRoleName());
            if (CollectionUtils.isNotEmpty(selfRoleList)) {
                return "角色名称：" + role.getRoleName() + "已经存在，不能重复添加";
            }
            this.roleRepository.save(role);
            return SelfConstant.SUCCESS;
        } catch (Exception e) {
            logger.error("添加角色出错", e);
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateRole(SelfRole role) throws Exception {
        try {
            SelfRole selfRole = this.roleRepository.getOne(role.getRoleId());
            if (selfRole == null) {
                return "角色名称：" + role.getRoleName() + "不存在，请确认后再操作";
            }
            this.roleRepository.save(role);
            return SelfConstant.SUCCESS;
        } catch (Exception e) {
            logger.error("修改角色出错", e);
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteRole(Long roleId) throws Exception {
        try {
            SelfRole selfRole = this.roleRepository.getOne(roleId);
            if (null == selfRole) {
                return "当前角色不存在，请确认后重新操作";
            }
            this.roleRepository.deleteById(roleId);
            return SelfConstant.SUCCESS;
        } catch (Exception e) {
            logger.error("删除角色出错", e);
            throw e;
        }
    }

    @Override
    public Page<SelfRole> getRolePaging(SelfRoleCondition condition) {
        Specification specification = this.getSpecification(condition);
        return this.roleRepository.findAll(specification, PageRequest.of(condition.getPageNum(), condition.getPageSize()));
    }

    private Specification<SelfRole> getSpecification(SelfRoleCondition condition) {
        Specification specification = new Specification() {
            @Nullable
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (StringUtils.isNotBlank(condition.getRoleName())) {
                    predicateList.add(criteriaBuilder.equal(root.get("roleName"), condition.getRoleName()));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return specification;
    }

    @Override
    public List<SelfRole> findAll() {
        return this.roleRepository.findAll();
    }
}
