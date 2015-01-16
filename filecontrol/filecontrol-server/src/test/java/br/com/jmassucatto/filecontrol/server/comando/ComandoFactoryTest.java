package br.com.jmassucatto.filecontrol.server.comando;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;

public class ComandoFactoryTest {
	
	@Rule public ExpectedException excecao = ExpectedException.none();
	
	@Test
	public void passandoTipoGetArquivosRetornaComandoGetArquivos() throws Exception {
		ComandoFactory factory = new ComandoFactory(ComandoTipo.GET_ARQUIVOS);
	 	assertEquals(ComandoGetArquivos.class, factory.getComando().getClass());
	}
	
	@Test
	public void passandoTipoGetArquivoRetornaComandoGetArquivo() throws Exception {
		ComandoFactory factory = new ComandoFactory(ComandoTipo.GET_ARQUIVO);
		assertEquals(ComandoGetArquivo.class, factory.getComando().getClass());
	}
	
	@Test
	public void passandoTipoNullDaErro() throws Exception {
		excecao.expect(FileControlException.class);
		excecao.expectMessage(Excecao.COMANDO_INVALIDO.getChave());
		
		new ComandoFactory(null);
	}
}
