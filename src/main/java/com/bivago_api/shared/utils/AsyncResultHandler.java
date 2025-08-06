package com.bivago_api.shared.utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.server.ResponseStatusException;

public class AsyncResultHandler {
 
    public static <T> T await(CompletableFuture<T> future) {
        try { return future.join(); }
        catch (CompletionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IllegalArgumentException iae) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iae.getMessage());
            if (cause instanceof ResponseStatusException rse) throw rse;
            if (cause instanceof AuthenticationException) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credentials");

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected async error", e);
        }
    }

}
