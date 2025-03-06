package com.pokemon.angular_pokemon.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

// Classe responsável para a comunicação com o usuário em relação à alguma exception.
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
