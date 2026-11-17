package com.example.FlashSaleInventory.Service.Model;

import com.example.FlashSaleInventory.Model.Product;
import com.example.FlashSaleInventory.Repository.ProductRepository;
import com.example.FlashSaleInventory.SaveProductRequest;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product create(SaveProductRequest request) {
        if (productRepository.existsBySku(request.getSku())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "A product with this SKU already exists"
            );
        }

        Product product = new Product();
        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setActive(request.getActive());

        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product getBySku(String sku) {
        return productRepository.findBySku(sku)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found"
                ));
    }
}