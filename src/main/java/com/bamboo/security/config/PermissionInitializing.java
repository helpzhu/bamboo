package com.bamboo.security.config;

import com.bamboo.constant.SelfConstant;
import com.bamboo.system.domain.*;
import com.bamboo.system.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author bamboo
 * @version 1.0
 * @desc
 * @date 2019/9/12 11:34
 * @since JDK1.8
 */
public class PermissionInitializing {

    private static final Logger logger = LoggerFactory.getLogger(PermissionInitializing.class);

    private static final String ADMIN = "admin";
    private static final String TEST = "test";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    protected void postInit() throws Exception {

        this.insertUser();

        this.insertRole();

        this.insertMenu();

        this.initPermission();
    }

    private void insertUser() throws Exception {
        try {
            SelfUser selfUser = new SelfUser(UUID.randomUUID().toString(), ADMIN, ADMIN, passwordEncoder.encode(ADMIN),
                    1L, ADMIN, null, null, SelfConstant.NORMAL, new Date(), null);
            this.userService.insertUser(selfUser);

            SelfUser selfUser1 = new SelfUser(UUID.randomUUID().toString(), TEST, TEST, passwordEncoder.encode(TEST),
                    1L, TEST, null, null, SelfConstant.NORMAL, new Date(), null);
            this.userService.insertUser(selfUser1);
        } catch (Exception e) {
            logger.error("初始化添加用户信息出错", e);
            throw e;
        }
    }

    private void insertRole() throws Exception {
        try {
            SelfRole selfRole = new SelfRole(ADMIN, "管理员");
            this.roleService.insertRole(selfRole);

            SelfRole selfRole1 = new SelfRole(TEST, "测试");
            this.roleService.insertRole(selfRole1);
        } catch (Exception e) {
            logger.error("初始化添加角色信息出错", e);
            throw e;
        }
    }

    private void insertMenu() throws Exception {
        try {

            SelfMenu rootMenu = new SelfMenu("系统管理", "", null, SelfConstant.MENU_ROOT_ID, SelfConstant.MENU);
            this.menuService.insertMenu(rootMenu);

            SelfMenu menu = this.menuService.findAll().get(0);

            List<SelfMenu> selfMenuList = new ArrayList<>();

            SelfMenu userMenu1 = new SelfMenu("用户管理", "/user/getUserPaging", null, menu.getMenuId(), SelfConstant.MENU);
            SelfMenu userMenu2 = new SelfMenu("新增用户", "/user/insertUser", null, menu.getMenuId(), SelfConstant.BUTTON);
            SelfMenu userMenu3 = new SelfMenu("编辑用户", "/user/updateUser", null, menu.getMenuId(), SelfConstant.BUTTON);
            SelfMenu userMenu4 = new SelfMenu("删除用户", "/user/deleteUser", null, menu.getMenuId(), SelfConstant.BUTTON);
            selfMenuList.add(userMenu1);
            selfMenuList.add(userMenu2);
            selfMenuList.add(userMenu3);
            selfMenuList.add(userMenu4);

            SelfMenu roleMenu1 = new SelfMenu("角色管理", "/role/getRolePaging", null, menu.getMenuId(), SelfConstant.MENU);
            SelfMenu roleMenu2 = new SelfMenu("新增角色", "/role/insertRole", null, menu.getMenuId(), SelfConstant.BUTTON);
            SelfMenu roleMenu3 = new SelfMenu("编辑角色", "/role/updateRole", null, menu.getMenuId(), SelfConstant.BUTTON);
            SelfMenu roleMenu4 = new SelfMenu("删除角色", "/role/deleteRole", null, menu.getMenuId(), SelfConstant.BUTTON);
            selfMenuList.add(roleMenu1);
            selfMenuList.add(roleMenu2);
            selfMenuList.add(roleMenu3);
            selfMenuList.add(roleMenu4);

            SelfMenu menu1 = new SelfMenu("菜单管理", "/menu/getMenuPaging", null, menu.getMenuId(), SelfConstant.MENU);
            SelfMenu menu2 = new SelfMenu("新增菜单", "/menu/insertMenu", null, menu.getMenuId(), SelfConstant.BUTTON);
            SelfMenu menu3 = new SelfMenu("编辑菜单", "/menu/updateMenu", null, menu.getMenuId(), SelfConstant.BUTTON);
            SelfMenu menu4 = new SelfMenu("删除菜单", "/menu/deleteMenu", null, menu.getMenuId(), SelfConstant.BUTTON);
            selfMenuList.add(menu1);
            selfMenuList.add(menu2);
            selfMenuList.add(menu3);
            selfMenuList.add(menu4);

            this.menuService.insertMenuBatch(selfMenuList);
        } catch (Exception e) {
            logger.error("初始化添加菜单信息出错", e);
            throw e;
        }
    }

    private void initPermission() {
        List<SelfUser> selfUserList = this.userService.findAll();
        List<SelfRole> selfRoleList = this.roleService.findAll();
        List<SelfMenu> selfMenuList = this.menuService.findAll();

        for (SelfUser selfUser : selfUserList) {
            if (StringUtils.equals(selfUser.getUserAccount(), ADMIN)) {
                for (SelfRole selfRole : selfRoleList) {
                    if (!StringUtils.equals(selfRole.getRoleName(), ADMIN)) {
                        continue;
                    }
                    UserRole userRole = new UserRole(selfUser.getUserId(), selfRole.getRoleId());
                    try {
                        this.userRoleService.insertUserRole(userRole);
                    } catch (Exception e) {
                        logger.error("添加用户与角色关联信息出错", e);
                    }
                    for (SelfMenu selfMenu : selfMenuList) {
                        RoleMenu roleMenu = new RoleMenu(selfRole.getRoleId(), selfMenu.getMenuId());
                        try {
                            this.roleMenuService.insertRoleMenu(roleMenu);
                        } catch (Exception e) {
                            logger.error("添加角色与菜单关联信息出错", e);
                        }
                    }
                }
            }

            if (StringUtils.equals(selfUser.getUserAccount(), TEST)) {
                for (SelfRole selfRole : selfRoleList) {
                    if (!StringUtils.equals(selfRole.getRoleName(), TEST)) {
                        continue;
                    }
                    UserRole userRole = new UserRole(selfUser.getUserId(), selfRole.getRoleId());
                    try {
                        this.userRoleService.insertUserRole(userRole);
                    } catch (Exception e) {
                        logger.error("添加用户与角色关联信息出错", e);
                    }
                    for (SelfMenu selfMenu : selfMenuList) {
                        if (!StringUtils.contains(selfMenu.getMenuName(), "用户")) {
                            continue;
                        }
                        RoleMenu roleMenu = new RoleMenu(selfRole.getRoleId(), selfMenu.getMenuId());
                        try {
                            this.roleMenuService.insertRoleMenu(roleMenu);
                        } catch (Exception e) {
                            logger.error("添加角色与菜单关联信息出错", e);
                        }
                    }
                }
            }
        }
    }
}
