package com.example.inventory_service.service;
import com.example.inventory_service.entity.Product;
import com.example.inventory_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category){
        return productRepository.findByCategory(category);
    }

    //Sales team: update the stock price of a product
    public Product updateStock(int id, int stock){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with Id: " + id));
        product.setStock(stock);
        return productRepository.save(product);
    }

    //warehouse : receive new shipment
    //Sales team: update the stock price of a product
    public Product receiveNewShipment(int id, int quantity){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with Id: " + id));
        product.setStock(product.getStock()+quantity);
        return productRepository.save(product);
    }

    //Make a post call
    public Product addProduct(Product product){
        Product newProduct = new Product(product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock());

        return productRepository.save(newProduct);
    }


    //Making a delete call
    public String deleteProduct(Integer id){
        Product productToBeDeleted = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(productToBeDeleted);
        return "Product with id: " +id+" deleted successfully";
    }
}
