package com.barclays.controller;

import com.barclays.dto.TradeDto;
import com.barclays.model.Trade;
import com.barclays.service.TradeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeInterface tradeInterface;

    @GetMapping("/getTrade_Details")
    public List<TradeDto> getAllTrade() {
        return tradeInterface.getAllTrade();
    }

    @PostMapping("/saveTrade")
    public ResponseEntity<TradeDto> saveTradeData(@RequestBody TradeDto trade){
        TradeDto tradeRes = tradeInterface.saveTrade(trade);
        return ResponseEntity.ok().body(tradeRes);
    }


}
