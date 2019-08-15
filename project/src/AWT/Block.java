package AWT;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream.GetField;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.ietf.jgss.GSSManager;

public class Block extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String imagePath = null;// = "image/a0.jpg";
	public int num;
	public ImageIcon openedIcon; // = new ImageIcon(imagePath);

	/**
	 * Create the application.
	 */
//	public Block(String imagePath) {
//		this.btnItem = new JButton("");
//		// this.openedIcon = new ImageIcon(imagePath);
//		this.imagePath = imagePath;
//	}
	public Block(String img, int i) {

		this.imagePath = img;
		this.num = i;
	}

	/**
	 * Initialize the contents of the frame.
	 */

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		// setOpenedIcon();
//
//	}

	public void setNullIcon() {
		super.setIcon(null);
	}

	public void setOpenedIcon() {

		openedIcon = new ImageIcon(imagePath);
		super.setIcon(openedIcon);
		// System.out.println(num);
		// System.out.println(imagePath);

	}

	public String getOpenedIcon() {
		return imagePath;
	}

}
