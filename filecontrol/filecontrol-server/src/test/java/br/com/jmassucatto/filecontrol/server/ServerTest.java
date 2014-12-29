package br.com.jmassucatto.filecontrol.server;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

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

@RunWith(MockitoJUnitRunner.class)
public class ServerTest {
	
	private static final String ARQUIVO_DOIS = "dois.txt";
	private static final String ARQUIVO_UM = "um.txt";
	
	@Spy private Server server;
	@Mock private File diretorio;
	@Rule public ExpectedException excecao = ExpectedException.none();
	
	@Before
	public void setup() {
		doReturn(diretorio).when(server).getDiretorio();
	}
	
	@Test
	public void serverSemArquivosRetoraListaVazia() throws Exception {
		assertEquals(new ArrayList<String>(), server.getNomeArquivos());
	}
	
	@Test
	public void serverComUmArquivoRetornaListaComNomeDoArquivo() throws Exception {
		mockArquivos(ARQUIVO_UM);
		assertEquals(Arrays.asList(ARQUIVO_UM), server.getNomeArquivos());
	}
	
	@Test
	public void getArquivoUm() throws Exception {
		mockArquivos(ARQUIVO_UM);
		assertEquals(ARQUIVO_UM, server.getArquivo(ARQUIVO_UM).getName());
	}
	
	@Test
	public void getArquivoEmListaVaziaLancaExcecao() throws Exception {
		mockArquivos();
		excecao.expect(FileControlException.class);
		excecao.expectMessage(Excecao.ARQUIVO_NAO_ENCONTRADO.getChave());
		server.getArquivo(ARQUIVO_UM);
	}
	
	@Test
	public void getArquivoInexistenteLancaExcecao() throws Exception {
		mockArquivos(ARQUIVO_UM);
		excecao.expect(FileControlException.class);
		excecao.expectMessage(Excecao.ARQUIVO_NAO_ENCONTRADO.getChave());
		server.getArquivo(ARQUIVO_DOIS);
	}

	private void mockArquivos(String... nomesArquivos) {
		File[] arquivos = new File[nomesArquivos.length];
		
		for (int x = 0; x < nomesArquivos.length; x++)
			arquivos[x] = mockArquivo(nomesArquivos[x]);
		
		when(diretorio.listFiles()).thenReturn(arquivos);
	}

	private File mockArquivo(String nomeArquivo) {
		File arquivo = mock(File.class);
		when(arquivo.getName()).thenReturn(nomeArquivo);
		return arquivo;
	}

}
