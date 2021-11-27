package com.gov.assembleia.assembleiaservice.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

  public NotFoundException(String mensagem){ this(mensagem,null);}

  public NotFoundException(String mensagem, Throwable motivo){ super(mensagem,motivo);}
}
