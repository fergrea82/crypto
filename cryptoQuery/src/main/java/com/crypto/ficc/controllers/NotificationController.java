package com.crypto.ficc.controllers;

import com.crypto.ficc.model.TickerResult;
import com.crypto.ficc.notify.NotifyService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("notifyCcy")
@RequestMapping("/notifyCcy")
@Slf4j
@Getter
@Setter
public class NotificationController {
    @Autowired
    private NotifyService notifyService;

    @GetMapping(value="/ccy/{currency}/{stake}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TickerResult> getCcy(@PathVariable("currency") String currency,
                                               @PathVariable("stake") String stake){
        try {
            notifyService.getTickerForCoin(currency,stake);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }
}
