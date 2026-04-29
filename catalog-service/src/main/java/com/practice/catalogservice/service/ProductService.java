    package com.practice.catalogservice.service;

    import com.practice.catalogservice.dtos.PagedResult;
    import com.practice.catalogservice.exceptions.ProductNotFoundException;
    import com.practice.catalogservice.exceptions.ResourceNotFoundException;
    import com.practice.catalogservice.model.Product;
    import com.practice.catalogservice.repository.ProductRepository;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.domain.Sort;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import java.util.List;

    @Service
    @Transactional
    public class ProductService {
        private final ProductRepository productRepository;

        public ProductService(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        @Transactional(readOnly = true)
        public PagedResult<Product> getProducts(int pageNo) {
            pageNo = pageNo <= 1 ? 0 : pageNo-1;
            Pageable pageable = PageRequest.of(pageNo, 10, Sort.by("name").ascending());
            Page<Product> productsPage = productRepository.findAll(pageable);

            return new PagedResult<>(
                    productsPage.getContent(),
                    productsPage.getTotalElements(),
                    productsPage.getNumber() +1,
                    productsPage.getTotalPages(),
                    productsPage.isFirst(),
                    productsPage.isLast(),
                    productsPage.hasNext(),
                    productsPage.hasPrevious()
            );
        }

        @Transactional(readOnly = true)
        public Product getProductByCode(String code) {
            return productRepository.findByCode(code)
                    .orElseThrow(() -> new ProductNotFoundException(code));
        }
    }

