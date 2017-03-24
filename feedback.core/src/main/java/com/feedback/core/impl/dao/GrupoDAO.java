
package com.feedback.core.impl.dao;

import com.feedback.dominio.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
//import dinax.projeto.dominio.Fornecedor;

public class GrupoDAO extends AbstractJdbcDAO {
	
	public GrupoDAO() {
		super("tb_grupo", "gru_id");		
	}
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst=null;
		Grupo grupo = (Grupo)entidade;
		
		
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_grupo(gru_nome, gru_descricao, gru_nivel,  ");
			sql.append("gru_dt_cadastro) VALUES (?,?,?,?)");		
			//gru_nivel_grupopai,
                        
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, grupo.getNome());
			pst.setString(2, grupo.getDescricao());
                        pst.setInt(3, Integer.parseInt(grupo.getNivel()));
                      //  pst.setString(4, grupo.getNivel_grupopai());
			Timestamp time = new Timestamp(grupo.getDtCadastro().getTime());
			pst.setTimestamp(4, time);
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
	 * TODO Descri��o do M�todo
	 * @param entidade
	 * @see fai.dao.IDAO#alterar(fai.domain.EntidadeDominio)
	 */
	@Override
	public void alterar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst=null;
		Grupo grupo = (Grupo)entidade;		
		
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_grupo SET gru_nome=?, gru_descricao=?, gru_nivel=?,"
                                + " gru_gpai=?,  ");
			sql.append("WHERE gru_id=?");	
                        
			pst = connection.prepareStatement(sql.toString());
                        pst.setString(1, grupo.getNome());
			pst.setString(2, grupo.getDescricao());
                        pst.setString(3, grupo.getNivel());
                        pst.setString(4, grupo.getNivel_grupopai());
                        pst.setInt(5,grupo.getId());
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
	 * TODO Descri��o do M�todo
	 * @param entidade
	 * @return
	 * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
            PreparedStatement pst = null;
			
			Grupo grupo = (Grupo)entidade;
			String sql=null;
			
			if(grupo.getNome() == null){
				grupo.setNome("");
			}
			
			if(grupo.getId() == null && grupo.getNome().equals("")){
				sql = "SELECT * FROM tb_grupo";
			}else if(grupo.getId() != null && grupo.getNome().equals("")){
				sql = "SELECT * FROM tb_grupo WHERE gru_id=?";
			}else if(grupo.getId() != null && !grupo.getNome().equals("")){
				sql = "SELECT * FROM tb_grupo WHERE gru_id=?";
			}else if(grupo.getId() == null && !grupo.getNome().equals("")){
				sql = "SELECT * FROM tb_grupo WHERE gru_nome like ?";			
			}
		
		
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if(grupo.getId() != null && grupo.getNome().equals("")){
				pst.setInt(1, grupo.getId());
			}else if(grupo.getId() == null && !grupo.getNome().equals("")){
				pst.setString(1, "%"+grupo.getNome()+"%");			
			}else if(grupo.getId() != null && !grupo.getNome().equals("")){                        
                                pst.setInt(1, grupo.getId());
                        }
                          
			
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> grupos = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Grupo g = new Grupo();
				g.setId(rs.getInt("gru_id"));
				g.setNome(rs.getString("gru_nome"));
				g.setDescricao(rs.getString("gru_descricao"));  
                                g.setNivel(rs.getString("gru_nivel"));
                                g.setNivel_grupopai(rs.getString("gru_gpai"));
				
				java.sql.Date dtCadastroEmLong = rs.getDate("gru_dt_cadastro");
				Date dtCadastro = new Date(dtCadastroEmLong.getTime());				
				g.setDtCadastro(dtCadastro);
				grupos.add(g);
			}
			return grupos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    @Override
    public List<EntidadeDominio> listar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
