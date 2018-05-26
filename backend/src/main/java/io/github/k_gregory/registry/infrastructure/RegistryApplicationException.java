package io.github.k_gregory.registry.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegistryApplicationException extends RuntimeException {
    public RegistryApplicationException(String message) {
        super(message);
    }

    public static <T> T checked(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<T> object, String message) {
        return object.orElseThrow(() -> new RegistryApplicationException(message));
    }
}
