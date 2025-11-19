package com.aniket.service;

import com.aniket.model.PaymentDetails;
import com.aniket.model.User;

public interface PaymentDetailsService {
    public PaymentDetails addPaymentDetails (String accountNumber,
                                             String accountHolderName,
                                             String ifsc,
                                             String bankName,
                                             User user);
    public PaymentDetails getUsersPaymentDetails (User user);
}
