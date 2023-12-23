package com.wolfcodea.test.aplication.firstexample.view.controller;

import org.springframework.web.bind.annotation.RestController;


import com.wolfcodea.test.aplication.firstexample.services.ProductService;
import com.wolfcodea.test.aplication.firstexample.shared.ProductDTO;
import com.wolfcodea.test.aplication.firstexample.shared.ResponseMessage;
import com.wolfcodea.test.aplication.firstexample.view.model.ProductRequest;
import com.wolfcodea.test.aplication.firstexample.view.model.ProductResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping(path="/api/products/")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public  ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductDTO> productDTOs =  service.getAllProducts();
        ModelMapper mapper = new ModelMapper();

        List<ProductResponse> response = productDTOs
        .stream()
        .map(product -> mapper
        .map(product, ProductResponse.class)).
        collect(Collectors.toList());

        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")   
    public ResponseEntity<Optional<ProductResponse>> getById(@PathVariable String id){

        Optional<ProductDTO> dto = service.getProductById(id);

        ProductResponse product = new ModelMapper().map(dto.get(), ProductResponse.class);

        return new ResponseEntity<>(Optional.of(product), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest){
        ModelMapper mapper = new ModelMapper();

        ProductDTO dto = mapper.map(productRequest, ProductDTO.class);

        dto =  service.addProduct(dto);
        return new ResponseEntity<>(mapper.map(dto, ProductResponse.class), HttpStatus.CREATED);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable String id){
        service.deleteProduct(id);

        ResponseMessage responseMessage = new ResponseMessage("Produto deletado com sucesso");
        
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PatchMapping( consumes = "application/json")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest,@RequestParam String id) {       
        ModelMapper mapper = new ModelMapper();

        ProductDTO dto = mapper.map(productRequest, ProductDTO.class);

        dto =   service.updateProduct(id, dto);

        return new ResponseEntity<>(mapper.map(dto, ProductResponse.class), HttpStatus.OK);
    }
}
