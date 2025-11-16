package com.aniket.service;

import com.aniket.model.Coin;
import com.aniket.model.User;
import com.aniket.model.Watchlist;

public interface WatchlistService {
    Watchlist findUserWatchlist (Long userId) throws Exception;
    Watchlist createWatchlist (User user);
    Watchlist findById(Long id) throws Exception;
    Coin addItemToWatchlist (Coin coin, User user) throws Exception;
}
