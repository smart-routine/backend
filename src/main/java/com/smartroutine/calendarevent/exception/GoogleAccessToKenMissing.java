package com.smartroutine.calendarevent.exception;

public class GoogleAccessToKenMissing extends IllegalArgumentException {
    public GoogleAccessToKenMissing() {
        super("Google access token is null or empty");
    }
}
