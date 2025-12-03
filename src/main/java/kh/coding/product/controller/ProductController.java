package kh.coding.product.controller;


import kh.coding.product.dto.ProductCreateRequest;
import kh.coding.product.dto.ProductResponse;
import kh.coding.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasAuthority('SCOPE_write')")
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAuthority('SCOPE_write')")
    @PutMapping("/{uuid}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String uuid,
            @Valid @RequestBody ProductCreateRequest request) {
        ProductResponse response = productService.updateProduct(uuid, request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<ProductResponse> getProductByUuid(@PathVariable String uuid) {
        ProductResponse response = productService.getProductByUuid(uuid);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('SCOPE_write')")
    public ResponseEntity<Void> deleteProductByUuid(@PathVariable String uuid) {
        productService.deleteProductByUuid(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        Page<ProductResponse> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }
}