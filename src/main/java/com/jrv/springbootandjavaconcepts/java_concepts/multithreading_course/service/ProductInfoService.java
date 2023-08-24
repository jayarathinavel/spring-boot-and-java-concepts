package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.ProductInfo;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.ProductOption;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.LoggerUtil;
import java.util.List;

import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.delay;

public class ProductInfoService {

    public ProductInfo retrieveProductInfo(String productId) {
        delay(1000);
        List<ProductOption> productOptions = List.of(new ProductOption(1, "64GB", "Black", 699.99),
                new ProductOption(2, "128GB", "Black", 749.99));
        LoggerUtil.log("retrieveProductInfo after Delay");
        return ProductInfo.builder().productId(productId)
                .productOptions(productOptions)
                .build();
    }
}
