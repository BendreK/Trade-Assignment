package com.barclays.service;

import com.barclays.dto.TradeDto;
import com.barclays.model.Trade;

import java.util.List;

public interface TradeInterface {

    List<TradeDto> getAllTrade();
    TradeDto saveTrade(TradeDto trade);
    void updateExpiryFlagOfTrade();
}
