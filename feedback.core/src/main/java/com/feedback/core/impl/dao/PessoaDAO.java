
package com.feedback.core.impl.dao;

import com.feedback.dominio.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("PessoaDAO")
public class PessoaDAO extends AbstractJdbcDAO {
	
        @Autowired
	public PessoaDAO() {
		super("tb_pessoa", "pes_id");		
	}
        
        /** 
	 * TODO Descrição do Método
	 * @param entidade
	 * @see fai.dao.IDAO#salvar(fai.domain.EntidadeDominio)
	 */
        
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst=null;
		Pessoa pessoa = (Pessoa)entidade;
                Endereco end = pessoa.getEndereco();
		
		
		try {                                        
			connection.setAutoCommit(false);
                        
						
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_pessoa(pes_nome, pes_cpf, pes_rg, ");
                        sql.append("pes_end_logradouro, pes_end_numero, pes_end_cep, pes_end_complemento, pes_end_bairro,pes_end_cidade, pes_end_estado, ");
			sql.append("pes_dt_cadastro)");
                        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, pessoa.getNome());
			pst.setString(2, pessoa.getCpf());
                        pst.setString(3, pessoa.getRg());
                        pst.setString(4, end.getLogradouro());
                        pst.setString(5, end.getNumero());
                        pst.setString(6, end.getCep());
                        pst.setString(7, end.getComplemento());
                        pst.setString(8, end.getBairro());
                        pst.setString(9, end.getCidade().getNome());
			pst.setString(10, end.getCidade().getEstado().getNome());
			Timestamp time = new Timestamp(pessoa.getDtCadastro().getTime());
                        
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
		Pessoa pessoa = (Pessoa)entidade;		
		
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_pessoa SET pes_nome=?, pes_cpf=?, pes_rg=?,"
                                + " pes_end_logradouro=?, pes_end_numero=?, pes_end_cep=?,"
                                + "  pes_end_complemento=?,  pes_end_bairro=?,"
                                + "  pes_end_cidade=?,  pes_end_estado=?, ");
			sql.append("WHERE pes_id=?");	
                        
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, pessoa.getNome());
			pst.setString(2, pessoa.getCpf());
			pst.setString(3, pessoa.getRg());
                        pst.setString(4, pessoa.getEndereco().getLogradouro());
                        pst.setString(5, pessoa.getEndereco().getNumero());
                        pst.setString(6, pessoa.getEndereco().getCep());
                        pst.setString(7, pessoa.getEndereco().getComplemento());
                        pst.setString(8, pessoa.getEndereco().getBairro());
                        pst.setString(9, pessoa.getEndereco().getCidade().getNome());
                        pst.setString(10,pessoa.getEndereco().getCidade().getEstado().getNome());
                        pst.setInt(11,pessoa.getId());
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
			
			Pessoa pessoa = (Pessoa)entidade;
			String sql=null;
			
			if(pessoa.getNome() == null){
				pessoa.setNome("");
			}
			
			if(pessoa.getId() == null && pessoa.getNome().equals("")){
				sql = "SELECT * FROM tb_pessoa";
			}else if(pessoa.getId() != null && pessoa.getNome().equals("")){
				sql = "SELECT * FROM tb_pessoa WHERE pes_id=?";
			}else if(pessoa.getId() == null && !pessoa.getNome().equals("")){
				sql = "SELECT * FROM tb_pessoa WHERE pes_nome like ?";
			
			}
		
		
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if(pessoa.getId() != null && pessoa.getNome().equals("")){
				pst.setInt(1, pessoa.getId());
			}else if(pessoa.getId() == null && !pessoa.getNome().equals("")){
				pst.setString(1, "%"+pessoa.getNome()+"%");			
			}		

			
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> pessoas = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Pessoa u = new Pessoa();
				u.setId(rs.getInt("pes_id"));
				u.setNome(rs.getString("pes_nome"));
				u.setCpf(rs.getString("pes_cpf"));  
                                u.setRg(rs.getString("pes_rg"));				
				java.sql.Date dtCadastroEmLong = rs.getDate("pes_dt_cadastro");
				Date dtCadastro = new Date(dtCadastroEmLong.getTime());                                
				u.setDtCadastro(dtCadastro);
				pessoas.add(u);
			}
			return pessoas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    @Override
    public List<EntidadeDominio> listar() {

            List<EntidadeDominio> pessoas = new ArrayList<EntidadeDominio>();
        
            PreparedStatement pst = null;
            String sql=null;
            
            try {
            openConnection();
                     
            sql = "SELECT * FROM tb_pessoa ORDER BY pes_id";
            pst = connection.prepareStatement(sql);              
            ResultSet rs = pst.executeQuery();
                            
		
               
			while (rs.next()) {
				Pessoa u = new Pessoa();
				u.setId(rs.getInt("pes_id"));
				u.setNome(rs.getString("pes_nome"));
				u.setCpf(rs.getString("pes_cpf"));  
                                u.setRg(rs.getString("pes_rg"));				
				java.sql.Date dtCadastroEmLong = rs.getDate("pes_dt_cadastro");
				Date dtCadastro = new Date(dtCadastroEmLong.getTime());                                
				u.setDtCadastro(dtCadastro);
				pessoas.add(u);
			}
                        
                        
			return pessoas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    
        

       
}

	

	
	

	


