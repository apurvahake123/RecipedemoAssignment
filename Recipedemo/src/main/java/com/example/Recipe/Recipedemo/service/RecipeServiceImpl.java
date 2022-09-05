package com.example.Recipe.Recipedemo.service;

import com.example.Recipe.Recipedemo.dto.RecipeDTO;
import com.example.Recipe.Recipedemo.entity.Recipe;
import com.example.Recipe.Recipedemo.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@Component
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDTO> getAllRecipes() throws Exception {
        List<Recipe> recipes = recipeRepository.findAll();
        List<RecipeDTO> recipeDTOs = new ArrayList<RecipeDTO>();
        for (Recipe recipe : recipes) {
            RecipeDTO recipeDTO = RecipeDTO.valueOf(recipe);
            recipeDTOs.add(recipeDTO);
        }
        return recipeDTOs;
    }

    public RecipeDTO getRecipe(Integer recipeid) throws Exception {
        Optional<Recipe> optional = recipeRepository.findById(recipeid);
        Recipe recipe = optional.orElseThrow(() -> new Exception("service.Recipe_NOT_FOUND"));
        RecipeDTO recipe2 = new RecipeDTO();
        recipe2 = RecipeDTO.valueOf(recipe);
        return recipe2;
    }
    public Integer addRecipe(RecipeDTO recipe) throws Exception {
        Recipe recipeEntity = recipe.createEntity();
        Recipe recipeEntity2 = recipeRepository.save(recipeEntity);
        return recipeEntity2.getRecipeid();
    }
    public List<Recipe> deleteRecipe(Integer recipeid) throws Exception {
        Optional<Recipe> recipe = recipeRepository.findById(recipeid);
        recipe.orElseThrow(() -> new Exception("service.Recipe_NOT_FOUND"));
        recipeRepository.deleteById(recipeid);

        return null;
    }
    public Recipe updateRecipe(RecipeDTO recipeDTO) throws Exception {
        Recipe recipe = recipeDTO.createEntity();
        recipe = recipeRepository.save(recipe);
        return recipe;
    }


    public List<RecipeDTO> getByRecipetype(String recipetype) {

        List<Recipe> recipes = recipeRepository.getByRecipetype(recipetype);
        List<RecipeDTO> recipeDTOs = new ArrayList<RecipeDTO>();
        for (Recipe recipe : recipes) {
            RecipeDTO recipeDTO = RecipeDTO.valueOf(recipe);
            recipeDTOs.add(recipeDTO);
        }
        return recipeDTOs;
    }


    public List<RecipeDTO> getByServe(Integer serve) {

        List<Recipe> recipes = recipeRepository.getByServe(serve);
        List<RecipeDTO> recipeDTOs = new ArrayList<RecipeDTO>();
        for (Recipe recipe : recipes) {
            RecipeDTO recipeDTO = RecipeDTO.valueOf(recipe);
            recipeDTOs.add(recipeDTO);
        }
        return recipeDTOs;
    }
}




