package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.models.Category;
import org.archivos.ecommercegt.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApplicationConfig.BASE_URL +"/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<String[]> getCategories(){
        List<Category> categories = categoryRepository.findAll();

        String[] categoriesName = new String[categories.size()];

        for(int i = 0; i < categories.size(); i++) {
            categoriesName[i] = categories.get(i).getName();
        }

        return ResponseEntity.ok().body(categoriesName);
    }

}