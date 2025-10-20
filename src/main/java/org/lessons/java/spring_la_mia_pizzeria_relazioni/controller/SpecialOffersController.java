package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.SpecialOffers;
/* import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository; */
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.SpecialOffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/special-offers")
public class SpecialOffersController {
    
    @Autowired
    private SpecialOffersRepository specialOffersRepository;

/*     @Autowired
    private PizzaRepository pizzaRepository; */

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("offer", specialOffersRepository.findById(id).get());
        return "special_offers/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("offer") SpecialOffers offer, BindingResult bindingResult, @PathVariable int id) {
        specialOffersRepository.save(offer);
        
        return "redirect:/pizzas/" + offer.getPizza().getId();
    }

    @PostMapping("/create-offer")
    public String storeOffer(@Valid @ModelAttribute("offer") SpecialOffers offer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/create-offer";
        }
  
        specialOffersRepository.save(offer);
        return "redirect:/pizzas/" + offer.getPizza().getId();
    }
    
    

    
}
