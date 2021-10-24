package com.codingtest.springwebfluxbanking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCreateEvent {

    private Account account;
    private LocalDateTime eventCreationDate;

}
