package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

public class UsuarioDAO extends DAO{
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Usuario> listar(){
        List<Usuario> lstUser = new ArrayList();
        try {
            ps = conectar().prepareStatement("SELECT * FROM usuario");
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                Usuario usuario = new Usuario();
                usuario.setId_Usuario(rs.getInt("Id_Usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setDireccion(rs.getString("Direccion"));
                usuario.setId_Rol(rs.getInt("Id_Rol"));
                
                lstUser.add(usuario);
            }
        } catch (SQLException e) {
        }
        return lstUser;
    }
    
    public void agregar(Usuario u) throws SQLException{
        try {
            String sql = "INSERT INTO usuario VALUES(?,?,?,?,?,?,?,2)";
            ps = conectar().prepareStatement(sql);
            ps.setInt(1, u.getId_Usuario());
            ps.setString(2, u.getContraseña());
            ps.setString(3, u.getNombre());
            ps.setString(4, u.getApellido());
            ps.setString(5, u.getCorreo());
            ps.setString(6, u.getTelefono());
            ps.setString(7, u.getDireccion());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new SQLException("Error, La Id pertenese a otro usuario");
        }
    }
    
    public void actualizar(Usuario u){
        try {
            String sql = "UPDATE usuario SET ContraseÃ±a = ?, Nombre = ?, Apellido = ?, Correo = ?, Telefono = ?, Direccion = ? WHERE Id_Usuario = ?";
            ps = conectar().prepareStatement(sql);
            ps.setString(1, u.getContraseña());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getCorreo());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getDireccion());
            ps.setInt(7, u.getId_Usuario());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
        }
    }
    
    
    public void eiminar(Usuario u){
        try {
            String sql = "DELETE FROM usuario WHERE Id_Usuario = ?";
            ps = conectar().prepareStatement(sql);
            ps.setInt(1, u.getId_Usuario());
            
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
