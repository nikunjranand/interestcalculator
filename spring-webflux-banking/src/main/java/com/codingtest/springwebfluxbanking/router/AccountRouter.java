package com.codingtest.springwebfluxbanking.router;

import com.codingtest.springwebfluxbanking.handler.AccountHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import java.text.ParseException;

@Configuration
@EnableWebFlux
public class AccountRouter {

    @Bean
    RouterFunction createAccount(AccountHandler accountHandler) throws ParseException {
            return RouterFunctions.route(RequestPredicates.GET("/createAccount"), accountHandler::streamCreateAccount);
    }

    @Bean
    RouterFunction balanceAccount(AccountHandler accountHandler) throws ParseException  {
        return RouterFunctions.route(RequestPredicates.GET("/balanceAccount"), accountHandler::streamBalanceAccount);

    }

}
