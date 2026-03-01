package stream.lexlab.simplecms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.sqlite.SQLiteException;
import stream.lexlab.simplecms.exceptions.PageNotFound;

import java.io.IOException;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PageNotFound.class)
    public ResponseEntity<Map.Entry> handleNotFound(PageNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.entry("error",ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map.Entry> handleIllegalArguments(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.entry("error",ex.getMessage()));
    }

    @ExceptionHandler(SQLiteException.class)
    public ResponseEntity<Map.Entry> handleSQLiteException(SQLiteException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.entry("error",ex.getMessage()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map.Entry> handleSQLiteException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.entry("error",ex.getMessage()));
    }
}
