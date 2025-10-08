package com.fireal99.OnlineServices.company.DTOS;

import org.springframework.stereotype.Component;

import com.fireal99.OnlineServices.company.Company;
import com.fireal99.OnlineServices.user.User;

@Component
public class CompanyMapper {
    public static Company toCompany(CompanyCreationDTO companyCreationDTO) {
        var admin = new User(companyCreationDTO.getAdminId(), null, null, null, null, null, null, null);
        return new Company(companyCreationDTO.getId(),
                companyCreationDTO.getName(),
                companyCreationDTO.getDescription(),
                companyCreationDTO.getLogoPath(),
                admin);
    }

    public static CompanyListDTO tCompanyListDTO(Company company) {
        return new CompanyListDTO(
                company.getId(),
                company.getName(),
                company.getDescription(),
                company.getLogoPath(),
                company.getAdmin().getId());
    }
}
