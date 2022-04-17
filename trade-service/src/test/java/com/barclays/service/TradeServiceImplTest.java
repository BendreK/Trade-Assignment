package com.barclays.service;

import com.barclays.dto.TradeDto;
import com.barclays.model.Trade;
import com.barclays.repository.TradeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeServiceImpl tradeServiceImpl;

    @Mock
    private ModelMapper modelMapper;

    static List<TradeDto> tradeDtoList;
    static TradeDto tradeDto;

    static List<Trade> tradeList;
    static Trade trade;

    @BeforeAll
    public static void setUp() {
        tradeDtoList = new ArrayList<TradeDto>();
        tradeDto = new TradeDto();

        tradeList = new ArrayList<Trade>();
        trade = new Trade();

        String created_date = "2022-04-17";
        String maturity_date = "2023-07-21";
        String created_date1 = "2022-04-17";
        String maturity_date1 = "2023-07-21";

        trade.setVersion(2);
        trade.setBook_Id("B1");
        trade.setExpired('N');
        trade.setTrade_Id("T1");
        trade.setCreated_date(LocalDate.parse(created_date));
        trade.setMaturity_date(LocalDate.parse(maturity_date));
        trade.setCounter_party_Id("CP-1");
        tradeList.add(trade);

        tradeDto.setTrade_Id("T1");
        tradeDto.setBook_Id("B1");
        tradeDto.setCounter_party_Id("CP-1");
        tradeDto.setCreated_date(LocalDate.parse(created_date1));
        tradeDto.setMaturity_date(LocalDate.parse(maturity_date1));
        tradeDto.setExpired('N');
        tradeDto.setVersion(2);

        tradeDtoList.add(tradeDto);
    }


    @Test
    @DisplayName("Get All Trade Details")
    void getAllTrade() {

        Mockito.when(tradeRepository.findAll()).thenReturn(tradeList);

        List<TradeDto> list = tradeList.stream().map(trade -> modelMapper.map(trade, TradeDto.class))
                .collect(Collectors.toList());

        List<TradeDto> result = tradeServiceImpl.getAllTrade();

        assertEquals(list, result);
    }

    @Test
    @DisplayName("Save Trade Data")
    void saveTrade() {
        Mockito.when(modelMapper.map(tradeDto,Trade.class)).thenReturn(trade);
        Mockito.when(tradeRepository.findById(trade.getTrade_Id())).thenReturn(java.util.Optional.ofNullable(trade));
        Mockito.when(tradeRepository.save(trade)).thenReturn(trade);
        Mockito.when(modelMapper.map(trade,TradeDto.class)).thenReturn(tradeDto);
        TradeDto result = tradeServiceImpl.saveTrade(tradeDto);
        assertEquals(tradeDto, result);
    }

}