package br.com.jmassucatto.filecontrol.server.comando;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ComandoTipo.class)
public class ComandoFactoryComandoInvalidoTest {
	
	@Rule public ExpectedException excecao = ExpectedException.none();
	
	@Test
	public void passandoTipoInvalidoDaErro() throws Exception {
		excecao.expect(FileControlException.class);
		excecao.expectMessage(Excecao.COMANDO_INVALIDO.getChave());
		
		ComandoTipo invalido = PowerMockito.mock(ComandoTipo.class);
		Whitebox.setInternalState(invalido, "name", "INVALIDO");
		Whitebox.setInternalState(invalido, "ordinal", 2);
		
		PowerMockito.mockStatic(ComandoTipo.class);
	    Mockito.when(ComandoTipo.values()).thenReturn(new ComandoTipo[]{ComandoTipo.GET_ARQUIVOS, ComandoTipo.GET_ARQUIVO, invalido});
	    
		new ComandoFactory(invalido).getComando();
	}
}
