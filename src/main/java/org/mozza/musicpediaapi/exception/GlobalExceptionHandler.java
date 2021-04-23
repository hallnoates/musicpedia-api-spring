package org.mozza.musicpediaapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.mozza.musicpediaapi.common.ResponseObject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice // @ControllerAdvice + @ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    protected ResponseEntity<ResponseObject> handlerDuplicateKeyException(RuntimeException e, WebRequest request) {
        log.error("duplicateKey error");
        return ResponseEntity.ok().body(ResponseObject.of(400,null,e.getMessage()));
    }

}
