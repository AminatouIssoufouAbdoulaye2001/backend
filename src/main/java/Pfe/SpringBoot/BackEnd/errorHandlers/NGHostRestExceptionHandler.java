package Pfe.SpringBoot.BackEnd.errorHandlers;

import Pfe.SpringBoot.BackEnd.dtos.NGHostRequestErrorDTO;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost400Exception;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost401Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Slf4j
public class NGHostRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NGHost400Exception.class)
    public ResponseEntity<Object> handleAPIResquestException(NGHost400Exception ex) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        NGHostRequestErrorDTO error = new NGHostRequestErrorDTO(
                "error",
                ex.getMessage(),
                badRequest.value()
        );
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(error, badRequest);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleAPIResquestException(final EntityNotFoundException ex) {
        HttpStatus multiStatus = HttpStatus.MULTI_STATUS;
        NGHostRequestErrorDTO error = new NGHostRequestErrorDTO(
                "error",
                ex.getMessage(),
                multiStatus.value()
        );
        return new ResponseEntity<>(error, multiStatus);
    }

    @ExceptionHandler(NGHost401Exception.class)
    protected ResponseEntity<Object> handleAPIResquestException(final NGHost401Exception ex) {
        HttpStatus unauthorizedStatus = HttpStatus.UNAUTHORIZED;
        NGHostRequestErrorDTO error = new NGHostRequestErrorDTO(
                "error",
                ex.getMessage(),
                unauthorizedStatus.value()
        );
        return new ResponseEntity<>(error, unauthorizedStatus);
    }

}
