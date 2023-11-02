package easybroker.easybroker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<MessageRequestException> tratarErro(Exception exceptionx){

        MessageRequestException message = new MessageRequestException(
            new Date(),
            HttpStatus.UNAUTHORIZED.value(),
            "Não autorizao",
            exceptionx.getMessage()
        );

        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }
}
