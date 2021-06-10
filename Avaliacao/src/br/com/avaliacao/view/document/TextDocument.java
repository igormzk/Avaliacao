package br.com.avaliacao.view.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Classe que limita o número de caracteres informado pelo usuário para
 * que os campos da tela sejam compatíveis com a quantidade de caracteres permitida
 * em uma coluna de uma tabela no banco de dados.
 * @author Igor Muzeka
 * @version 1.0
 */
public class TextDocument extends PlainDocument {
	
	private final int maxLength;
	
	public TextDocument(int maxLength) {
		super();
		this.maxLength = maxLength;
	}
	
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if (str == null) return;
		int length = getLength();
		int free = maxLength - length;
		if (free > 0) {
			int strLength = str.length();
			if (strLength > free)
				strLength = free;
			super.insertString(offs, str.substring(0,strLength), a);
		}	
	}

}
