package com.wolfcodea.test.aplication.firstexample.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolfcodea.test.aplication.firstexample.exceptions.ResourceNotFoundException;
import com.wolfcodea.test.aplication.firstexample.model.Product;
import com.wolfcodea.test.aplication.firstexample.repository.IProductRepository;
import com.wolfcodea.test.aplication.firstexample.shared.ProductDTO;

@Service
public class ProductService {

    @Autowired
    private IProductRepository repository;

    public List<ProductDTO> getAllProducts(){

        List<Product> products =  repository.findAll();
        
        return products.stream()
        .map(product -> new ModelMapper().map(product, ProductDTO.class))
        .collect(Collectors.toList());
    }

    public ProductDTO addProduct(ProductDTO productDTO){

        ModelMapper mapper = new ModelMapper();

        Product product = mapper.map(productDTO, Product.class);

        product = repository.save(product);
        productDTO.setId(product.getId());

        return productDTO;
    }

    public Optional<ProductDTO> getProductById(String id){
        Optional<Product> product =  repository.findById(id);

        if(product.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: "+ id +"não encontrado!");
        }
        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
        return Optional.of(dto);
    }

    public void deleteProduct(String id){

        Optional<Product> product = repository.findById(id);

        if(product.isEmpty()){
            throw new ResourceNotFoundException("Não foi possivel deletar o produto com o id:"+ id +" Produto não existe");
        }

        repository.deleteById(id);
    }

      public ProductDTO updateProduct(String id, ProductDTO productDTO){

        productDTO.setId(id);
        
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productDTO, Product.class);

        repository.save(product);

        return productDTO;
    }
}
