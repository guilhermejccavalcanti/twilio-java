package com.twilio.rest.exception;

public class InvalidRequestException extends TwilioException {

    private static final long serialVersionUID = 1L;

    private final String param;

    public InvalidRequestException(final String message, final String param, final Throwable cause) {
        super(message, cause);
        this.param = param;
    }

    public String getParam() {
        return param;
    }

}
