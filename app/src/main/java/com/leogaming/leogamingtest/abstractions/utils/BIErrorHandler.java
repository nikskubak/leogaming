package com.leogaming.leogamingtest.abstractions.utils;

public interface BIErrorHandler {


    void processNetError(Throwable throwable);

    void processStatusCode(int statusCode);
}
