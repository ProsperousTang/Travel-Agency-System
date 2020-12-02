package com.dtss.dao;

import com.dtss.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    Set<Permission> findPermission(Integer id);
}
