package dev.rhc.apiuser.dto;

public class ApiResponse {
    private String mensaje;

    public ApiResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    // getters y setters
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
