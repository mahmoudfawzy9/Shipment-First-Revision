package com.company.star.exception;

import org.springframework.http.HttpStatus;

public class FcmException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private HttpStatus status;
    private Integer tokenId;

    public FcmException(String message)
    {
        super(message);
    }

    public FcmException(HttpStatus status,String message,Throwable cause,Integer tokenId)
    {
        super(message, cause);
        this.status=status;
        this.tokenId=tokenId;
    }

    public HttpStatus getStatus()
    {
        return this.status;
    }
    public Integer getTokenId()
    {
        return this.tokenId;
    }
}
