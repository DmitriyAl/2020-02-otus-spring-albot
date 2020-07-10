package otus.spring.albot.lesson29.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otus.spring.albot.lesson29.entity.Product;
import otus.spring.albot.lesson29.service.NoteService;
import otus.spring.albot.lesson29.service.ProductService;

@Controller
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private NoteService noteService;

    @GetMapping(value = {"", "products"})
    public String productList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping(value = "products/{id}")
    public String product(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product";
    }

    @PostMapping(value = "products/addNote")
    public String addNote(@RequestParam("productId") Long productId, @RequestParam("comment") String comment, Model model) {
        productService.addComment(productId, comment);
        return String.format("redirect:/products/%d", productId);
    }

    @PostMapping(value = "products/{id}")
    public String deleteNote(@PathVariable("id") Long id, @RequestParam("noteToDelete") Long noteId) {
        noteService.removeNote(noteId);
        return String.format("redirect:/products/%d", id);
    }

    @GetMapping(value = "products/addNew")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "productDetails";
    }

    @GetMapping(value = "product/edit")
    public String editProduct(@RequestParam("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productDetails";
    }

    @GetMapping(value = "product/delete")
    public String deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @PostMapping(value = "product/save")
    public String save(Product product) {
        product = productService.save(product);
        return String.format("redirect:/products/%d", product.getId());
    }
}
