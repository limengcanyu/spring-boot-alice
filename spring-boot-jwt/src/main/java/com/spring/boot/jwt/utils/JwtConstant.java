package com.spring.boot.jwt.utils;

import java.util.UUID;

public class JwtConstant {
    public static final String JWT_ID = UUID.randomUUID().toString();
    public static final String JWT_SECRET = "LookWhatYouMadeMeDoWeAreNeverEverGettingBackTogetherEverythingHasChanged";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
}
