package com.barclays.controller;

import com.barclays.dto.TradeDto;
import com.barclays.service.TradeInterface;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TradeControllerTest {

    @Mock
    TradeInterface tradeInterface;

    @InjectMocks
    TradeController tradeController;

    static List<TradeDto> tradeDtoList;
    static TradeDto tradeDto;

    @BeforeAll
    public static void setUp() {
        tradeDtoList=new ArrayList<TradeDto>();
        tradeDto= new TradeDto();
        tradeDto.setTrade_Id("T1");
        tradeDto.setBook_Id("B1");
        tradeDto.setCounter_party_Id("CP-1");
        String created_date="2022-04-17";
        String maturity_date="2021-04-21";
        tradeDto.setCreated_date(LocalDate.parse(created_date));
        tradeDto.setMaturity_date(LocalDate.parse(maturity_date));
        tradeDto.setExpired('N');
        tradeDto.setVersion(2);

        tradeDtoList.add(tradeDto);
    }

    @Test
    @DisplayName("Get all Trade Details")
    void getAllTrade() {
    Mockito.when(tradeInterface.getAllTrade()).thenReturn(tradeDtoList);
        // when or event
        List<TradeDto> result= tradeController.getAllTrade();
        // when or event
        verify(tradeInterface).getAllTrade();
        //outcome
        assertEquals(tradeDtoList, result);
    }

    @Test
    @DisplayName("Save Trade Data")
    void saveTradeData() {
        when(tradeInterface.saveTrade(tradeDto)).thenReturn(tradeDto);
        ResponseEntity<TradeDto> result= tradeController.saveTradeData(tradeDto);
        verify(tradeInterface).saveTrade(tradeDto);
        assertEquals(result.getStatusCodeValue() /*actual value*/, 200 /*expected value*/, "Record saved successfully");

    }
}