package bean;

import DAO.ProductoDAO;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Producto;

@ManagedBean
@ViewScoped
public class ProductoBean {

    Producto prod = new Producto();
    List<Producto> lstProduc;
    ProductoDAO prodDAO = new ProductoDAO();

    public Producto getProd() {
        return prod;
    }

    public void setProd(Producto prod) {
        this.prod = prod;
    }

    public List<Producto> getLstProduc() {
        return lstProduc;
    }

    public void setLstProduc(List<Producto> lstProduc) {
        this.lstProduc = lstProduc;
    }

    public void listar() {
        lstProduc = prodDAO.listar();
    }

    public void crear() {
        try {
            prodDAO.agregar(prod);
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Crear Producto", "El producto fue creado exitosamente"));

        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Crear Producto", e.getMessage()));

        }
    }

    public void editar(Producto p) {
        prod = p;
    }

    public void actualizar() throws SQLException {
        prodDAO.actualizar(prod);
        limpiar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Editar Producto", "El producto fue actualizado exitosamente"));
    }

    public void eliminar(Producto p) {
        prodDAO.eliminar(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminar Usuario", "El Usuario fue eliminado exitosamente"));

    }

    public void limpiar() {
        prod.setId_Producto(0);
        prod.setNombre_Producto("");
        prod.setDescripcion_Producto("");
        prod.setPrecio(0);
        prod.setCantidad_Stock(0);
    }
    
}
