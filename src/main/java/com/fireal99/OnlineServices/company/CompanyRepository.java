package com.fireal99.OnlineServices.company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fireal99.OnlineServices.user.User;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    List<Company> findAllByAdmin(User admin, Pageable pageable);

    List<Company> findByNameStartsWithIgnoreCase(String name, Pageable pageable);

    Optional<Company> findByAdminId(UUID adminId);
}
