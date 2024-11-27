package com.tienda.service;

import jakarta.mail.MessagingException;

public interface CorreoService {
    public void enviarCorreoHtml(
            String para, 
            String asunto, 
            String contenidoHtml) 
            throws MessagingException; //Este metodo puede mostrar una excepcion, es un aviso
}

