package kh.coding.product.service;

import kh.coding.product.dto.ProductCreateRequest;
import kh.coding.product.dto.ProductResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse createProduct(ProductCreateRequest request);
    ProductResponse updateProduct(String uuid, ProductCreateRequest request);
    ProductResponse getProductByUuid(String uuid);
    void deleteProductByUuid(String uuid);
    Page<ProductResponse> getAllProducts(Pageable pageable);
    boolean existsByUuid(String uuid);
}
