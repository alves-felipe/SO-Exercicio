package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {
	
	public static void main(String[] args) {
		RedesController rc = new RedesController();
		int op = 0;
		
		while(op != 9) {
		
			op = Integer.parseInt(JOptionPane.showInputDialog("1 - ip\n2 - ping\n9 - finalizar"));
		
			if(op == 1) rc.ip();
			if(op == 2) rc.ping();
		}
	}

}
