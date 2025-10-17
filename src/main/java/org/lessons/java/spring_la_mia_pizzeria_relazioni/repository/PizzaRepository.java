package org.lessons.java.spring_la_mia_pizzeria_relazioni.repository;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    
}
