package com.example.helpdesk.repository;

import com.example.helpdesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("""
            select u
            from User u
            where u.username=?1 AND u.isActive = ?2
            """)
    UserDetails loadByUsername(String username, boolean b);
}
