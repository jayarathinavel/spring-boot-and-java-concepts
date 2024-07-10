package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.service;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.checkout.Cart;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.checkout.CheckoutResponse;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.checkout.CheckoutStatus;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.DataSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void checkNoOfCores(){
        System.out.println("No of cores : " + Runtime.getRuntime().availableProcessors());
    }
    @Test
    void checkout() {
        /* Given */
        Cart cart = DataSet.createCart(6);
        /* When */
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        /* Then */
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());

    }

    @Test
    void checkout10Products() {
        /* Since the products is greater than available cores, it is going to take 1secs instead of 0.5*/
        /* Given */
        Cart cart = DataSet.createCart(10);
        /* When */
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        /* Then */
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());

    }
}