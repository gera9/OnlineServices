package com.fireal99.OnlineServices.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fireal99.OnlineServices.category.DTOS.CategoryCreationDTO;
import com.fireal99.OnlineServices.category.DTOS.CategoryListDTO;
import com.fireal99.OnlineServices.category.DTOS.CategoryMapper;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public UUID create(@RequestBody CategoryCreationDTO category) {
        return categoryService.create(CategoryMapper.toCategory(category));
    }

    @GetMapping
    public ResponseEntity<List<CategoryListDTO>> findAll(
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

        var result = categoryService.findAll(pageNumber, pageSize)
                .stream()
                .map(CategoryMapper::toCategoryListDTO)
                .toList();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable UUID id) {
        var optCategory = categoryService.findById(id);
        if (optCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optCategory.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateById(@PathVariable UUID id, @RequestBody CategoryCreationDTO category) {
        var optCategory = categoryService.updateById(id, CategoryMapper.toCategory(category));
        if (optCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optCategory.get().getId(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable UUID id) {
        categoryService.deleteById(id);
    }
}
