package com.barclays.scheduler;

import com.barclays.service.TradeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TradeScheduler {

    private static final Logger log = LoggerFactory.getLogger(TradeScheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Autowired
    private TradeInterface tradeInterface;


    @Scheduled(cron="*/60 * * * * ?")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        tradeInterface.updateExpiryFlagOfTrade();
    }
}
