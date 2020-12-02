package com.dtss.dao;

import com.dtss.pojo.Role;

import java.util.Set;

public interface RoleDao {
        Set<Role> findRole(Integer id);
}
