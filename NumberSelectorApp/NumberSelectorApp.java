/**
 *@author chikaiiii
 *@version 1.0
 *@since 26/4/2019
 */

package NumberSelectorApp;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;


public class NumberSelectorApp extends JFrame{
	
	boolean key=true;
	
	private JPanel panel=new JPanel();

	
	public NumberSelectorApp()
	{
		setSize(250,300);
		setLocationRelativeTo(null);
		setTitle("NumberSelectorApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton startbutton=new JButton("START");
		JButton stopbutton=new JButton("STOP");
		JButton resetbutton=new JButton("RESET");
		panel.setLayout(null);
		startbutton.setLocation(75,120);
		startbutton.setSize(100,30);
		stopbutton.setLocation(75,160);
		stopbutton.setSize(100,30);
		resetbutton.setLocation(75,200);
		resetbutton.setSize(100,30);
		
		JTextField jt=new JTextField();
		
		
		panel.add(startbutton);
		panel.add(stopbutton);
		panel.add(resetbutton);
		panel.add(jt);
		add(panel);
		
		
		startbutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				key=true;
				run(jt);
		}
		});
		stopbutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				key=false;
				run(jt);
			}
		});
		resetbutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				JButton clickbutton=(JButton) e.getSource();
				clickbutton.setText("funny!");
			}
		});
	}
	
	
		public void run(JTextField jt) {
		while (key) {
		try {
		Thread.sleep(100);
		Random random = new Random();
		int x=random.nextInt(60);
		jt.setText(String.valueOf(x));
		jt.setForeground(Color.blue); // 设置字体颜色
		jt.setFont(new Font("Dialog", 1, 12)); // 设置字体样式、大小
		jt.setLocation(75, 20);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		key=false;
		}
		}
	
	public static void main(String[] args) {
	NumberSelectorApp frame=new NumberSelectorApp();
	frame.setVisible(true);
	}

}