package info.mattweppler.finddupes;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Logger
{
	private JFrame frame;
	private Container content;
	private JTextArea textArea;
	
	public Logger()
	{		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550,550);
        content = frame.getContentPane();
        content.setBackground(Color.white);
        content.setLayout(new FlowLayout());
        textArea = new JTextArea(32,45);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
		content.add(scrollPane);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        frame.setLocation(x,y);
        frame.setVisible(true);
	}
	
	public void displayLog(String log)
	{
		if (log.equals("")) {
			log = "No Dupes Found.";
		}
		//System.out.println(log);
		textArea.append(log);
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

}
