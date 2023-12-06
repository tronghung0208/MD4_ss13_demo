package com.example.ss13_demo.controller;

import com.example.ss13_demo.model.entity.Category;
import com.example.ss13_demo.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/home"})
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("")
    public String home() {
        return "home";
    }

    @RequestMapping("/category")
    public String index(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "category/index";
    }

    @RequestMapping("/add-category")
    public String add(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category/add";
    }

    @RequestMapping("/create-category")
    public String create(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        Boolean check = categoryService.create(category);
        if (!check) {
            return "category/add?err";
        }
        redirectAttributes.addFlashAttribute("mess", "Thêm mới thành công");
        return "redirect:/category";
    }

    @RequestMapping("/edit-category/{id}")
    public String edit(@PathVariable("id") Integer id,Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "category/edit";
    }

@RequestMapping("/update-category")
    public String update(@ModelAttribute ("category") Category category){
categoryService.update(category,category.getCategoryId());
return "redirect:/category";
}
}
