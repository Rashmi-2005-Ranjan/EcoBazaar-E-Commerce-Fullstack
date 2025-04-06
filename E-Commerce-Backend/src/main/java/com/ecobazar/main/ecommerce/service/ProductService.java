package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.model.Product;
import com.ecobazar.main.ecommerce.model.Size;
import com.ecobazar.main.ecommerce.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest req);
    public String deleteProduct(Long productId)throws ProductException;
    public Product updateProduct(Long productId,Product product)throws ProductException;
    public Product getProductById(Long productId)throws ProductException;
    public List<Product> findProductByCategory(String category);
    public Page<Product> getAllProducts(
            String category,
            List<String>colors,
            List<String>sizes,
            int minPrice,
            int maxPrice,
            int minDiscount,
            String sort,
            String stock,
            int pageNo,
            int pageSize
    );
    public List<Product> findAllProducts();
}
