package com.example.ss13_demo.controller;

import com.example.ss13_demo.model.entity.Category;
import com.example.ss13_demo.model.entity.Product;
import com.example.ss13_demo.model.service.CategoryService;
import com.example.ss13_demo.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Controller

public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/product")
    public String index(Model model) {
        List<Product> productList = productService.getAll();
        model.addAttribute("productList", productList);
        return "product/index";
    }
    @RequestMapping("add-product")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> categoryList=categoryService.getAll();
        model.addAttribute("categoryList",categoryList);
        return "product/add";
    }

    @RequestMapping("/create-product")
    public String create(@ModelAttribute("product") Product product, @RequestParam ("img_upload")MultipartFile file, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("uploads/images");
        String fileName = file.getOriginalFilename();
        File destination = new File(path+"/"+fileName);

        try {
            Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setImage(fileName);
        System.out.println(fileName);
        productService.create(product);
        return "redirect:/product";
    }
@RequestMapping("edit-product/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
Product product=productService.findById(id);
model.addAttribute("product-edit",product);
List<Category> listCategory=categoryService.getAll();
model.addAttribute("listCategory",listCategory);
        return "product/edit";
}
}
