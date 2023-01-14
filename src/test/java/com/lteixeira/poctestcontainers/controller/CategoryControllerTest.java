package com.lteixeira.poctestcontainers.controller;

import com.lteixeira.poctestcontainers.BaseTest;
import com.lteixeira.poctestcontainers.model.Category;
import com.lteixeira.poctestcontainers.service.CategoryService;
import com.lteixeira.poctestcontainers.util.ReadFileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest extends BaseTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void shouldAddCategoryWithSuccessfully() throws Exception {

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ReadFileUtils.read("_files/request-category.json")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("computadores")));
    }

    @Test
    public void shouldReturnCategoryWithNameEqualComputadores() throws Exception {
        Category category = Category.builder().name("computadores").build();
        categoryService.save(category);
        mockMvc.perform(get("/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo("computadores")));
    }
}
