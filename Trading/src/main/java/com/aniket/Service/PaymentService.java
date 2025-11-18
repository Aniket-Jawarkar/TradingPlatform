package com.aniket.service;

import com.aniket.domain.PaymentMethod;
import com.aniket.model.PaymentOrder;
import com.aniket.model.User;
import com.aniket.response.PaymentResponse;
import com.razorpay.RazorpayException;

public interface PaymentService  {

    PaymentOrder createOrder (User user, Long amount,
    PaymentMethod paymentMethod);
    PaymentOrder getPaymentOrderById (Long id) throws Exception;

    Boolean ProccedPaymentOrder (PaymentOrder paymentOrder, String paymentId) throws RazorpayException;
    PaymentResponse createRazorpayPaymentLing(User user, Long amount) throws RazorpayException;
    PaymentResponse createStripePaymentLing (User user, Long amount, Long orderId);
}
