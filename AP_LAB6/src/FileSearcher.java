import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.Color;
import javax.swing.SwingConstants;


public class FileSearcher {

	private JFrame frame;
	private JPanel panel;
	private JTextField textField;
	private JLabel lblFileName;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileSearcher window = new FileSearcher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FileSearcher() {
		new localSpider();
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(133, 64, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblFileName = new JLabel("File Name");
		lblFileName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFileName.setBounds(51, 67, 58, 14);
		frame.getContentPane().add(lblFileName);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.removeAll();
				Vector<String> temp=localSpider.find(textField.getText());
				if(temp==null){
					JLabel label=new JLabel("No Files Found.");
					label.setHorizontalAlignment(SwingConstants.LEFT);
					label.setBounds(5, 5, 100, 14);
					panel.add(label);
				}
				else{
					JLabel label=new JLabel("Results:");
					label.setBounds(5, 5, 46, 14);
					panel.add(label);
					for(int i=0;i<temp.size();i++){
						label=new JLabel("-"+temp.elementAt(i));
						label.setBounds(5,25+i*20,temp.elementAt(i).length()*5+5,14);
						label.setHorizontalAlignment(SwingConstants.LEFT);
						panel.add(label);
					}
				}
				frame.revalidate();
				frame.repaint();
			}
		});
		btnNewButton.setBounds(259, 66, 76, 20);
		frame.getContentPane().add(btnNewButton);
		
		panel= new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(51, 106, 168, 128);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
	}
}
