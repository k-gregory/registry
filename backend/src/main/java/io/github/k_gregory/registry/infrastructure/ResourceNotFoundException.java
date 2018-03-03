package io.github.k_gregory.registry.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public static <T> void throwIfNull(T object) {
        if (object == null)
            throw new ResourceNotFoundException();
    }

    public static <T> T getOr404(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<T> object) {
        return object.orElseThrow(ResourceNotFoundException::new);
    }
}
