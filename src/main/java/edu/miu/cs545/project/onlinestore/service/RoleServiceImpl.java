package edu.miu.cs545.project.onlinestore.service;
import edu.miu.cs545.project.onlinestore.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import edu.miu.cs545.project.onlinestore.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findRoleById(id);
    }
}