package appAmirSalyakhov.raiffeisenTask.advice;

import appAmirSalyakhov.raiffeisenTask.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice()
public class SocksControllerExceptionHandler extends RuntimeException {

    @ExceptionHandler({RuntimeException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ExceptionResponse> handleRunTimeException(RuntimeException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        ExceptionResponse response = new ExceptionResponse(message);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleNotReadableException(HttpMessageNotReadableException e) {
        String message = String.format("%s %s", LocalDateTime.now(), "Please check JSON format of you Request");
        ExceptionResponse response = new ExceptionResponse(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> validationHandlerException(ConstraintViolationException e) {
        String message = String.format("%s %s", LocalDateTime.now(),"Invalid request exception, please check one of the param");
        ExceptionResponse response = new ExceptionResponse(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
