package bean;

import DAO.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;


@ManagedBean
@ViewScoped
public class UsuarioBean {
    Usuario user = new Usuario();
    List<Usuario> lstUser;
    UsuarioDAO userDAO = new UsuarioDAO();

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Usuario> getLstUser() {
        return lstUser;
    }

    public void setLstUser(List<Usuario> lstUser) {
        this.lstUser = lstUser;
    }

    public void listar() {
        lstUser = userDAO.listar();
    }

    public void crear() {
        try {
            userDAO.agregar(user);
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Crear Usuario", "El Usuario fue creado exitosamente"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Crear Usuario", e.getMessage()));
        }
    }

    public void editar(Usuario u) {
        user = u;
    }

    public void actualizar() {
        userDAO.actualizar(user);
        limpiar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar Usuario", "El Usuario fue actualizado exitosamente"));
    }

    public void eliminar(Usuario u) {
        userDAO.eiminar(u);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminar Usuario", "El Usuario fue eliminado exitosamente"));
    }

    public void limpiar() {
        user.setId_Usuario(0);
        user.setContraseña("");
        user.setNombre("");
        user.setApellido("");
        user.setCorreo("");
        user.setTelefono("");
        user.setDireccion("");
    }
}
