package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.checkout.Cart;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.checkout.CartItem;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.checkout.CheckoutResponse;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.checkout.CheckoutStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.startTimer;
import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.timeTaken;

public class CheckoutService {
    private PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public CheckoutResponse checkout(Cart cart){
        startTimer();
        List<CartItem> priceValidationList = cart.getCartItemList()
                .stream()
                .parallel()
                .map(cartItem -> {
                    boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                    cartItem.setExpired(isPriceInvalid);
                    return cartItem;
                })
                .filter(CartItem::isExpired)
                .collect(Collectors.toList());
        timeTaken();
        if(priceValidationList.size() > 0){
            return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
        }

        return new CheckoutResponse(CheckoutStatus.SUCCESS);
    }
}
