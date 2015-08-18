package br.com.fiap.bean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import sun.nio.ch.IOUtil;
import br.com.fiap.dao.FuncionarioDAO;
import br.com.fiap.dao.impl.FuncionarioDAOImpl;
import br.com.fiap.entity.Funcionario;
import br.com.fiap.exceptions.DBCommitException;
import br.com.fiap.singleton.EMFactorySingleton;

@ManagedBean
@SessionScoped
public class FuncionarioBean implements Serializable {

	private FuncionarioDAO dao;
	
	private Funcionario funcionario;
	
	private List<Funcionario> lista;
	
	private String nomeBusca;
	
	@PostConstruct
	private void init(){
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		dao = new FuncionarioDAOImpl(em);
		
		funcionario = new Funcionario();
	}
	
	public StreamedContent getFoto(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		DefaultStreamedContent foto = new DefaultStreamedContent();
		foto.setContentType("image/jpg");

		try {
			if (context.getRenderResponse() || funcionario.getFoto() == null){
				foto.setStream(new FileInputStream("c:\\temp\\teste.jpg"));
			}else{
				/*foto.setStream(
					new FileInputStream("c:\\temp\\"+funcionario.getFoto()));
				*/
				foto.setStream(
					new ByteArrayInputStream(funcionario.getFoto()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return foto;
	}
	
	//Método responsável por realizar o upload da foto
	public void subir(FileUploadEvent event){
		try {
			//Recupera o nome do arquivo enviado
			//Grava o arquivo em disco
			/*String nomeArquivo = event.getFile().getFileName();
			File file = new File("C:\\temp\\",nomeArquivo);
			FileOutputStream output = new FileOutputStream(file);
			output.write(event.getFile().getContents());
			output.close();*/	
			
			//Grava o arquivo no DB
			funcionario.setFoto(
				IOUtils.toByteArray(event.getFile().getInputstream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar(){
		FacesMessage msg;
		try {
			if (funcionario.getCodigo() == 0){
				dao.create(funcionario);
				msg = new FacesMessage("Cadastrado!");
			}else{
				dao.update(funcionario);
				msg = new FacesMessage("Alterado!");
			}
			funcionario = new Funcionario(); //Cadastrar um novo usuário
		} catch (DBCommitException e) {
			msg = new FacesMessage("Erro..");
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void buscar(){
		lista = dao.buscarPorNome(nomeBusca);
	}

	//Método para o autocomplete
	public List<String> completarNome(String nome){
		return dao.autoCompletePorNome(nome);
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getLista() {
		return lista;
	}

	public void setLista(List<Funcionario> lista) {
		this.lista = lista;
	}

	public String getNomeBusca() {
		return nomeBusca;
	}

	public void setNomeBusca(String nomeBusca) {
		this.nomeBusca = nomeBusca;
	}
	
}
