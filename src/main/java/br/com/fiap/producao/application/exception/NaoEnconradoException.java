package br.com.fiap.producao.application.exception;

public class NaoEnconradoException extends RuntimeException{

    public NaoEnconradoException(String mensagem){
        super(mensagem);
    }

    public NaoEnconradoException(String mensagem, Throwable e){
        super(mensagem, e);
    }

}
