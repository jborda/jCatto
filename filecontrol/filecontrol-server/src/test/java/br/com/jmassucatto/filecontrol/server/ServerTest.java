package br.com.jmassucatto.filecontrol.server;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
@Ignore
public class ServerTest {
	
	@SuppressWarnings("rawtypes")
	@Test
	public void serverSemArquivosRetoraListaVazia() throws Exception {
		Server server = new Server();
		assertEquals(new HashMap(), server.getNomeArquivos());
	}
	
	@Test
	public void serverComUmArquivoRetornaListaComNomeDoArquivo() throws Exception {
		Server server = new Server();
//		server.arquivos.put("ArquivoUm", "ArquivoUm");
		HashMap<String, String> arquivos = new HashMap<String, String>();
		arquivos.put("ArquivoUm", "ArquivoUm");
		assertEquals(arquivos, server.getNomeArquivos());
	}
	
	@Test
	public void getArquivoUm() throws Exception {
		Server server = spy(new Server());
		Map arquivos = mock(Map.class);
		when(arquivos.get("ArquivoUm")).thenReturn("ArquivoUm");
		doReturn(arquivos).when(server).getNomeArquivos();
//		assertEquals("ArquivoUm", server.getArquivo("ArquivoUm"));
	}
	
	@Test
	public void getArquivoDois() throws Exception {
		Server server = mock(Server.class);
//		when(server.getArquivos()).thenReturn(Arrays.asList("ArquivoDois"));
		
//		assertEquals("ArquivoDois", server.getArquivo("ArquivoDois"));
	}

}
