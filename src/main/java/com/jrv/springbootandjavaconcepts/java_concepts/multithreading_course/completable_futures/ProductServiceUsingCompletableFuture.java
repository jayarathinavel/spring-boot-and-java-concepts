package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.completable_futures;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.*;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.InventoryService;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.ProductInfoService;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.ReviewService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.stopWatch;
import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.timeTaken;
import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.LoggerUtil.log;

public class ProductServiceUsingCompletableFuture {
    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    private InventoryService inventoryService;

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService, InventoryService inventoryService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
        this.inventoryService = inventoryService;
    }

    /* This approach has blocking code*/
    public Product retrieveProductDetails(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> productInfoCompletableFuture =
                CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));

        CompletableFuture<Review> reviewCompletableFuture =
                CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = productInfoCompletableFuture.thenCombine(reviewCompletableFuture, (productInfo, review) -> new Product(productId, productInfo, review))
                .join();

        /* Join is a Blocking code*/
/*
        ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking call
        Review review = reviewService.retrieveReviews(productId); // blocking call
*/

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }

    /* This approach has no blocking code*/
    public CompletableFuture<Product> retrieveProductDetailsApproach2(String productId) {
        stopWatch.start();
        CompletableFuture<ProductInfo> productInfoCompletableFuture =
                CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));

        CompletableFuture<Review> reviewCompletableFuture =
                CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));
        timeTaken();
        return productInfoCompletableFuture.thenCombine(reviewCompletableFuture, (productInfo, review) -> new Product(productId, productInfo, review));
    }

    /* Combining streams and Completable Future */
    public Product retrieveProductDetailsWithInventory(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> productInfoCompletableFuture =
                CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                        .thenApply(productInfo -> {
                            productInfo.setProductOptions(updateInventory(productInfo));
                            return productInfo;
                        });

        CompletableFuture<Review> reviewCompletableFuture =
                CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = productInfoCompletableFuture.thenCombine(reviewCompletableFuture, (productInfo, review) -> new Product(productId, productInfo, review))
                .join();

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }

    public Product retrieveProductDetailsWithInventoryApproach2(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> productInfoCompletableFuture =
                CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                        .thenApply(productInfo -> {
                            productInfo.setProductOptions(updateInventoryApproach2(productInfo));
                            return productInfo;
                        });

        CompletableFuture<Review> reviewCompletableFuture =
                CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = productInfoCompletableFuture.thenCombine(reviewCompletableFuture, (productInfo, review) -> new Product(productId, productInfo, review))
                .join();

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }

    private List<ProductOption> updateInventory(ProductInfo productInfo) {
        List<ProductOption> productOptionList = productInfo.getProductOptions()
                .stream()
                .map(productOption -> {
                    Inventory inventory = inventoryService.retrieveInventory(productOption);
                    productOption.setInventory(inventory);
                    return productOption;
                })
                .collect(Collectors.toList());
        return productOptionList;
    }

    private List<ProductOption> updateInventoryApproach2(ProductInfo productInfo) {
        List<CompletableFuture<ProductOption>> productOptionList = productInfo.getProductOptions()
                .stream()
                .map(productOption -> {
                    return CompletableFuture.supplyAsync(() -> inventoryService.retrieveInventory(productOption))
                            .thenApply(inventory -> {
                                productOption.setInventory(inventory);
                                return productOption;
                            });
                }).collect(Collectors.toList());
        return productOptionList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }


    public Product retrieveProductDetailsWithInventoryAndExceptionHandling(String productId) {
        stopWatch.start();
        CompletableFuture<ProductInfo> productInfoCompletableFuture =
                CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                        .thenApply(productInfo -> {
                            productInfo.setProductOptions(updateInventoryApproach2(productInfo));
                            return productInfo;
                        });
        CompletableFuture<Review> reviewCompletableFuture =
                CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId))
                        .exceptionally((e) -> {
                            log("Handled Exceptions in reviewService : " + e);
                            return Review.builder()
                                    .noOfReviews(0)
                                    .overallRating(0.0)
                                    .build();
                        });

        Product product = productInfoCompletableFuture.thenCombine(reviewCompletableFuture, (productInfo, review) -> new Product(productId, productInfo, review))
                .join();

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }


    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceUsingCompletableFuture productService = new ProductServiceUsingCompletableFuture(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }
}
