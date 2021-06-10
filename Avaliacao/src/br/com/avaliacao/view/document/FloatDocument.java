package br.com.avaliacao.view.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Classe que valida os caracteres digitado pelo usuário.
 * Esta classe válida números com vírgula sendo que mantêm apenas um dígito como decimal e
 * no máximo dois dígitos como parte inteira
 * @author Igor Muzeka
 * @version 1.0
 */
public class FloatDocument extends PlainDocument {
	
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

		String original = getText(0, getLength());
		StringBuilder sb = new StringBuilder();
		remove(0, getLength());
		for (char c : original.toCharArray()) {
			if (Character.isDigit(c)) 	{
				sb.append(c);
			}
		}
		boolean isDigit = true;
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				isDigit = false;
			}
		}
		
		if (isDigit) {
			sb.append(str);
			if (sb.length() == 1) {
				sb.insert(0, ",");
				sb.insert(0, "0");
			} else if (sb.length() == 2) {
				sb.insert(0, "0");
				sb.insert(2, ",");
			} else if (sb.length() == 3) {
				sb.insert(2, ",");
			} else if (sb.length() == 4) {
				sb = sb.replace(0, 1, "");
				sb.insert(2, ",");
			}
			
			super.insertString(0, sb.toString(), a);
		} else {
			super.insertString(0, original, a);
		}
	}

}
