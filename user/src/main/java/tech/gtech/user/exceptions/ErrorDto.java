package tech.gtech.user.exceptions;

import java.time.LocalDateTime;

public record ErrorDto(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
