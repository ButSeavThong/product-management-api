package kh.coding.product.service;

import kh.coding.product.domain.Product;
import kh.coding.product.dto.ProductCreateRequest;
import kh.coding.product.dto.ProductResponse;
import kh.coding.product.exception.ProductNotFoundException;
import kh.coding.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .description(request.description())
                .image(request.image())
                .build();

        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(String uuid, ProductCreateRequest request) {
        Product product = productRepository.findByUuidAndDeletedFalse(uuid)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with UUID: " + uuid));

        // Update only non-null fields (partial update)
        if (request.name() != null) {
            product.setName(request.name());
        }
        if (request.price() != null) {
            product.setPrice(request.price());
        }
        if (request.description() != null) {
            product.setDescription(request.description());
        }
        if (request.image() != null) {
            product.setImage(request.image());
        }

        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductByUuid(String uuid) {
        Product product = productRepository.findByUuidAndDeletedFalse(uuid)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with UUID: " + uuid));
        return mapToResponse(product);
    }

    @Override
    public void deleteProductByUuid(String uuid) {
        Product product = productRepository.findByUuidAndDeletedFalse(uuid)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with UUID: " + uuid));

        product.setDeleted(true);
        product.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAllByDeletedFalse(pageable)
                .map(this::mapToResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUuid(String uuid) {
        return productRepository.existsByUuidAndDeletedFalse(uuid);
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .uuid(product.getUuid())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .image(product.getImage())
                .build();
    }

    // Add a builder pattern to Product entity
    public static class ProductBuilder {
        private String name;
        private Double price;
        private String description;
        private String image;

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder image(String image) {
            this.image = image;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setImage(image);
            return product;
        }
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }
}