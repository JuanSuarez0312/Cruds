package bean;

import DAO.PqrsDAO;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.PQRS;

@ManagedBean
@ViewScoped
public class PqrsBean {

    PQRS pqrs = new PQRS();
    List<PQRS> lstPqrs;
    PqrsDAO pqrsDAO = new PqrsDAO();

    public PQRS getPqrs() {
        return pqrs;
    }

    public void setPqrs(PQRS pqrs) {
        this.pqrs = pqrs;
    }

    public List<PQRS> getLstPqrs() {
        return lstPqrs;
    }

    public void setLstPqrs(List<PQRS> lstPqrs) {
        this.lstPqrs = lstPqrs;
    }

    public void listar() {
        lstPqrs = pqrsDAO.listar();
    }

    public void crear() {
        try {
            pqrsDAO.agregar(pqrs);
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Crear PQRS", "La PQRS ha sido creada exitosamente"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Crear PQRS", e.getMessage()));

        }
    }

    public void editar(PQRS pq) {
        pqrs = pq;
    }

    public void actualizar() {
        pqrsDAO.actualizar(pqrs);
        limpiar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar PQRS", "La PQRS ha sido actualizada exitosamente"));

    }

    public void eliminar(PQRS pq) {
        pqrsDAO.eliminar(pq);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar PQRS", "La PQRS ha sido eliminada exitosamente"));

    }

    public void limpiar() {
        pqrs.setId_Pqrs(0);
        pqrs.setTipo_Pqrs("");
        pqrs.setAsunto("");
        pqrs.setAsunto("");
        pqrs.setDescripcion_Pqrs("");
        pqrs.setId_Usuario(0);
    }
}
