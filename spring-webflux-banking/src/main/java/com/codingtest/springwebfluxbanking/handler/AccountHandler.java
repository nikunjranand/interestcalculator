package com.codingtest.springwebfluxbanking.handler;

import com.codingtest.springwebfluxbanking.model.AccountBalanceEvent;
import com.codingtest.springwebfluxbanking.model.AccountCreateEvent;
import com.codingtest.springwebfluxbanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@Component
public class AccountHandler {

    @Autowired
    private AccountService accountService;

    public Mono<ServerResponse> streamCreateAccount(ServerRequest request) {
        try {
            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(accountService.accountCreateStream(), AccountCreateEvent.class);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public Mono<ServerResponse> streamBalanceAccount(ServerRequest request)  {
        try {
            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(accountService.accountBalanceStream(), AccountBalanceEvent.class);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}


