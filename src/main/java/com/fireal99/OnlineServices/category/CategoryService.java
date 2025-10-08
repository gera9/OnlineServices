package com.fireal99.OnlineServices.category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public UUID create(Category category) {
        return categoryRepository.save(category).getId();
    }

    public List<Category> findAll(Integer pageNumber, Integer pageSize) {
        var sortedPagination = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        return categoryRepository.findAll(sortedPagination).toList();
    }

    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    public void deleteById(UUID id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> updateById(UUID id, Category category) {
        var optCategory = categoryRepository.findById(id);
        if (optCategory.isEmpty()) {
            return Optional.empty();
        }

        var oldCategory = optCategory.get();

        if (!category.getName().equals(oldCategory.getName())) {
            oldCategory.setName(category.getName());
        }

        return Optional.of(categoryRepository.save(oldCategory));
    }
}
