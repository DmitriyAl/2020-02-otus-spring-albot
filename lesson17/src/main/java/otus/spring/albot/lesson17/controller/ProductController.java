package otus.spring.albot.lesson17.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import otus.spring.albot.lesson17.service.ProductService;

@Controller
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping(value = "products")
    public String productList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "pages/products";
    }

    @GetMapping(value = "products/{id}")
    public String product(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "pages/product";
    }
}