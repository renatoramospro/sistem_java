package Dao;

import Factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioDAO {
    private Connection connection;
    long idUsuario;
    String usuario;
    String senha;
    ResultSet rs;
    ArrayList<Usuario> lista = new ArrayList<>();
   
    public UsuarioDAO(){ 
        this.connection  = new ConnectionFactory().getConnection(); 
    }

    public UsuarioDAO(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void adciona(Usuario usuario) throws Exception{
        String sql = "insert into usuario (usuario,senha) values(?,?);";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());
            stmt.execute();
            stmt.close();
            
        }catch(SQLException u){
            throw new Exception("Erro ao inserir usuário");
        }
    }
    
    public ArrayList<Usuario> Pesquisar(){
        String sql = "SELECT * FROM usuario";
       
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
             rs =stmt.executeQuery();
    
             while(rs.next()){
                 Usuario obejto = new Usuario();
                 obejto.setId(rs.getInt("Id"));
                 obejto.setUsuario(rs.getString("usuario"));
                 obejto.setSenha(rs.getString("senha"));
                 
                 lista.add(obejto);
             }
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"UsuarioDAO:" + erro);
        }
        return lista;
    }
    
    public void Alterar(Usuario usuario) throws Exception{
        String sql = "update usuario set usuario = ?, senha = ? where id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,usuario.getUsuario());
            stmt.setString(2,usuario.getSenha());            
            stmt.setInt(3,usuario.getId());            
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new Exception("Erro ao Alterar informações do Aluno");
        }
    }
    
    
    public void excluircampos(Usuario usuario){
        String sql = "DELETE  from  usuario where id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,usuario.getId());
            stmt.execute();
            stmt.close();
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"erro la"+erro);
        }

    }

}
