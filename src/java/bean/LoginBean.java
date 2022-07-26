package bean;

import DAO.DAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.Usuario;

@ManagedBean
public class LoginBean {

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void autenticar() {
        DAO dao = new DAO();
        Connection con = dao.conectar();

        try {
            String sql = "SELECT * FROM usuario WHERE Id_Usuario = ? && Contraseña = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, usuario.getId_Usuario());
            ps.setString(2, usuario.getContraseña()
            );

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //Permite acceder a una gran cantidad de elementos de la aplicacion
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", rs.getString("Nombre"));

                if (rs.getInt("Id_rol") == 1) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");

                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("client.xhtml");

                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Id de Usuario de usuario y/o ContraseÃ±a no validos"));
            }

        } catch (SQLException | IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", e.getMessage()));
        }

    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");

        } catch (IOException e) {
        }
    }

    public void verificarSesion() {
        String nombre = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (nombre == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("sinacceso.xhtml");
            } catch (IOException e) {
            }
        }
    }
}
