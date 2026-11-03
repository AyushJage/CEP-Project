package com.dbit.rwh.repository;

import com.dbit.rwh.entity.Role;
import com.dbit.rwh.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);
    boolean existsByPhone(String phone);
    List<User> findAllByRole(Role role);

    @Query("SELECT u FROM User u WHERE u.role = :role AND (" +
           "LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(u.phone) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(u.village) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(u.district) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<User> searchFarmers(@Param("role") Role role, @Param("search") String search, Pageable pageable);
}
