package com.gm.EcommerceBackend.repositories;

import com.gm.EcommerceBackend.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}
