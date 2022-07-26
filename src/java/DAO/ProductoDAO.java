package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

public class ProductoDAO extends DAO {

    PreparedStatement ps;
    ResultSet rs;

    public List<Producto> listar() {
        List<Producto> lstProducto = new ArrayList();
        try {
            ps = conectar().prepareStatement("Select * FROM producto");
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_Producto(rs.getInt("Id_Producto"));
                producto.setNombre_Producto(rs.getString("Nombre_Producto"));
                producto.setDescripcion_Producto(rs.getString("Descripcion_Producto"));
                producto.setPrecio(rs.getInt("Precio"));
                producto.setCantidad_Stock(rs.getInt("Cantidad_Stock"));

                lstProducto.add(producto);

            }
        } catch (SQLException e) {
        }
        return lstProducto;
    }

    public void agregar(Producto p) throws SQLException {
        try {
            ps = conectar().prepareStatement("INSERT INTO producto(Id_Producto,Nombre_Producto,Descripcion_Producto,Precio,Cantidad_Stock) VALUES(?,?,?,?,?)");
            ps.setInt(1, p.getId_Producto());
            ps.setString(2, p.getNombre_Producto());
            ps.setString(3, p.getDescripcion_Producto());
            ps.setInt(4, p.getPrecio());
            ps.setInt(5, p.getCantidad_Stock());
            if (p.getCantidad_Stock() < 0 | p.getPrecio() < 0) {
                throw new SQLException("Error, la cantidad en stock o el precio no puede ser mejor a 0");
            }

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

    }

    public void actualizar(Producto p) throws SQLException {
        try {
            ps = conectar().prepareStatement("UPDATE producto SET Nombre_Producto = ?, Descripcion_Producto = ?, Precio = ?, Cantidad_Stock = ? WHERE Id_Producto = ? ;");
            ps.setString(1, p.getNombre_Producto());
            ps.setString(2, p.getDescripcion_Producto());
            ps.setInt(3, p.getPrecio());
            if (p.getPrecio() < 0) {
                throw new SQLException("Error, El precio no puede ser mejor a 0");
            }
            ps.setInt(4, p.getCantidad_Stock());
            if (p.getCantidad_Stock() < 0) {
                throw new SQLException("Error, la cantidad en stock no puede ser mejor a 0");
            }
            ps.setInt(5, p.getId_Producto());

            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void eliminar(Producto p) {
        try {
            ps = conectar().prepareStatement("DELETE FROM producto WHERE Id_Producto = ?");
            ps.setInt(1, p.getId_Producto());

            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
