package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.PQRS;

public class PqrsDAO extends DAO {

    PreparedStatement ps;
    ResultSet rs;

    public List<PQRS> listar() {
        List<PQRS> lstPqrs = new ArrayList();
        try {
            ps = conectar().prepareStatement("SELECT * FROM pqrs");
            rs = ps.executeQuery();

            while (rs.next()) {
                PQRS pqrs = new PQRS();
                pqrs.setId_Pqrs(rs.getInt("Id_Pqrs"));
                pqrs.setTipo_Pqrs(rs.getString("Tipo_Pqrs"));
                pqrs.setFecha_Inicial(rs.getDate("Fecha_Inicial"));
                pqrs.setFecha_Maxima_Respuesta(rs.getDate("Fecha_Maxima_Respuesta"));
                pqrs.setAsunto(rs.getString("Asunto"));
                pqrs.setDescripcion_Pqrs(rs.getString("Descripcion_Pqrs"));
                pqrs.setEstado(rs.getString("Estado"));
                pqrs.setId_Usuario(rs.getInt("Id_Usuario"));

                lstPqrs.add(pqrs);

            }
        } catch (SQLException e) {
        }
        return lstPqrs;
    }

    public void agregar(PQRS pq) throws SQLException {
        try {
            ps = conectar().prepareStatement("INSERT INTO pqrs(Tipo_Pqrs,Fecha_Inicial,Fecha_Maxima_Respuesta,Asunto,Descripcion_Pqrs,Estado,Id_Usuario,Id_Venta) VALUES (?,now(),date_add(now(),interval 15 day),?,?,'Pendiente',?,null)");
            ps.setString(1, pq.getTipo_Pqrs());
            ps.setString(2, pq.getAsunto());
            ps.setString(3, pq.getDescripcion_Pqrs());
            ps.setInt(4, pq.getId_Usuario());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

    }

    public void actualizar(PQRS pq) {
        try {
            ps = conectar().prepareStatement("UPDATE Pqrs SET Asunto = ?, Descripcion_Pqrs = ? WHERE Id_Pqrs = ?");
            ps.setString(1, pq.getAsunto());
            ps.setString(2, pq.getDescripcion_Pqrs());
            ps.setInt(3, pq.getId_Pqrs());

            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void eliminar(PQRS pq) {
        try {
            ps = conectar().prepareStatement("DELETE FROM pqrs WHERE Id_Pqrs = ?");
            ps.setInt(1, pq.getId_Pqrs());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
