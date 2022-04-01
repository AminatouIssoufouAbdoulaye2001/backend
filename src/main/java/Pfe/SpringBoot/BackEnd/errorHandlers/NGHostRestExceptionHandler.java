package Pfe.SpringBoot.BackEnd.errorHandlers;

import Pfe.SpringBoot.BackEnd.dtos.NGHostRequestErrorDTO;
import Pfe.SpringBoot.BackEnd.exceptions.NGHost400Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class NGHostRestExceptionHandler {

    @ExceptionHandler(value = {NGHost400Exception.class})
    public ResponseEntity<Object> handleAPIResquestException(NGHost400Exception ex) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        NGHostRequestErrorDTO error = new NGHostRequestErrorDTO(
                badRequest.value(),
                ex.getMessage()
        );
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(error, badRequest);
    }
}
