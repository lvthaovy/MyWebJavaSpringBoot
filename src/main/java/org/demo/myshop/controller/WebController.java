package org.demo.myshop.controller;

import org.demo.myshop.entity.Product;
import org.demo.myshop.service.CategoryService;
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
@RequestMapping("/admin")
public class WebController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public String list(Model model) {
        model.addAttribute("product", productService.findAll());
        return "tables";
    }

    @GetMapping("/products/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/admin/products";
        }

        model.addAttribute("product", productService.search(term));
        return "tables";
    }

    @GetMapping("/products/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("isNew", true);
        model.addAttribute("categories", categoryService.findAll());
        return "form";
    }

    @GetMapping("/products/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findOne(id));
        model.addAttribute("isNew", false);
        model.addAttribute("categories", categoryService.findAll());
        return "form";
    }

    @PostMapping("/products/save")
    public String save(@Valid Product product, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        productService.save(product);
//        redirect.addFlashAttribute("successMessage", "Saved contact successfully!");
        return "redirect:/admin/products";
    }

    @GetMapping("/products/{id}/delete")
    public String delete(@PathVariable long id, RedirectAttributes redirect) {
        productService.delete(id);
        redirect.addFlashAttribute("successMessage", "Deleted contact successfully!");
        return "redirect:/admin/products";
    }
}
