package org.ipn.mx.administracioneventos.util.service;

public interface EmailService {

    public void enviarEmail(String to, String subject, String text);
}
