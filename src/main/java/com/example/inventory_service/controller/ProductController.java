package com.example.inventory_service.controller;

import com.example.inventory_service.entity.Product;
import com.example.inventory_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

//@RestController
//@RequestMapping("/products")
@Controller //we need only this @Controller annotation for graphQL
public class ProductController {

    @Autowired
    private ProductService productService;

//    @GetMapping
    @QueryMapping //we need @QueryMapping only instead of @GetMapping for graphQL
    public List<Product> getProducts(){
        return productService.getProducts();
    }

//    @GetMapping("/{category}")
    @QueryMapping   //we need @QueryMapping only instead of @GetMapping for graphQL
    //and we need the @Argument for passing the argument to the api from function.
    public List<Product> getProductsByCategory(@Argument String category){
        return productService.getProductsByCategory(category);
    }

    @MutationMapping //we need this @MutationMapping for the Put mapping
    public Product updateStock(@Argument int id, @Argument int stock){
        return productService.updateStock(id, stock);
    }

    @MutationMapping // update mapping
    public Product receiveNewShipment(@Argument int id, @Argument int quantity){
        return productService.receiveNewShipment(id, quantity);
    }

    //@MutationMapping is used to create or for @PostMapping as well.
    @MutationMapping
    public Product addProduct(@Argument Product product){
        return productService.addProduct(product);
    }

    //@MutationMapping is also used for @DeleteMapping as well
    @MutationMapping
    public String deleteProductById(@Argument Integer id){
        return productService.deleteProduct(id);
    }
}
