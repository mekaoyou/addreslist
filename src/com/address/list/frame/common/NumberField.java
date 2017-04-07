package com.address.list.frame.common;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class NumberField extends JTextField
{
	private static final long serialVersionUID = 1L;

	public NumberField()
	{
		super();
		this.addCaretListener(new TextFieldInputListener());
	}

	class TextFieldInputListener implements CaretListener
	{
		@Override
	    public void caretUpdate(CaretEvent e) 
	    {
	        JTextField textField = (JTextField) e.getSource(); 
	        String text = textField.getText();
	        if (text.length() == 0) {
	            return;
	        }
	        char ch = text.charAt(text.length() - 1);
	        if (!(ch >= '0' && ch <= '9')) {
	            SwingUtilities.invokeLater(new Runnable() {
	                @Override
	                public void run() {
	                    textField.setText(text.substring(0, text.length() - 1));
	                }
	            });
	        }
	    }
	}
}
