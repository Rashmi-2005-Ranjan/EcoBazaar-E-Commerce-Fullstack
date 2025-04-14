package com.ecobazar.main.ecommerce.controllers;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.model.Product;
import com.ecobazar.main.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(
            @RequestParam(required = true) String category,  // Required
            @RequestParam(required = false, defaultValue = "") List<String> color,
            @RequestParam(required = false, defaultValue = "") List<String> size,
            @RequestParam(defaultValue = "0") Integer minPrice,
            @RequestParam(defaultValue = "10000") Integer maxPrice,
            @RequestParam(defaultValue = "0") Integer minDiscount,
            @RequestParam(required = false) String sort,      // Optional
            @RequestParam(required = false) String stock,     // Optional
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Page<Product> res = productService.getAllProducts ( category , color , size , minPrice , maxPrice , minDiscount , sort , stock , pageNumber , pageSize );
        return new ResponseEntity<> ( res , HttpStatus.ACCEPTED );
    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product> findProductByIdhandler(@PathVariable Long productId) throws ProductException {
        Product product = productService.getProductById ( productId );
        return new ResponseEntity<> ( product , HttpStatus.ACCEPTED );
    }
}
