package br.com.jmassucatto.filecontrol.common;

public class FileControlException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private Excecao excecao;

	public FileControlException(Excecao excecao) {
		super(excecao.getChave());
		this.excecao = excecao;
	}
	
	public Excecao getExcecao() {
		return excecao;
	}

}
