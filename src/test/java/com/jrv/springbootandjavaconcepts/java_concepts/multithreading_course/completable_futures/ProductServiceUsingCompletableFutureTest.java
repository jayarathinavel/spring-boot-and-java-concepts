package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.completable_futures;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.Product;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.InventoryService;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.ProductInfoService;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.ReviewService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {

    private ProductInfoService productInfoService = new ProductInfoService();
    private ReviewService reviewService = new ReviewService();

    private InventoryService inventoryService = new InventoryService();
    ProductServiceUsingCompletableFuture productServiceUsingCompletableFuture
            = new ProductServiceUsingCompletableFuture(productInfoService, reviewService, inventoryService);

    @Test
    void retrieveProductDetails() {
        String productId = "ABC123";

        Product product = productServiceUsingCompletableFuture.retrieveProductDetails(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size()>0);
        assertNotNull(product.getReview());
    }
    @Test
    void retrieveProductDetailsApproach2() {
        String productId = "ABC123";

        Product product = productServiceUsingCompletableFuture.retrieveProductDetailsApproach2(productId).join();

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size()>0);
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetailsApproachWithInventory() {
        String productId = "ABC123";

        Product product = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventory(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size()>0);
        product.getProductInfo().getProductOptions().forEach(productOption -> {
            assertNotNull(productOption.getInventory());
        });
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetailsApproachWithInventoryApproach2() {
        String productId = "ABC123";

        Product product = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventoryApproach2(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size()>0);
        product.getProductInfo().getProductOptions().forEach(productOption -> {
            assertNotNull(productOption.getInventory());
        });
        assertNotNull(product.getReview());
    }
}