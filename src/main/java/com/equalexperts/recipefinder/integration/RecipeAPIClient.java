package com.equalexperts.recipefinder.integration;

import com.equalexperts.recipefinder.dto.DietsDTO;
import com.equalexperts.recipefinder.dto.IngredientsDTO;
import com.equalexperts.recipefinder.dto.MethodDTO;
import com.equalexperts.recipefinder.dto.RecipeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class RecipeAPIClient {
    private final WebClient webClient;

    @Autowired
    public RecipeAPIClient(WebClient.Builder webClientBuilder, @Value("${api}") String baseUrl) {
        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<List<RecipeDTO>> getRecipeList() {
        return this.webClient.get()
                .uri("/list.json")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>(){});
    }

    public Mono<IngredientsDTO> getIngredients(String recipeId) {
        return this.webClient.get()
                .uri(String.format("/%s/ingredients.json", recipeId))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>(){});
    }

    public Mono<DietsDTO> getDiets(String recipeId) {
        return this.webClient.get()
                .uri(String.format("/%s/diets.json", recipeId))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>(){});
    }

    public Mono<MethodDTO> getMethod(String recipeId) {
        return this.webClient.get()
                .uri(String.format("/%s/method.json", recipeId))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>(){});
    }
}
