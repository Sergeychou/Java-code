/**
 *@author chikaiiii
 *@version 1.0
 *@since 26/4/2019
 */

package NumberSelectorApp;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NumberSelectorApp extends JFrame{
	boolean isStop=false;
	Thread thread;
	private JPanel panel1=new JPanel();
	private JPanel panel2=new JPanel();
	private JButton startbutton=new JButton("START");
	private JButton stopbutton=new JButton("STOP");
	private JLabel label=new JLabel();
	Random random=new Random();
	
	public NumberSelectorApp() 
	{
		addCompontents();
		addAction_button();
		initialization();
	}
	
	public void initialization()
	{
		setSize(300,250);
		setLocationRelativeTo(null);
		setTitle("NumberSelectorApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public void addCompontents() {
		panel1.add(label);
		panel2.add(startbutton);
		panel2.add(stopbutton);
		getContentPane().add(panel1,BorderLayout.CENTER);
		getContentPane().add(panel2,BorderLayout.SOUTH);
	}
	
	public void runtest() {
		random=new Random();
		
		thread=new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				int ans;
				while(!(isStop)) {
					try {
						ans=random.nextInt(300);
						label.setText(""+ans);
						label.setFont(new Font("Times New Roman",1,36));
						label.setForeground(Color.black);
						thread.sleep(100);
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, "FAILED");
					}

			}
			}
			
		});
		thread.start();
	}
	
	public void addAction_button() {
		startbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					isStop=false;
					runtest();
				}catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null,"FAILED>_<!!!");
				}
			}
		});
		stopbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isStop=true;
				
				label.setForeground(Color.GREEN);
			}
		});
	}
	
	public static void main(String[] args) {
		new NumberSelectorApp();
		
	}

}
