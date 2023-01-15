package com.lteixeira.poctestcontainers.controller;

import com.lteixeira.poctestcontainers.PostgresTestContainerSimpleConfiguration;
import com.lteixeira.poctestcontainers.model.Category;
import com.lteixeira.poctestcontainers.service.CategoryService;
import com.lteixeira.poctestcontainers.util.ReadFileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryControllerOtherTest extends PostgresTestContainerSimpleConfiguration {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    protected MockMvc mockMvc;

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
