package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("")
    public String index(Model model) {
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaRepository.findById(id).get());

        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }

        pizzaRepository.save(pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pizzas/edit";
        }

        pizzaRepository.save(pizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        pizzaRepository.deleteById(id);
        return "redirect:/pizzas";
    }
    

    
    
    
}
