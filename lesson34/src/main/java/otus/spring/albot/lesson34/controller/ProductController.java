package otus.spring.albot.lesson34.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otus.spring.albot.lesson34.entity.Product;
import otus.spring.albot.lesson34.service.NoteService;
import otus.spring.albot.lesson34.service.ProductService;
import otus.spring.albot.lesson34.service.SleepService;

@Controller
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final NoteService noteService;
    private final SleepService sleepService;

    @GetMapping(value = {"", "products"})
    @HystrixCommand
    public String productList(Model model) {
        sleepService.sleep();
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping(value = "products/{id}")
    @HystrixCommand
    public String product(@PathVariable("id") Long id, Model model) {
        sleepService.sleep();
        model.addAttribute("product", productService.getProductById(id));
        return "product";
    }

    @PostMapping(value = "products/addNote")
    @HystrixCommand
    public String addNote(@RequestParam("productId") Long productId, @RequestParam("comment") String comment, Model model) {
        sleepService.sleep();
        productService.addComment(productId, comment);
        return String.format("redirect:/products/%d", productId);
    }

    @PostMapping(value = "products/{id}")
    @HystrixCommand
    public String deleteNote(@PathVariable("id") Long id, @RequestParam("noteToDelete") Long noteId) {
        sleepService.sleep();
        noteService.removeNote(noteId);
        return String.format("redirect:/products/%d", id);
    }

    @GetMapping(value = "products/addNew")
    @HystrixCommand
    public String addProduct(Model model) {
        sleepService.sleep();
        model.addAttribute("product", new Product());
        return "productDetails";
    }

    @GetMapping(value = "product/edit")
    @HystrixCommand
    public String editProduct(@RequestParam("id") Long id, Model model) {
        sleepService.sleep();
        model.addAttribute("product", productService.getProductById(id));
        return "productDetails";
    }

    @GetMapping(value = "product/delete")
    @HystrixCommand
    public String deleteProduct(@RequestParam("id") Long id) {
        sleepService.sleep();
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @PostMapping(value = "product/save")
    @HystrixCommand
    public String save(Product product) {
        sleepService.sleep();
        product = productService.save(product);
        return String.format("redirect:/products/%d", product.getId());
    }
}
