package com.fireal99.OnlineServices.category.DTOS;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fireal99.OnlineServices.category.Category;

@Component
public class CategoryMapper {
    public static Category toCategory(CategoryCreationDTO category) {
        Category parent = null;
        if (category.getParentId() != null) {
            parent = new Category(category.getParentId(), null, null, parent);
        }
        return new Category(category.getId(), category.getName(), category.getImgPath(), parent);
    }

    public static CategoryListDTO toCategoryListDTO(Category category) {
        UUID parentId = null;
        if (category.getParent() != null) {
            parentId = category.getParent().getId();
        }

        return new CategoryListDTO(
                category.getId(),
                category.getName(),
                category.getImgPath(),
                parentId);
    }
}
