package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service;


import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.checkout.CartItem;

import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.delay;
import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.LoggerUtil.log;

public class PriceValidatorService {

    public boolean isCartItemInvalid(CartItem cartItem){
        int cartId = cartItem.getItemId();
        log("isCartItemInvalid : "+ cartItem);
        delay(500);
        if (cartId == 7 || cartId == 9 || cartId == 11) {
            return true;
        }
        return false;
    }
}
