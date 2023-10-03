package com.tew.presentation;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="error")
@SessionScoped
public class ErrorBean implements Serializable {

    private static final long serialVersionUID = 10L;
    
    
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private final PrintStream originalStderr = System.err;
    private final PrintStream customStream = new PrintStream(baos);

    @PostConstruct
    public void init() {
        // Redirige stderr al ByteArrayOutputStream
        System.setErr(customStream);
    }

    @PreDestroy
    public void destroy() {
        // Restaura stderr al original
        System.setErr(originalStderr);
    }

    public String getErrors() {
        try {
            Thread.sleep(100);  // Introduce un retraso de 100 milisegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return baos.toString();
    }

    public String redirectToErrorPage() {
        try {
            Thread.sleep(5000);  // Espera 2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "error.xhtml?faces-redirect=true";  // Redirige a error.xhtml
    }
    // ... otros métodos de tu bean ...

}
