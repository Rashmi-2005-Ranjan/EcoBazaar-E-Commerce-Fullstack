package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.model.Category;
import com.ecobazar.main.ecommerce.model.Product;
import com.ecobazar.main.ecommerce.model.Size;
import com.ecobazar.main.ecommerce.repositories.CategoryRepositry;
import com.ecobazar.main.ecommerce.repositories.ProductRepository;
import com.ecobazar.main.ecommerce.request.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepositry categoryRepositry;
    @Autowired
    private UserService userService;

    @Override
    public Product createProduct(CreateProductRequest req) {
        Category topLevel = categoryRepositry.findByName ( req.getTopLavelCategory ( ) );
        if (topLevel == null) {
            Category topLevelCategory = new Category ( );
            topLevelCategory.setName ( req.getTopLavelCategory ( ) );
            topLevelCategory.setLevel ( 1 );
            topLevel = categoryRepositry.save ( topLevelCategory );
        }
        Category secondLevel = categoryRepositry.findByNameAndParent ( req.getSecondLavelCategory ( ) , req.getTopLavelCategory ( ) );
        if (secondLevel == null) {
            Category secondLevelCategory = new Category ( );
            secondLevelCategory.setName ( req.getSecondLavelCategory ( ) );
            secondLevelCategory.setLevel ( 2 );
            secondLevelCategory.setParentCategory ( topLevel );
            secondLevel = categoryRepositry.save ( secondLevelCategory );
        }
        Category thirdLevel = categoryRepositry.findByNameAndParent ( req.getThirdLavelCategory ( ) , secondLevel.getName ( ) );
        if (thirdLevel == null) {
            Category thirdLevelCategory = new Category ( );
            thirdLevelCategory.setName ( req.getThirdLavelCategory ( ) );
            thirdLevelCategory.setLevel ( 3 );
            thirdLevelCategory.setParentCategory ( secondLevel );
            thirdLevel = categoryRepositry.save ( thirdLevelCategory );
        }

        Product product = new Product ( );
        product.setTitle ( req.getTitle ( ) );
        product.setDescription ( req.getDescription ( ) );
        product.setPrice ( req.getPrice ( ) );
        product.setDiscountedPrice ( req.getDiscountedPrice ( ) );
        product.setDiscountPresent ( req.getDiscountPercent ( ) );
        product.setQuantity ( req.getQuantity ( ) );
        product.setBrand ( req.getBrand ( ) );
        product.setColor ( req.getColor ( ) );
        product.setSizes ( req.getSize ( ) );
        product.setImageUrl ( req.getImageUrl ( ) );
        product.setCategory ( thirdLevel );
        product.setCreatedAt ( LocalDateTime.now ( ) );
        Product savedProduct = productRepository.save ( product );
        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = getProductById ( productId );
        product.getSizes ( ).clear ( );
        productRepository.delete ( product );
        return "Product deleted successfully";
    }

    @Override
    public Product updateProduct(Long productId , Product product) throws ProductException {
        Product existingProduct = getProductById ( productId );
        if (product.getQuantity ( ) != 0) {
            existingProduct.setQuantity ( product.getQuantity ( ) );
        }
        return productRepository.save ( existingProduct );
    }

    @Override
    public Product getProductById(Long productId) throws ProductException {
        Optional<Product> opt = productRepository.findById ( productId );
        if (opt.isPresent ( )) {
            return opt.get ( );
        } else {
            throw new ProductException ( "Product not found" );
        }
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProducts(
            String category ,
            List<String> colors ,
            List<String> sizes ,
            int minPrice ,
            int maxPrice ,
            int minDiscount ,
            String sort ,
            String stock ,
            int pageNo ,
            int pageSize
    ) {
        colors = colors != null ? colors : Collections.emptyList ( );
        sizes = sizes != null ? sizes : Collections.emptyList ( );

        Pageable pageable = PageRequest.of ( pageNo , pageSize );
        List<Product> products = productRepository.filterProducts ( category , minPrice , maxPrice , minDiscount , sort );

        if (stock != null) {
            if (stock.equals ( "in_stock" )) {
                products = products.stream ( ).filter ( p -> p.getQuantity ( ) > 0 ).collect ( Collectors.toList ( ) );
            } else if (stock.equals ( "out_of_stock" )) {
                products = products.stream ( ).filter ( p -> p.getQuantity ( ) == 0 ).collect ( Collectors.toList ( ) );
            }
        }
        int startIndex = (int) pageable.getOffset ( );
        int endIndex = Math.min ( startIndex + pageable.getPageSize ( ) , products.size ( ) );
        List<Product> pageContent = products.subList ( startIndex , endIndex );
        Page<Product> filterProducts = new PageImpl<> ( pageContent , pageable , products.size ( ) );
        return filterProducts;
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = productRepository.findAll ( );
        if (products.isEmpty ( )) {
            return null;
        }
        return products;
    }
}
