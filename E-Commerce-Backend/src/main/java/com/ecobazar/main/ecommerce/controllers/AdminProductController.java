package com.ecobazar.main.ecommerce.controllers;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.model.Product;
import com.ecobazar.main.ecommerce.request.CreateProductRequest;
import com.ecobazar.main.ecommerce.response.ApiResponse;
import com.ecobazar.main.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req) {
        Product product = productService.createProduct ( req );
        return new ResponseEntity<Product> ( product , HttpStatus.CREATED );
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {
        productService.deleteProduct ( productId );
        ApiResponse apiResponse = new ApiResponse ( );
        apiResponse.setMessage ( "Product Deleted Successfully" );
        apiResponse.setStatus ( true );
        return new ResponseEntity<> ( apiResponse , HttpStatus.OK );
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> products = productService.findAllProducts ( );
        return new ResponseEntity<> ( products , HttpStatus.OK );
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product req , @PathVariable Long productId) throws ProductException {
        Product product = productService.updateProduct ( productId , req );
        return new ResponseEntity<> ( product , HttpStatus.CREATED );
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] req) {
        for (CreateProductRequest productRequest : req) {
            productService.createProduct ( productRequest );
        }
        ApiResponse response = new ApiResponse ( );
        response.setMessage ( "Products Created Successfully" );
        response.setStatus ( true );
        return new ResponseEntity<> ( response , HttpStatus.CREATED );
    }
}
