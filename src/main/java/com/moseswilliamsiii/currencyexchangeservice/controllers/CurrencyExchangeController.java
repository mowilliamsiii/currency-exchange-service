package com.moseswilliamsiii.currencyexchangeservice.controllers;

import com.moseswilliamsiii.currencyexchangeservice.model.CurrencyExchange;
import com.moseswilliamsiii.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    //autowire
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
//        CurrencyExchange currencyExchange = new CurrencyExchange(30, "USD", "INR", BigDecimal.valueOf(65));
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if(currencyExchange == null){
            throw new RuntimeException("Unable to find data where from = " + from + " and to = " + to);
        }
        String property = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(property);
        return currencyExchange;
    }




}
