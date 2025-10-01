package com.fireal99.OnlineServices.company.DTOS;

import java.util.UUID;

public class CompanyListDTO {
    private UUID id;
    private String name;
    private String description;
    private String logoPath;
    private UUID adminId;

    public CompanyListDTO(UUID id, String name, String description, String logoPath, UUID adminId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logoPath = logoPath;
        this.adminId = adminId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }
}
