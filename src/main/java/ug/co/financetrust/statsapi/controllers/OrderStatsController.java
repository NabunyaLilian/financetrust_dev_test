package ug.co.financetrust.statsapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ug.co.financetrust.statsapi.service.RecipeService;
import ug.co.financetrust.statsapi.models.RecipeOrder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderStatsController {

    @Autowired
    private RecipeService recipeService;


    @PostMapping("/unique_recipe_count")
    public Map<String, Integer> uniqueCounter(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Map.of("unique_recipe_count", recipeService.uniqueCounter(Arrays.asList(mapper.readValue(multipartFile.getInputStream(), RecipeOrder[].class))));
    }

    @PostMapping("/count_per_recipe")
    public Map<String, List<Map<String, Object>>> recipeOccurrences(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Map.of("count_per_recipe",recipeService.recipeOccurrences(Arrays.asList(mapper.readValue(multipartFile.getInputStream(), RecipeOrder[].class))));
    }

    @PostMapping("/busiest_postcode")
    public Map<String, Object> hungriestPostCode(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Map.of("busiest_postcode", recipeService.hungriestPostCode(Arrays.asList(mapper.readValue(multipartFile.getInputStream(), RecipeOrder[].class))));
    }

    @PostMapping("/match_by_name")
    public Map<String, List<String>> recipesByName(@RequestParam("file") MultipartFile multipartFile, @RequestParam("matches") String matches) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> possibleNames = List.of(matches.split(","));
        return Map.of("match_by_name", recipeService.recipesByName(Arrays.asList(mapper.readValue(multipartFile.getInputStream(), RecipeOrder[].class)), possibleNames));
    }
}
