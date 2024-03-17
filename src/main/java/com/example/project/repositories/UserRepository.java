package com.example.project.repositories;

import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
	User findByEmail (String email);
	@Query("SELECT u FROM User u WHERE u.role = :role")
	List<User> findByRole(@Param("role") String role);

	User findUserById(long id);
}
