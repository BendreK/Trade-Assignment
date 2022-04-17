package com.barclays.service;

import com.barclays.dto.TradeDto;
import com.barclays.exception.TradeNotFoundException;
import com.barclays.model.Trade;
import com.barclays.repository.TradeRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.RollbackException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TradeServiceImpl implements TradeInterface {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private ModelMapper modelMapper;


    private static final Logger log = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Override
    public List<TradeDto> getAllTrade() {
        log.info("Inside service of getAllEmployeeList.");
        List<Trade> tradeList = tradeRepository.findAll();
        return tradeList.stream().map(trade -> modelMapper.map(trade, TradeDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TradeDto saveTrade(TradeDto trade) throws TradeNotFoundException {
        Trade tradeObj= modelMapper.map(trade,Trade.class);
        if(isValidTrade(tradeObj)) {
            tradeObj.setCreated_date(LocalDate.now());
                log.info("Trade Data to be saved [{}]", tradeObj);
            Trade tradeRes =  tradeRepository.save(tradeObj);
            return modelMapper.map(tradeRes,TradeDto.class);
        }else{
            throw  new TradeNotFoundException("Invalid version received: Latest version is -> " +tradeObj.getVersion());
        }

    }

    public boolean isValidTrade(Trade trade){
        if(validateTradeMaturityDate(trade)) {
            Optional<Trade> exsitingTrade = tradeRepository.findById(trade.getTrade_Id());
            if (exsitingTrade.isPresent()) {
                return validateTradeVersion(trade, exsitingTrade.get());
            }else{
                return true;
            }
        }else {
            throw  new TradeNotFoundException("Maturity date is less than todays date.");
        }

    }

    private boolean validateTradeVersion(Trade trade,Trade oldTrade) {
        if(trade.getVersion() >= oldTrade.getVersion()){
            log.info("trade.getVersion()",trade.getVersion());
            return true;
        }
        return false;
    }

    private boolean validateTradeMaturityDate(Trade trade){
        return trade.getMaturity_date().isBefore(LocalDate.now())  ? false:true;
    }

   @Transactional
    public void updateExpiryFlagOfTrade(){
        tradeRepository.findAll().stream().forEach(tread -> {
            if (!validateTradeMaturityDate(tread)) {
                tread.setExpired('Y');
                log.info("Trade which needs to updated {}", tread);
                tradeRepository.save(tread);
            }
        });
    }

}
