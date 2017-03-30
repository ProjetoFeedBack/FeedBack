
package com.feedback.core.impl.dao;

import com.feedback.dominio.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO extends AbstractJdbcDAO {
	
        
	public UsuarioDAO() {
		super("tb_usuario", "usu_id");		
	}
        
        /** 
	 * TODO Descrição do Método
	 * @param entidade
	 * @see fai.dao.IDAO#salvar(fai.domain.EntidadeDominio)
	 */
        
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst=null;
		Usuario usuario = (Usuario)entidade;
		
		
		try {                                        
			connection.setAutoCommit(false);
                        
						
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_usuario(usu_email, usu_senha)");                      
                        sql.append(" VALUES (?, ?)");
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getEmail());
			pst.setString(2, usuario.getSenha());
                        
			pst.executeUpdate();			
			connection.commit();		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		

	}
        
        
	/** 
	 * TODO Descrição do Método
	 * @param entidade
	 * @see fai.dao.IDAO#alterar(fai.domain.EntidadeDominio)
	 */
	@Override        
	public void alterar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst=null;
		Usuario usuario = (Usuario)entidade;		
		
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_usuario SET usu_email=?, usu_senha=? ");
			sql.append("WHERE usu_id=?");	
                        
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getEmail());
			pst.setString(2, usuario.getSenha());			
			pst.executeUpdate();			
			connection.commit();		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
	}
        
        
        
	/** 
	 * TODO Descrição do Método
	 * @param entidade
	 * @return
	 * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
            PreparedStatement pst = null;
			
			Usuario usuario = (Usuario)entidade;
			String sql=null;
			
			if(usuario.getNome() == null){
				usuario.setNome("");
			}
			
			if(usuario.getId() == null && usuario.getEmail().equals("")){
				sql = "SELECT * FROM tb_usuario";
			}else if(usuario.getId() != null && usuario.getEmail().equals("")){
				sql = "SELECT * FROM tb_usuario WHERE usu_id=?";
			}else if(usuario.getId() == null && !usuario.getEmail().equals("")){
				sql = "SELECT * FROM tb_usuario WHERE usu_email like ?";			
			}else if(usuario.getId() == null && !usuario.getSenha().equals("")){
				sql = "SELECT * FROM tb_usuario WHERE usu_senha like ?";			
			}		
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if(usuario.getId() != null && usuario.getNome().equals("")){
				pst.setInt(1, usuario.getId());
			}else if(usuario.getId() == null && !usuario.getNome().equals("")){
				pst.setString(1, usuario.getNome());			
			}else if(usuario.getId() == null && !usuario.getSenha().equals("")){
				pst.setString(1, usuario.getSenha());			
			}		
			
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> usuarios = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setEmail(rs.getString("usu_email"));
				u.setSenha(rs.getString("usu_senha"));  
				usuarios.add(u);
			}
			return usuarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    @Override
    public List<EntidadeDominio> listar() {

            List<EntidadeDominio> usuarios = new ArrayList<EntidadeDominio>();
        
            PreparedStatement pst = null;
            String sql=null;
            
            try {
            openConnection();
                     
            sql = "SELECT * FROM tb_usuario ORDER BY usu_id";
            pst = connection.prepareStatement(sql);              
            ResultSet rs = pst.executeQuery();
                        
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setEmail(rs.getString("usu_email"));
				u.setSenha(rs.getString("usu_senha"));                                 
				usuarios.add(u);
			}
                        
			return usuarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    
        

       
}

	

	
	

	


