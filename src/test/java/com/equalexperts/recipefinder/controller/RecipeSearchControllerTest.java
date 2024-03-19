package com.equalexperts.recipefinder.controller;

import com.equalexperts.recipefinder.controller.RecipeAdminController;
import com.equalexperts.recipefinder.dto.RecipeDTO;
import com.equalexperts.recipefinder.entity.Recipe;
import com.equalexperts.recipefinder.integration.RecipeAPIClient;
import com.equalexperts.recipefinder.repository.RecipeRepository;
import com.equalexperts.recipefinder.util.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import static com.equalexperts.recipefinder.util.Data.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.just;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RecipeSearchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    RecipeAdminController controller;

    @MockBean
    RecipeRepository repository;


    @BeforeEach
    public void setUp(@Autowired WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        given(repository.findAll()).willReturn(List.of(
                new Recipe(Data.avoOnToastRecipeDTO())
                        .setDiets(Data.avoOnToastDiets())
                        .setIngredients(Data.avoOnToastIngredients())
                        .setMethod(Data.avoOnToastMethod()),
                new Recipe(Data.burntToastRecipeDTO())
                        .setDiets(Data.burntToastDiets())
                        .setIngredients(Data.burntToastIngredients())
                        .setMethod(Data.burntToastMethod())
        ));
    }

    @Test
    public void search_should_return_all_recipes_with_no_filter() throws Exception {
        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe-search"))
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().attribute("recipes", hasSize(2)))
                ;
    }

    @Test
    public void search_on_name() throws Exception {
        mockMvc.perform(get("/recipes?query=" + "aVoC"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe-search"))
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().attribute("recipes", hasSize(1)))
        ;
    }

    @Test
    public void search_on_desc() throws Exception {
        mockMvc.perform(get("/recipes?query=" + "carcin"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe-search"))
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().attribute("recipes", hasSize(1)))
        ;
    }
    @Test
    public void search_on_method() throws Exception {
        mockMvc.perform(get("/recipes?query=" + "50"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe-search"))
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().attribute("recipes", hasSize(1)))
        ;
    }

    @Test
    public void search_on_diet() throws Exception {
        mockMvc.perform(get("/recipes?query=" + "vegan"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe-search"))
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().attribute("recipes", hasSize(1)))
        ;
    }


    @Test
    public void noResults() throws Exception {
        mockMvc.perform(get("/recipes?query=" + "monkey"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe-search"))
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().attribute("recipes", hasSize(0)))
        ;
    }
}
