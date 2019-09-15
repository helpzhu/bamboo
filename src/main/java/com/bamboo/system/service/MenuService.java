package com.bamboo.system.service;

import com.bamboo.system.api.entity.MenuTreeVo;
import com.bamboo.system.condition.SelfMenuCondition;
import com.bamboo.system.domain.SelfMenu;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @date 2019/9/11 15:56
 * @since JDK1.8
 */
public interface MenuService {

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    String insertMenu(SelfMenu menu) throws Exception;

    /**
     * 批量添加菜单
     * @param selfMenuList
     * @return
     * @throws Exception
     */
    String insertMenuBatch(List<SelfMenu> selfMenuList) throws Exception;

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    String updateMenu(SelfMenu menu) throws Exception;

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    String deleteMenu(Long menuId) throws Exception;

    /**
     * 分页查询菜单
     *
     * @param condition
     * @return
     */
    Page<SelfMenu> getMenuPaging(SelfMenuCondition condition) throws Exception;

    /**
     * 查询所有菜单信息
     * @return
     */
    List<SelfMenu> findAll();

    /**
     * 获取菜单树
     * @return
     */
    Map<Long, MenuTreeVo> getMenuTree() throws Exception;
}
