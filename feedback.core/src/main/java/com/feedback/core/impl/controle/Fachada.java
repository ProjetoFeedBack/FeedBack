package com.feedback.core.impl.controle;

import com.feedback.core.IDAO;
import com.feedback.core.IFachada;
import com.feedback.core.IStrategy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feedback.core.aplicacao.Resultado;
import com.feedback.core.impl.dao.UsuarioDAO;
import com.feedback.core.impl.dao.GrupoDAO;
import com.feedback.core.impl.negocio.ComplementarDtCadastro;
import com.feedback.core.impl.negocio.ValidadorCpf;
import com.feedback.core.impl.negocio.ValidadorDadosObrigatoriosUsuario;
import com.feedback.core.impl.negocio.ValidadorDadosObrigatoriosGrupo;
import com.feedback.core.impl.negocio.ValidadorGrupoPai;
import com.feedback.dominio.*;


public class Fachada implements IFachada {

	/** 
	 * Mapa de DAOS, ser� indexado pelo nome da entidade 
	 * O valor � uma inst�ncia do DAO para uma dada entidade; 
	 */
	private Map<String, IDAO> daos;
	
	/**
	 * Mapa para conter as regras de neg�cio de todas opera��es por entidade;
	 * O valor � um mapa que de regras de neg�cio indexado pela opera��o
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;
	
	private Resultado resultado;
	
	
	public Fachada(){
		/* Intanciando o Map de DAOS */
		daos = new HashMap<String, IDAO>();
		/* Intanciando o Map de Regras de Neg�cio */
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
				
                
                /* Criando instancias dos DAOs a serem utilizados*/
                UsuarioDAO usuDAO = new UsuarioDAO();
                GrupoDAO gruDAO = new GrupoDAO();
		
		/* Adicionando cada dao no MAP indexando pelo nome da classe */
                daos.put(Usuario.class.getName(), usuDAO);
                daos.put(Grupo.class.getName(), gruDAO);
                			
		/* Criando instancias de regras de negocio a serem utilizados*/		
		ComplementarDtCadastro cDtCadastro = new ComplementarDtCadastro();
                ValidadorDadosObrigatoriosUsuario vDadosUsuario = new ValidadorDadosObrigatoriosUsuario();
		ValidadorCpf vCpf = new ValidadorCpf();                
                ValidadorDadosObrigatoriosGrupo vDadosGrupo = new ValidadorDadosObrigatoriosGrupo();                
                ValidadorGrupoPai vGrupoPai = new ValidadorGrupoPai();
						
                
                    /* Criando uma lista para conter as regras de negocio de Usuario*/
                     
                
                        /*Regras quando a operacao for salvar */
                        List<IStrategy> rnsSalvarUsuario = new ArrayList<IStrategy>();
                        /* Adicionando as regras a serem utilizadas na operacao salvar do usuario */
                        rnsSalvarUsuario.add(cDtCadastro);
                        rnsSalvarUsuario.add(vDadosUsuario);
                        rnsSalvarUsuario.add(vCpf);

                        /* Regras quando a operacao for alterar */
                        List<IStrategy> rnsAlterarUsuario = new ArrayList<IStrategy>();
                        /* Adicionando as regras a serem utilizadas na operacao alterar do usuario */
                        rnsAlterarUsuario.add(vDadosUsuario);		
                        rnsAlterarUsuario.add(vCpf);
                        
                        
                        
                        
                        /* Cria o mapa que podera conter todas as listas de regras de negocio especifica 
                         * por operacao do usuario
                         */
                        Map<String, List<IStrategy>> rnsUsuario = new HashMap<String, List<IStrategy>>();
                        
                        
                        /*
                         * Adiciona a lista de regras na operacao salvar no mapa do usuario 
                         */
                        rnsUsuario.put("SALVAR", rnsSalvarUsuario);		
                        
                         /*
                         * Adiciona a lista de regras na operacao alterar no mapa do usuario 
                         */
                        rnsUsuario.put("ALTERAR", rnsAlterarUsuario);		
                        
                        
                        /* Adiciona o mapa(criado na linha 66) com as regras indexadas pelas operacoes no mapa geral indexado 
                         * pelo nome da entidade. Observe que este mapa (rns) e o mesmo utilizado na linha 74
                         */
                        rns.put(Usuario.class.getName(), rnsUsuario);
                        
                        
                        /* Criando uma lista para conter as regras de negocio de Grupo*/
                     
                
                        /*Regras quando a operacao for salvar */
                        List<IStrategy> rnsSalvarGrupo = new ArrayList<IStrategy>();
                        /* Adicionando as regras a serem utilizadas na operacao salvar do grupo */
                        rnsSalvarGrupo.add(cDtCadastro);
                        rnsSalvarGrupo.add(vDadosGrupo);                        
                       // rnsSalvarGrupo.add(vGrupoPai);
                        

                        /* Regras quando a operacao for alterar */
                        List<IStrategy> rnsAlterarGrupo = new ArrayList<IStrategy>();
                        /* Adicionando as regras a serem utilizadas na operacao alterar do grupo */
                        rnsAlterarGrupo.add(vDadosGrupo);	                      
                                               
                        
                        /* Cria o mapa que podera conter todas as listas de regras de negocio especifica 
                         * por operacao do grupo
                         */
                        Map<String, List<IStrategy>> rnsGrupo = new HashMap<String, List<IStrategy>>();
                       
                        
                        /*
                         * Adiciona a lista de regras na operacao salvar no mapa do grupo 
                         */
                        rnsGrupo.put("SALVAR", rnsSalvarGrupo);		
                        
                         /*
                         * Adiciona a lista de regras na operacao alterar no mapa do grupo 
                         */
                        rnsGrupo.put("ALTERAR", rnsAlterarGrupo);		
                        
                        
                        /* Adiciona o mapa com as regras indexadas pelas operacoes no mapa geral indexado 
                         * pelo nome da entidade. Observe que este mapa (rns) e o mesmo utilizado na linha 74
                         */
                        rns.put(Grupo.class.getName(), rnsGrupo);
                        
                        /*
                        Codigo para Mensagem aqui !!!!
                        */
		
		
		
	}
	
	
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "SALVAR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "ALTERAR");
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "EXCLUIR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "CONSULTAR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				
				resultado.setEntidades(dao.consultar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar a consulta!");
				
			}
		}else{
			resultado.setMsg(msg);
			
		}
		
		return resultado;

	}   
	
	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
		resultado.getEntidades().add(entidade);		
		return resultado;

	}

        
	private String executarRegras(EntidadeDominio entidade, String operacao){
		String nmClasse = entidade.getClass().getName();		
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		
		
		if(regrasOperacao != null){
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			if(regras != null){
				for(IStrategy s: regras){			
					String m = s.processar(entidade);			
					
					if(m != null){
						msg.append(m);
						msg.append("\n");
					}			
				}	
			}			
			
		}
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}
}
