package com.exemplo.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.exemplo.entidade.Cliente;
import com.exemplo.entidade.Endereco;
import com.exemplo.repositorio.Pesquisa;
import com.exemplo.repositorio.RepositorioCliente;

@ManagedBean(name = "controleCliente")
@SessionScoped
public class ControleCliente {
	public static String valorPes;
	private RepositorioCliente repositorioCliente;

	private Cliente cliente;
	private List<Cliente> clientes;
	private String pesquisaEndereco;
	private Endereco endereco;
	private List<Endereco> enderecos;

	private Pesquisa pesquisa;

	public ControleCliente() {
		repositorioCliente = new RepositorioCliente();
		cliente = new Cliente();
		endereco = new Endereco();
		pesquisa = new Pesquisa();
	}
	
	
	
	
	

	public List<Endereco> getEnderecos() {
		enderecos = repositorioCliente.listarEnderecos();
		int idzinhoCliente = cliente.getIdCliente();
		int idzinhoEndereco = endereco.getIdEndereco();
		List<Endereco> listaEndereco = enderecos;
		List<Endereco> listaFiltrada = new ArrayList<Endereco>();

		for (Endereco endereco : listaEndereco) {

			idzinhoEndereco = endereco.getIdEndereco();
			if (idzinhoCliente == idzinhoEndereco) {
				listaFiltrada.add(endereco);
			}
		}

		return listaFiltrada;
	}

	public void setEnderecos(List<Endereco> enderecos) {

		this.enderecos = enderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;

	}

	public List<Cliente> getClientes() {
		clientes = repositorioCliente.listarTodos();
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String salvar() {
		Random random = new Random();
		int idClientinho = random.nextInt(1000);
		cliente.setIdCliente(idClientinho);

		repositorioCliente.salvar(cliente);

		return "index";
	}

	public String listarPorEndereco() {

	
		return "buscaEnd";

	}

	public String salvarEdit() {
		repositorioCliente.salvar(cliente);

		return "index";
	}

	public String salvarEndereco() {

		repositorioCliente.salvar(endereco);
		return "index";
	}

	public String novaPesquisa() {

		return "buscaEnd";
	}

	public String novo() {
		cliente = new Cliente();
		return "formularioCliente";
	}

	public String novoEndereco() {
		endereco = new Endereco();
		int idzinhoCliente = cliente.getIdCliente();
		endereco.setIdEndereco(idzinhoCliente);
		return "addEnd";
	}

	public String editar() {
		return "editarCliente";
	}

	public String editarEndereco() {
		return "maisEndereco";
	}

	public String remover() {
		List<Endereco> list = getEnderecos();
		int clienteIdzinho = cliente.getIdCliente();
		repositorioCliente.remover(cliente);

		for (Endereco endereco : list) {
			cliente.setIdCliente(clienteIdzinho);
			repositorioCliente.removerEnd(endereco);
		}

		return null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getPesquisaEndereco() {
		enderecos = repositorioCliente.listarEnderecos();
		clientes = repositorioCliente.listarTodos();
		List<Endereco> listaEndereco = enderecos;
		List<Cliente> listaCliente = clientes;
		ArrayList <String> logradouros = new ArrayList<String>();
		String pesq = pesquisa.getPesquisado();

		for (Endereco endereco : listaEndereco) {
			 String log = endereco.getLogradouro();
			 if (log.contains(pesq)){
				 
				 logradouros.add(pesq);
			}
		}
		return pesquisaEndereco + logradouros;
	}

	public void setPesquisaEndereco(String pesquisaEndereco) {
		this.pesquisaEndereco = pesquisaEndereco;
	}
	
	


	public Pesquisa getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Pesquisa pesquisa) {
		this.pesquisa = pesquisa;
	}

}
