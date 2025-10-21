package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index() {
        List<Pizza> pizzas = pizzaService.findAll();
        return pizzas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable int id) {
        Optional<Pizza> pizza = pizzaService.findById(id);
        
        if (pizza.isEmpty()) return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Pizza>(pizza.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pizza> store(@RequestBody Pizza pizza) {
        return new ResponseEntity<Pizza>(pizzaService.create(pizza), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable int id, @RequestBody Pizza pizza) {

        if (pizzaService.findById(id).isEmpty()) return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Pizza>(pizzaService.update(pizza), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> destroy(int id) {

       if (pizzaService.findById(id).isEmpty()) return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);

        pizzaService.deleteById(id);
        return new ResponseEntity<Pizza>(HttpStatus.OK);
    }
    
    
}
