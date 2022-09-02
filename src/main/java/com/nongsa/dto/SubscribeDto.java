package com.nongsa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
    private BigInteger id;
    private String username;
    private BigInteger subscribeState;
    private BigInteger equalUserState;
}
