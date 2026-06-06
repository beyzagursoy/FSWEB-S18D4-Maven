package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/burger")
public class BurgerController {
    private BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao){
        this.burgerDao = burgerDao;
    }

    @GetMapping
    public List<Burger> findAll(){
        log.info("findAll endpoint triggered");
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger findById(@PathVariable Long id){
        log.info("findById endpoint triggered for id: {}", id);
        BurgerValidation.checkId(id);

        Burger burger = burgerDao.findById(id);
        BurgerValidation.checkBurger(burger, "Burger not found with id: " + id);

        return burger;
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger){
        log.info("save endpoint triggered for burger name: {}", burger.getName());
        return burgerDao.save(burger);
    }

    @PutMapping
    public Burger update(@RequestBody Burger burger) {
        log.info("update endpoint triggered for burger id: {}", burger.getId());
        BurgerValidation.checkId(burger.getId());
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger delete(@PathVariable Long id) {
        log.info("delete endpoint triggered for id: {}", id);
        BurgerValidation.checkId(id);
        return burgerDao.remove(id);
    }

    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable("price") Double price){
        log.info("findByPrice endpoint triggered for price: {}", price);
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable("breadType") String breadType) {
        log.info("findByBreadType endpoint triggered for breadType: {}", breadType);

        BreadType type = BreadType.valueOf(breadType.toUpperCase());
        return burgerDao.findByBreadType(type);
    }

    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable("content") String contents){
        log.info("findByContent endpoint triggered for content: {}", contents);
        return burgerDao.findByContent(contents);
    }
}
