package net.eldarov.usermanager.repository;


import net.eldarov.usermanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
    void AddUserRole(Long UserId,Long RoleId);
}