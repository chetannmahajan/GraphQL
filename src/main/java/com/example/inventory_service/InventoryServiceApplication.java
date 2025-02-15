package com.example.inventory_service;

import com.example.inventory_service.entity.Product;
import com.example.inventory_service.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class InventoryServiceApplication {

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	public void initDB(){
		if (productRepository.count() == 0) {
			List<Product> products = Stream.of(
					new Product("Laptop", "Electornics", 74999.99f, 50),
					new Product("SmartPhone", "Electornics", 39999.99f, 100),
					new Product("Office Chair", "Furniture", 799.99f, 200),
					new Product("Notebook", "Stationery", 99.99f, 500),
					new Product("Desk Lamp", "Furniture", 1999.99f, 50),
					new Product("Water Bottle", "Accessories", 499.99f, 300)
			).collect(Collectors.toList());

			productRepository.saveAll(products);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
