
package com.feedback.core.impl.dao;

import com.feedback.dominio.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;


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
                Endereco end = usuario.getEndereco();
		
		
		try {                                        
			connection.setAutoCommit(false);
                        
						
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_usuario(usu_nome, usu_cpf, usu_rg, ");
                        sql.append("usu_end_logradouro, usu_end_numero, usu_end_cep, usu_end_complemento, usu_end_bairro,usu_end_cidade, usu_end_estado, ");
			sql.append("usu_dt_cadastro)");
                        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getCpf());
                        pst.setString(3, usuario.getRg());
                        pst.setString(4, end.getLogradouro());
                        pst.setString(5, end.getNumero());
                        pst.setString(6, end.getCep());
                        pst.setString(7, end.getComplemento());
                        pst.setString(8, end.getBairro());
                        pst.setString(9, end.getCidade().getNome());
			pst.setString(10, end.getCidade().getEstado().getNome());
			Timestamp time = new Timestamp(usuario.getDtCadastro().getTime());
                        
			pst.setTimestamp(11, time);
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
			sql.append("UPDATE tb_usuario SET usu_nome=?, usu_cpf=?, usu_rg=?,"
                                + " usu_end_logradouro=?, usu_end_numero=?, usu_end_cep=?,"
                                + "  usu_end_complemento=?,  usu_end_bairro=?,"
                                + "  usu_end_cidade=?,  usu_end_estado=?, ");
			sql.append("WHERE usu_id=?");	
                        
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getCpf());
			pst.setString(3, usuario.getRg());
                        pst.setString(4, usuario.getEndereco().getLogradouro());
                        pst.setString(5, usuario.getEndereco().getNumero());
                        pst.setString(6, usuario.getEndereco().getCep());
                        pst.setString(7, usuario.getEndereco().getComplemento());
                        pst.setString(8, usuario.getEndereco().getBairro());
                        pst.setString(9, usuario.getEndereco().getCidade().getNome());
                        pst.setString(10,usuario.getEndereco().getCidade().getEstado().getNome());
                        pst.setInt(11,usuario.getId());
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
			
			if(usuario.getId() == null && usuario.getNome().equals("")){
				sql = "SELECT * FROM tb_usuario";
			}else if(usuario.getId() != null && usuario.getNome().equals("")){
				sql = "SELECT * FROM tb_usuario WHERE usu_id=?";
			}else if(usuario.getId() == null && !usuario.getNome().equals("")){
				sql = "SELECT * FROM tb_usuario WHERE usu_nome like ?";
			
			}
		
		
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if(usuario.getId() != null && usuario.getNome().equals("")){
				pst.setInt(1, usuario.getId());
			}else if(usuario.getId() == null && !usuario.getNome().equals("")){
				pst.setString(1, "%"+usuario.getNome()+"%");			
			}		

			
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> usuarios = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("usu_id"));
				u.setNome(rs.getString("usu_nome"));
				u.setCpf(rs.getString("usu_cpf"));  
                                u.setRg(rs.getString("usu_rg"));				
				java.sql.Date dtCadastroEmLong = rs.getDate("usu_dt_cadastro");
				Date dtCadastro = new Date(dtCadastroEmLong.getTime());                                
				u.setDtCadastro(dtCadastro);
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
				u.setId(rs.getInt("usu_id"));
				u.setNome(rs.getString("usu_nome"));
				u.setCpf(rs.getString("usu_cpf"));  
                                u.setRg(rs.getString("usu_rg"));				
				java.sql.Date dtCadastroEmLong = rs.getDate("usu_dt_cadastro");
				Date dtCadastro = new Date(dtCadastroEmLong.getTime());                                
				u.setDtCadastro(dtCadastro);
				usuarios.add(u);
			}
                        
                        
			return usuarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    
        

       
}

	

	
	

	


