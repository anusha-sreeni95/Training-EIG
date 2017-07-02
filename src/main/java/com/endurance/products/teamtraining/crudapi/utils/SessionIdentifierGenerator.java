package com.endurance.products.teamtraining.crudapi.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.math.BigInteger;

@Component
public final class SessionIdentifierGenerator {
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}