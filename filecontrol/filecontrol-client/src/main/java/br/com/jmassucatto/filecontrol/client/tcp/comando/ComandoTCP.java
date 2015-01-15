package br.com.jmassucatto.filecontrol.client.tcp.comando;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;

public abstract class ComandoTCP {

	private DataOutputStream saida;
	private BufferedReader entrada;
	private Socket conexao;

	protected abstract String getRequisicao();
	
	protected Socket getConexao() throws UnknownHostException, IOException {
		if (conexao == null || conexao.isClosed())
			inicializa();
		
		return conexao;
	}

	protected void inicializa() {
		try {
			conexao = new Socket("localhost", 6789);
			saida = new DataOutputStream(conexao.getOutputStream());
			entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
		} catch (UnknownHostException e) {
			throw new FileControlException(Excecao.ERRO_AO_INICIAR_CLIENTE, e);
		} catch (IOException e) {
			throw new FileControlException(Excecao.ERRO_AO_INICIAR_CLIENTE, e);
		}
	}
	
	protected void fazRequisicao() throws IOException {
		getSaida().writeBytes(getRequisicao() + '\n');
	}

	protected void finaliza() throws IOException, UnknownHostException {
		getConexao().close();
	}

	protected DataOutputStream getSaida() {
		if (saida == null)
			inicializa();
		
		return saida;
	}

	protected BufferedReader getEntrada() {
		if (entrada == null)
			inicializa();
		
		return entrada;
	}

}
