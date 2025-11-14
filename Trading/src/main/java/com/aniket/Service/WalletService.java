package com.aniket.service;

import com.aniket.model.Order;
import com.aniket.model.User;
import com.aniket.model.Wallet;

public interface WalletService {

    Wallet getUserWallet(User user);
    Wallet addBalance(Wallet wallet, Long money);
    Wallet findWalletById(Long id) throws Exception;

    Wallet walletToWalletTransfer(User sender , Wallet reciverWallet, Long amount) throws Exception;

    Wallet payOrderPayment(Order order , User user) throws Exception;

}
