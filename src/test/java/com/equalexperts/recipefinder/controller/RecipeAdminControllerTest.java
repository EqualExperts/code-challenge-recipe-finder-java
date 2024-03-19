package com.equalexperts.recipefinder.controller;

import com.equalexperts.recipefinder.dto.*;
import com.equalexperts.recipefinder.entity.Recipe;
import com.equalexperts.recipefinder.integration.RecipeAPIClient;
import com.equalexperts.recipefinder.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.equalexperts.recipefinder.util.Data.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.just;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class RecipeAdminControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    RecipeAdminController controller;

    @Autowired
    RecipeRepository repository;

    @MockBean
    RecipeAPIClient apiClient;

    @Test
    @Transactional
    public void importShouldSucceed() {
        final RecipeDTO recipeDTO = avoOnToastRecipeDTO();

        when(apiClient.getRecipeList()).thenReturn(just(List.of(recipeDTO)));
        when(apiClient.getMethod(any())).thenReturn(just(avoOnToastMethod()));
        when(apiClient.getDiets(any())).thenReturn(just(avoOnToastDiets()));
        when(apiClient.getIngredients(any())).thenReturn(just(avoOnToastIngredients()));

        webTestClient
                .get()
                .uri("/admin/import")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).consumeWith(response -> {
                    String responseBody = response.getResponseBody();
                    assertTrue(responseBody.contains("Downloading")); // Adjust based on actual response
                });


        final Recipe recipe = repository.findAll().get(0);

        assertFalse(recipe.getMethod().isEmpty());
        assertFalse(recipe.getDiets().isEmpty());
        assertFalse(recipe.getIngredients().isEmpty());

        assertEquals(recipeDTO.getName(), recipe.getName());
        assertEquals(recipeDTO.getDescription(), recipe.getDescription());
        assertEquals(recipeDTO.getServings(), recipe.getServings());
        assertEquals(recipeDTO.getCookingTimeMin(), recipe.getCookingTimeMin());
        assertEquals(recipeDTO.getPreparationTimeMin(), recipe.getPrepTimeMin());
        assertEquals(recipeDTO.getEstimatedCostDollars(), recipe.getEstimatedCostDollars());
    }
}
