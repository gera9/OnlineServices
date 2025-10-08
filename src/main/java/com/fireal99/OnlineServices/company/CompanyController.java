package com.fireal99.OnlineServices.company;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fireal99.OnlineServices.company.DTOS.CompanyCreationDTO;
import com.fireal99.OnlineServices.company.DTOS.CompanyListDTO;
import com.fireal99.OnlineServices.company.DTOS.CompanyMapper;
import com.fireal99.OnlineServices.user.User;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public UUID create(@RequestBody CompanyCreationDTO company) {
        return companyService.create(CompanyMapper.toCompany(company));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable UUID id) {
        var optCompany = companyService.findById(id);
        if (optCompany.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optCompany.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CompanyListDTO>> findAll(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "10", name = "limit") String pageSizeStr,
            @RequestParam(defaultValue = "0", name = "offset") String pageNumberStr) {

        Integer pageSize;
        Integer pageNumber;
        try {
            pageSize = Integer.parseInt(pageSizeStr);
            pageNumber = Integer.parseInt(pageNumberStr);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var result = companyService.findAll(name, pageNumber, pageSize)
                .stream()
                .map(CompanyMapper::tCompanyListDTO)
                .toList();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<Company> findByAdminId(@PathVariable UUID adminId) {
        var optCompany = companyService.findByAdminId(adminId);
        if (optCompany.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optCompany.get(), HttpStatus.OK);
    }

    @GetMapping("/admin/me")
    public ResponseEntity<Company> findByMe() {
        var usrFromToken = new User();
        var optCompany = companyService.findByAdminId(usrFromToken.getId());
        if (optCompany.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optCompany.get(), HttpStatus.OK);
    }
}
