package io.github.k_gregory.registry.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegistryApplicationException extends RuntimeException {
    public RegistryApplicationException(String message) {
        super(message);
    }
}
