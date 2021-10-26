package runData;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.allEnums.Modules;

import javax.swing.*;

public class RunSettings {

	private JFrame ourFrame = new JFrame("Run Test Suite");

	public RunSettings(){

		ourFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ourFrame.setBounds(200, 100, 700, 900);

		Container container = ourFrame.getContentPane();
		container.setLayout(null);

		JLabel logo = new JLabel("Run KFC Peru");
		logo.setBounds(350,5,250,30);

		JRadioButton runall=new JRadioButton("Run All"); 
		runall.setBounds(120,30,250,30);

		JRadioButton runModule=new JRadioButton("Run a Module"); 
		runModule.setBounds(120,60,250,30);

		Modules[] modules = Modules.class.getEnumConstants();
		String mods[]= new String[modules.length];

		for(int i=0;i<modules.length;i++) {
			
			mods[i]=modules[i].toString();
		}        
		JComboBox<?> cb=new JComboBox<Object>(mods);    
		cb.setBounds(120,90,250,30);  

		String placeOrders[]={"Yes Place Orders","Donot place orders"};        
		JComboBox<?> po=new JComboBox<Object>(placeOrders);    
		po.setBounds(120,120,250,30); 


		JLabel chromepath = new JLabel("Chrome Path :");
		chromepath.setBounds(20,150,250,30);
		JTextField chromepathText = new JTextField();
		chromepathText.setBounds(120,150,250,30);

		String rerun[]={"Re-Run Failed Test Cases","Donot Run Failed Test Cases"};        
		JComboBox<?> rr=new JComboBox<Object>(rerun);    
		rr.setBounds(120,180,250,30); 

		JRadioButton runRange=new JRadioButton("Run a Range of Test Cases"); 
		runRange.setBounds(120,210,250,30);

		JLabel runRangeLabel = new JLabel("Run Test Cases From :");
		runRangeLabel.setBounds(20,240,250,30);
		JTextField from = new JTextField();
		from.setBounds(150,240,40,30);
		JLabel runRangeLabelTo = new JLabel("To :");
		runRangeLabelTo.setBounds(190,240,250,30);
		JTextField to = new JTextField();
		to.setBounds(210,240,40,30);
		/*JLabel email_label = new JLabel("Email :");
		email_label.setBounds(20,60,250,30);

		JTextField nameText = new JTextField();
		nameText.setBounds(65,30,250,30);

		JTextField emailText = new JTextField();
		emailText.setBounds(65, 60, 250, 30);*/

		JButton runBtn  = new JButton("Run");
		runBtn.setBounds(150,300,100,30);

		container.add(logo);
		container.add(runall);
		container.add(runModule);
		container.add(cb);
		container.add(po);
		container.add(chromepath);
		container.add(chromepathText);
		container.add(rr);
		container.add(runRange);
		container.add(runRangeLabel);
		container.add(from);
		container.add(runRangeLabelTo);
		container.add(to);
		container.add(runBtn);
		
		chromepathText.setText(".\\Drivers\\chromedriver.exe");
		ourFrame.setVisible(true);

		runBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println(runall.isSelected());
				System.out.println(runModule.isSelected());
				System.out.println(cb.getItemAt(cb.getSelectedIndex()));
				System.out.println(po.getItemAt(po.getSelectedIndex()));
				System.out.println(chromepathText.getText());
				System.out.println(rr.getItemAt(rr.getSelectedIndex()));
				System.out.println(runRange.isSelected());
				System.out.println(from.getText());
				System.out.println(to.getText());
			}
		});

	}
}
