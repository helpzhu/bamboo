package com.bamboo.system.service;

import com.bamboo.system.condition.SelfMenuCondition;
import com.bamboo.system.domain.SelfMenu;
import org.springframework.data.domain.Page;

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
    String insertMenu(SelfMenu menu);

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    String updateMenu(SelfMenu menu);

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    String deleteMenu(Long menuId);

    /**
     * 分页查询菜单
     *
     * @param condition
     * @return
     */
    Page<SelfMenu> getMenuPaging(SelfMenuCondition condition);
}
