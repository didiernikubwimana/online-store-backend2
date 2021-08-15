package edu.miu.cs545.project.onlinestore.service;

import java.util.List;

import edu.miu.cs545.project.onlinestore.domain.Role;

public interface RoleService {
    Role findRoleByName(String name);
    List<Role> findAll();
    Role findRoleById(Long id);
}
