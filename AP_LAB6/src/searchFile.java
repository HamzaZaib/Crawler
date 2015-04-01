import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;


public class searchFile {

	private JFrame frame;
	private JPanel panel;
	private JButton btn;
	private String currentDir="E:\\lab3";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchFile window = new searchFile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public searchFile() {
		new localSpider();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 37, 434, 224);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function(null,1);
			}
		});
		btnBack.setBounds(268, 11, 89, 23);
		frame.getContentPane().add(btnBack);
		
		
		Vector<String> temp=localSpider.find(currentDir);
		for(int i=0;i<temp.size();i++){
			btn = new JButton(temp.elementAt(i));
			btn.setBounds(5, 5+i*20, temp.elementAt(i).length()*14, 14);
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent){
					function(actionEvent,0);
				}
			});
			panel.add(btn);
			panel.revalidate();
			panel.repaint();
		}
	}
	
	public void function(ActionEvent actionEvent,int mode){
		Vector<String> temp=null;
		String label="";
		if(mode==0){
			JButton btn=(JButton)actionEvent.getSource();
			label=btn.getText();
			temp=localSpider.find(currentDir+"\\"+label);
		}
		else{
			currentDir=currentDir.substring(0, currentDir.lastIndexOf('\\'));
			temp=localSpider.find(currentDir);
		}
		if(temp!=null){
			panel.removeAll();
			currentDir+="\\"+label;
			for(int i=0;i<temp.size();i++){
				btn = new JButton(temp.elementAt(i));
				btn.setBounds(5, 5+i*20, temp.elementAt(i).length()*14, 14);
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent actionEvent){
						function(actionEvent,0);
					}
				});
				panel.add(btn);
			}
			panel.revalidate();
			panel.repaint();
		}
	}
}
