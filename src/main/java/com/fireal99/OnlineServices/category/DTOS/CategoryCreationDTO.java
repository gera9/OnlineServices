package com.fireal99.OnlineServices.category.DTOS;

import java.util.UUID;

public class CategoryCreationDTO {
    private UUID id;
    private String name;
    private String imgPath;
    private UUID parentId;

    public CategoryCreationDTO(UUID id, String name, String imgPath, UUID parentId) {
        this.id = id;
        this.name = name;
        this.imgPath = imgPath;
        this.parentId = parentId;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }
}
