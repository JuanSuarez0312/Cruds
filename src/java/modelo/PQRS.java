package modelo;

import java.sql.Date;

public class PQRS {
    
    private int Id_Pqrs, Id_Usuario;
    private String Tipo_Pqrs,Asunto,Descripcion_Pqrs,Estado;
    private Usuario usuario;
    private Date Fecha_Inicial, Fecha_Maxima_Respuesta;

    public int getId_Pqrs() {
        return Id_Pqrs;
    }

    public void setId_Pqrs(int Id_Pqrs) {
        this.Id_Pqrs = Id_Pqrs;
    }

    public int getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(int Id_Usuario) {
        this.Id_Usuario = Id_Usuario;
    }

    public String getTipo_Pqrs() {
        return Tipo_Pqrs;
    }

    public void setTipo_Pqrs(String Tipo_Pqrs) {
        this.Tipo_Pqrs = Tipo_Pqrs;
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }

    public String getDescripcion_Pqrs() {
        return Descripcion_Pqrs;
    }

    public void setDescripcion_Pqrs(String Descripcion_Pqrs) {
        this.Descripcion_Pqrs = Descripcion_Pqrs;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha_Inicial() {
        return Fecha_Inicial;
    }

    public void setFecha_Inicial(Date Fecha_Inicial) {
        this.Fecha_Inicial = Fecha_Inicial;
    }

    public Date getFecha_Maxima_Respuesta() {
        return Fecha_Maxima_Respuesta;
    }

    public void setFecha_Maxima_Respuesta(Date Fecha_Maxima_Respuesta) {
        this.Fecha_Maxima_Respuesta = Fecha_Maxima_Respuesta;
    }
    
    
    
}
