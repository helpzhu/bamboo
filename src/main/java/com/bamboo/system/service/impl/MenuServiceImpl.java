package com.bamboo.system.service.impl;

import com.bamboo.constant.SelfConstant;
import com.bamboo.system.condition.SelfMenuCondition;
import com.bamboo.system.dao.MenuRepository;
import com.bamboo.system.domain.SelfMenu;
import com.bamboo.system.service.MenuService;
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
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuRepository menuRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertMenu(SelfMenu menu) throws Exception {
        try {
            List<SelfMenu> selfMenuList = this.menuRepository.findAllByMenuUrl(menu.getMenuUrl());
            if (CollectionUtils.isNotEmpty(selfMenuList)) {
                return "菜单名称：" + menu.getMenuName() + "，菜单URL：" + menu.getMenuUrl() + "已经存在，不能重复添加";
            }
            this.menuRepository.save(menu);
            return SelfConstant.SUCCESS;
        } catch (Exception e) {
            logger.error("添加菜单出错", e);
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateMenu(SelfMenu menu) throws Exception {
        try {
            SelfMenu selfMenu = this.menuRepository.getOne(menu.getMenuId());
            if (selfMenu == null) {
                return "不存在菜单：" + menu.getMenuName() + "，请确认后在操作";
            }
            this.menuRepository.save(menu);
            return SelfConstant.SUCCESS;
        } catch (Exception e) {
            logger.error("修改菜单出错", e);
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteMenu(Long menuId) throws Exception {
        try {
            SelfMenu selfMenu = this.menuRepository.getOne(menuId);
            if (selfMenu == null) {
                return "当前菜单不存在，请确认后再操作";
            }
            this.menuRepository.deleteById(menuId);
            return SelfConstant.SUCCESS;
        } catch (Exception e) {
            logger.error("删除菜单出错", e);
            throw e;
        }
    }

    @Override
    public Page<SelfMenu> getMenuPaging(SelfMenuCondition condition) {
        Specification<SelfMenu> specification = this.getSpecification(condition);
        return this.menuRepository.findAll(specification, PageRequest.of(condition.getPageNum(), condition.getPageSize()));
    }

    private Specification<SelfMenu> getSpecification(SelfMenuCondition condition) {
        Specification<SelfMenu> specification = new Specification<SelfMenu>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<SelfMenu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (StringUtils.isNotBlank(condition.getMenuName())) {
                    predicateList.add(criteriaBuilder.equal(root.get("menuName"), condition.getMenuName()));
                }
                if (StringUtils.isNotBlank(condition.getMenuUrl())) {
                    predicateList.add(criteriaBuilder.equal(root.get("menuUrl"), condition.getMenuUrl()));
                }
                if (StringUtils.isNotBlank(condition.getType())) {
                    predicateList.add(criteriaBuilder.equal(root.get("type"), condition.getType()));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return specification;
    }

    @Override
    public List<SelfMenu> findAll() {
        return this.menuRepository.findAll();
    }
}
