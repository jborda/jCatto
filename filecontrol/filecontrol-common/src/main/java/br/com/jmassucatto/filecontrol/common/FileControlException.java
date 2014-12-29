package br.com.jmassucatto.filecontrol.common;

public class FileControlException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private Excecao excecao;
	private Object[] parametros;

	public FileControlException(Excecao excecao, Object... parametros) {
		super(excecao.getChave());
		this.excecao = excecao;
		this.parametros = parametros;
	}
	
	public FileControlException(Excecao excecao,  Throwable causa, Object... parametros) {
		super(excecao.getChave(), causa);
		this.excecao = excecao;
		this.parametros = parametros;
	}
	
	public Excecao getExcecao() {
		return excecao;
	}

}
