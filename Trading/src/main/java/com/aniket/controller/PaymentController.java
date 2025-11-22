package com.aniket.controller;


import com.aniket.domain.PaymentMethod;
import com.aniket.model.PaymentOrder;
import com.aniket.model.User;
import com.aniket.response.PaymentResponse;
import com.aniket.service.PaymentService;
import com.aniket.service.UserService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private UserService userService;
    private PaymentService paymentService;

    public PaymentController(UserService userService, PaymentService paymentService) {
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @PostMapping("/api/payment/{paymentMethod}/amount/{amount}")
    public ResponseEntity<PaymentResponse> paymentHandler(
            @PathVariable PaymentMethod paymentMethod,
            @PathVariable Long amount,
            @RequestHeader("Authorization") String jwt) throws
            Exception,
            RazorpayException,
            StripeException {
        User user = userService.findUserProfileByJwt(jwt);

        PaymentResponse paymentResponse;

        PaymentOrder order = paymentService.createOrder (user, amount, paymentMethod);
        if (paymentMethod.equals (PaymentMethod. RAZORPAY)) {
            paymentResponse=paymentService.createRazorpayPaymentLing(user, amount, order.getId());
        }
        else{
            paymentResponse=paymentService.createStripePaymentLing(user, amount, order.getId());
        }
        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }
}
