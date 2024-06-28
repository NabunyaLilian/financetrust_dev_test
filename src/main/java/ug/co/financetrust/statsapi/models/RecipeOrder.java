package ug.co.financetrust.statsapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipeOrder {
    @JsonProperty("recipe")
    private String recipe;

    @JsonProperty("postcode")
    private String postcode;

    @JsonProperty("delivery")
    private String delivery;

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
