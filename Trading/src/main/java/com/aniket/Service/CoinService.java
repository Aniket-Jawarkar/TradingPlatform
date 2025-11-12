package com.aniket.service;


import com.aniket.model.Coin;

import java.util.List;

public interface CoinService {

    List<Coin> getCoinList(int page) throws Exception;

    String getMarketChart(String coinId, int days) throws Exception;

    String getCoinDetails(String coinId) throws Exception;

    Coin findById(String coinId) throws Exception;

    String serachCoin(String keyword) throws Exception;

    String getTop50CoinsByMarketCapRank() throws Exception;

    String GetTreadingCoins() throws Exception;


}
