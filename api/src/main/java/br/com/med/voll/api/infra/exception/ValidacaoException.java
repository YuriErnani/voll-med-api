package br.com.med.voll.api.infra.exception;

public class ValidacaoException extends RuntimeException {

    public ValidacaoException(String mensagem) {
        super(mensagem);
    }

}
