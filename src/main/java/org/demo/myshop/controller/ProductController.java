package org.demo.myshop.controller;


import org.demo.myshop.entity.Product;

import org.demo.myshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/admin")
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "tables";
    }

    @GetMapping("/admin/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/admin";
        }

        model.addAttribute("admin", productService.search(term));
        return "tables";
    }

    @GetMapping("/admin/add")
    public String add(Model model) {
        model.addAttribute("admin", new Product());
        return "form";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("admin", productService.findOne(id));
        return "form";
    }

    @PostMapping("/admin/save")
    public String save(@Valid Product product, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        productService.save(product);
        redirect.addFlashAttribute("successMessage", "Saved contact successfully!");
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/delete")
    public String delete(@PathVariable long id, RedirectAttributes redirect) {
        productService.delete(id);
        redirect.addFlashAttribute("successMessage", "Deleted contact successfully!");
        return "redirect:/admin";
    }
}
