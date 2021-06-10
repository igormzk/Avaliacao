package br.com.avaliacao.view.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Classe que valida os caracteres digitados.
 * Esta classe permite que apenas número inteiros sejam informados.
 * @author Igor Muzeka
 * @version 1.0
 */
public class IntegerDocument extends PlainDocument{
	
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		try {
			Integer.parseInt(str);
			super.insertString(offs, str, a);
		} catch (NumberFormatException e) {
		}
	}

}
