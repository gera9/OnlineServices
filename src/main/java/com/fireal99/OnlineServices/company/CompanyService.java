package com.fireal99.OnlineServices.company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public UUID createCompany(Company company) {
        return companyRepository.save(company).getId();
    }

    public List<Company> findAll(String name, Integer pageNumber, Integer pageSize) {
        var sortedPagination = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        if (name.isEmpty()) {
            return companyRepository.findAll(sortedPagination).toList();
        }

        return companyRepository.findByNameStartsWithIgnoreCase(name, sortedPagination);
    }

    public Optional<Company> findById(UUID id) {
        return companyRepository.findById(id);
    }

    public Optional<Company> findByAdminId(UUID adminId) {
        return companyRepository.findByAdminId(adminId);
    }
}
