package br.com.jmassucatto.filecontrol.server.tcp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;
import br.com.jmassucatto.filecontrol.server.comando.Comando;
import br.com.jmassucatto.filecontrol.server.comando.ComandoGetArquivo;
import br.com.jmassucatto.filecontrol.server.comando.ComandoTipo;

@RunWith(MockitoJUnitRunner.class)
public class RequisicaoTest {
	
	@Spy private Requisicao requisicao;
	
	@Mock private Comando comando;
	@Mock private BufferedReader entrada;
	@Mock private DataOutputStream saida;

	@Rule public ExpectedException excecao = ExpectedException.none();
	
	@Before
	public void setup() {
		requisicao.entrada = entrada;
		requisicao.saida = saida;
	}

	@Test
	public void executa() throws Exception {
		doReturn(comando).when(requisicao).getComando(anyString());
		requisicao.run();
		
		verify(comando).executa(entrada, saida);
	}
	
	@Test
	public void entradaPassandoTipoGetArquivoRetornaComandoGetArquivo() throws Exception {
		assertEquals(ComandoGetArquivo.class, requisicao.getComando(ComandoTipo.GET_ARQUIVO.name()).getClass());
	}
	
	@Test
	public void erroAoExecutarComandoLancaExcecaoTratada() throws Exception {
		excecao.expect(FileControlException.class);
		excecao.expectMessage(Excecao.ERRO_AO_EXECUTAR_REQUISICAO.getChave());

		doReturn(comando).when(requisicao).getComando(anyString());
		doThrow(IOException.class).when(comando).executa((BufferedReader)anyObject(), (DataOutputStream)anyObject());
		
		requisicao.run();
	}
	
	@Test
	public void setaVariaveisEntradaESaidaAoCriarRequisicao() throws Exception {
		Socket conexao = mock(Socket.class);

		InputStream inputStream = mock(InputStream.class);
		when(conexao.getInputStream()).thenReturn(inputStream);
		
		OutputStream outputStream = mock(OutputStream.class);
		when(conexao.getOutputStream()).thenReturn(outputStream);
		
		Requisicao requisicao = new Requisicao(conexao);
		
		assertNotNull(requisicao.entrada);
		assertNotNull(requisicao.saida);
	}

}
