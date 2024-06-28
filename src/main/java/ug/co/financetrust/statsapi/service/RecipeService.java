package ug.co.financetrust.statsapi.service;

import org.springframework.stereotype.Service;
import ug.co.financetrust.statsapi.models.RecipeOrder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    public int uniqueCounter(List<RecipeOrder> recipeOrders) {
        return recipeOrders.stream().map(RecipeOrder::getRecipe).collect(Collectors.toSet()).size();
    }

    public List<Map<String, Object>> recipeOccurrences(List<RecipeOrder> recipeOrders) {
        Map<String, Long> occurrences = recipeOrders.stream().collect(Collectors.groupingBy(RecipeOrder::getRecipe, Collectors.counting()));
        return occurrences.entrySet().stream().sorted(Map.Entry.comparingByKey()).map((e) ->
                new HashMap<String, Object>() {{
                    put("recipe", e.getKey());
                    put("count", e.getValue());
                }}).collect(Collectors.toList());
    }

    public Map<String, Object> hungriestPostCode(List<RecipeOrder> recipeOrders) {
        Map<String, Long> postCodes = recipeOrders.stream().collect(Collectors.groupingBy(RecipeOrder::getPostcode, Collectors.counting()));
        var hungriestEntry = postCodes.entrySet().stream().max(Map.Entry.comparingByValue()).orElseGet(null);
        return new HashMap<>() {{
            put("postcode", hungriestEntry.getKey());
            put("delivery_count", hungriestEntry.getValue());
        }};
    }

    public List<String> recipesByName(List<RecipeOrder> recipeOrders, List<String> names) {
        Set<String> recipeNames =  recipeOrders.stream().collect(Collectors.groupingBy(RecipeOrder::getRecipe, Collectors.counting())).keySet();
        return recipeNames.stream().filter(key -> names.stream().map(String::trim).map(String::toLowerCase).anyMatch(key.toLowerCase()::contains)).toList();
    }

}
