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
            SelfMenu selfMenu = new SelfMenu("用户管理", "/user", null, SelfConstant.MENU_ROOT_ID, SelfConstant.MENU);
            this.menuService.insertMenu(selfMenu);

            SelfMenu selfMenu1 = new SelfMenu("角色管理", "/role", null, SelfConstant.MENU_ROOT_ID, SelfConstant.MENU);
            this.menuService.insertMenu(selfMenu1);

            SelfMenu selfMenu2 = new SelfMenu("菜单管理", "/menu", null, SelfConstant.MENU_ROOT_ID, SelfConstant.MENU);
            this.menuService.insertMenu(selfMenu2);
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
                        if (!StringUtils.equals(selfMenu.getMenuName(), "用户管理")) {
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
