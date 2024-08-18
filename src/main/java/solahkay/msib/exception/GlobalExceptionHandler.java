package solahkay.msib.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import solahkay.msib.dto.WebResponse;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebResponse<String>> constraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder()
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .errors(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> apiException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(WebResponse.<String>builder()
                        .message(exception.getMessage())
                        .errors(exception.getReason())
                        .build());
    }

    @ExceptionHandler(InvalidMediaTypeException.class)
    public ResponseEntity<WebResponse<String>> invalidMediaTypeException(InvalidMediaTypeException exception) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(WebResponse.<String>builder()
                        .message(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())
                        .errors(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<WebResponse<String>> invalidFormatException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder()
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .errors("Wrong format data")
                        .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<WebResponse<String>> httpMessageNotReadableException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder()
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .errors("Wrong format data")
                        .build());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<WebResponse<String>> exception(IOException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder()
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .errors(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebResponse<String>> exception() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(WebResponse.<String>builder()
                        .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .errors("Can't process request because of server error")
                        .build());
    }


}
