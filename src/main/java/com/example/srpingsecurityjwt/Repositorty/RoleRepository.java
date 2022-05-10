package com.example.srpingsecurityjwt.Repositorty;

import com.example.srpingsecurityjwt.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
