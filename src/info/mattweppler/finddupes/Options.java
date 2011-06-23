package info.mattweppler.finddupes;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Options implements ActionListener
{
	private JFrame frame;
	private Container content;
	private JComboBox uniqueFieldOption;
	private JComboBox fieldTypeOption;
	private JButton submitButton;
	private String[] arguments;
	private String[] headerRow;
	private int fieldType;
	private int uniqueField;
	public CustomClass customClass;
	
	public Options(String[] args, String[] hrow)
	{
		arguments = args;
		headerRow = hrow;
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
        content = frame.getContentPane();
        content.setBackground(Color.white);
        content.setLayout(new FlowLayout());
		uniqueFieldOption = new JComboBox();
		fieldTypeOption = new JComboBox();
		setComboBoxes();
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		customClass = new CustomClass();
		content.add(uniqueFieldOption);
        content.add(fieldTypeOption);
        content.add(submitButton);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        frame.setLocation(x,y);
        frame.setVisible(true);
	}
	
	public int getFieldType()
	{
		return fieldType;
	}
	
	public int getUniqueField()
	{
		return uniqueField;
	}
	
	private void setFieldType(int s)
	{
		fieldType = s;
	}
	
	private void setUniqueField(int s)
	{
		uniqueField = s;
	}
	
	private void setComboBoxes()
	{
		fieldTypeOption.addItem("String");
		fieldTypeOption.addItem("Number");
		for (int i=0;i<headerRow.length;i++) {
			uniqueFieldOption.addItem(headerRow[i]);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(submitButton)) {
			System.out.print(fieldTypeOption.getItemAt(fieldTypeOption.getSelectedIndex()).toString());
			System.out.println(":"+fieldTypeOption.getSelectedIndex());
			System.out.print(uniqueFieldOption.getItemAt(uniqueFieldOption.getSelectedIndex()).toString());
			System.out.println(":"+uniqueFieldOption.getSelectedIndex());
			setFieldType(fieldTypeOption.getSelectedIndex());
			setUniqueField(uniqueFieldOption.getSelectedIndex());
			frame.setVisible(false);
			customClass.fireEvent(null);
		}
	}
	
	public void addCustomListener(CustomListener con) {
		customClass.addEventListener(con);
	}
}
