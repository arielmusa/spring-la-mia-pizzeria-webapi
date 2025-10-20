package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        return "ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create";
    }
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "ingredients/create";
        
        ingredientRepository.save(ingredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        return "ingredients/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "ingredients/edit";
        
        ingredientRepository.save(ingredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String destroy(@PathVariable int id) {
        Ingredient ingredientToDelete = ingredientRepository.findById(id).get();
        List<Pizza> pizzas = pizzaRepository.findAll();

        for (Pizza pizza : pizzas) {
            if (pizza.getIngredients().contains(ingredientToDelete))
            pizza.getIngredients().remove(ingredientToDelete);
            pizzaRepository.save(pizza);
        }
        
        ingredientRepository.delete(ingredientToDelete);
        return "redirect:/ingredients";
    }
    
    
    
    
    
    
}
