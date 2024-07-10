package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.executor;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.Product;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.ProductInfo;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.Review;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.ProductInfoService;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service.ReviewService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.stopWatch;
import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.LoggerUtil.log;

public class ProductServiceUsingExecutor {
    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductServiceUsingExecutor(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) throws ExecutionException, InterruptedException {
        stopWatch.start();

        /* Concurrency using Executor Service*/
        Future<ProductInfo> productInfoFuture = executorService.submit(() -> productInfoService.retrieveProductInfo(productId));
        Future<Review> reviewFuture = executorService.submit(() -> reviewService.retrieveReviews(productId));

        ProductInfo productInfo = productInfoFuture.get();
        Review review = reviewFuture.get();
/*
        ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking call
        Review review = reviewService.retrieveReviews(productId); // blocking call
*/

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceUsingExecutor productService = new ProductServiceUsingExecutor(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);
        executorService.shutdown();
    }
}
