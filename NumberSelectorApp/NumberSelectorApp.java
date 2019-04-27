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

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NumberSelectorApp{
	boolean isStop=false;
	Thread thread;
	private JPanel panel1=new JPanel();
	private JPanel panel2=new JPanel();
	private JButton startbutton=new JButton("START");
	private JButton stopbutton=new JButton("STOP");
	private JLabel label=new JLabel();
	Random random=new Random();
	
}
