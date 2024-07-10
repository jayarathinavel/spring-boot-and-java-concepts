package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.completable_futures;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.Product;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.InventoryService;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.ProductInfoService;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureAndExceptionHandlingTest {

    @Mock
    private ProductInfoService productInfoServiceMock;

    @Mock
    private ReviewService reviewServiceMock;

    @Mock
    private InventoryService inventoryServiceMock;

    @InjectMocks
    ProductServiceUsingCompletableFuture productServiceUsingCompletableFuture;



    @Test
    void retrieveProductDetailsWithInventoryAndExceptionHandling() {
        String productId = "ABC123";

        when(productInfoServiceMock.retrieveProductInfo(any())).thenCallRealMethod();
        when(reviewServiceMock.retrieveReviews(any())).thenThrow(new RuntimeException());
        when(inventoryServiceMock.retrieveInventory(any())).thenCallRealMethod();

        Product product = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventoryAndExceptionHandling(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size()>0);
        product.getProductInfo().getProductOptions().forEach(productOption -> {
            assertNotNull(productOption.getInventory());
        });
        assertNotNull(product.getReview());

    }
}