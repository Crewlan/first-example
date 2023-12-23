package com.wolfcodea.test.aplication.firstexample.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.wolfcodea.test.aplication.firstexample.exceptions.ResourceNotFoundException;
import com.wolfcodea.test.aplication.firstexample.model.Product;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<Product>();
    
    public List<Product> getAllProducts(){
        return products;
    }

    public Optional<Product> getProductById(String id){
        return products.stream()
        .filter(product -> id.equals(product.getId()))
        .findFirst();
    }

    public Product addProduct(Product product){
        var id = UUID.randomUUID();
        product.setId(id.toString());
        
        products.add(product);
        return product;
    }

    public void deleteProduct(String id){
        products.removeIf(product -> id.equals(product.getId()));
    }

    public Product updateProduct(Product product){
        Optional<Product> productFounded =  getProductById(product.getId());
        if(productFounded.isEmpty()){
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        deleteProduct(product.getId());
        products.add(product);
        return product;
    }

}
