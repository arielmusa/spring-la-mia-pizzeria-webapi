package org.lessons.java.spring_la_mia_pizzeria_relazioni.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name is required")
    private String name;

    @Lob
    private String description;

    private String image_url;

    @Min(value = 0, message = "price must be positive")
    private double price;

    @OneToMany(mappedBy = "pizza")
    @JsonManagedReference
    private List<SpecialOffers> specialOffers;

    @ManyToMany
    @JoinTable ( name = "ingredient_pizza",
    joinColumns = @JoinColumn(name="pizza_id"),
    inverseJoinColumns = @JoinColumn(name="ingredient_id")
     )
     @JsonManagedReference
    private List<Ingredient> ingredients;


    // Getters and Setters

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<SpecialOffers> getSpecialOffers() {
        return this.specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffers> specialOffers) {
        this.specialOffers = specialOffers;
    }


    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    @Override
    public String toString(){
        return String.format("%d - %s (%s) %.2f€", id, name, description, price);
    }

    
}
