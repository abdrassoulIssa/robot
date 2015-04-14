package swing;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;


@SuppressWarnings("serial")
public class JsplitPane extends JFrame implements ActionListener
{
	JFrame fenetre1;
	JPanel panelGauche;
	JPanel panelDroite;
	JScrollPane jsp;
	JSplitPane splitPane,splitPane2;
	JPanel panelboutton;
	JButton pre,nex,fin,can;
	JPanel panelgen;
		
	JsplitPane()
	{
		  fenetre1 =  new JFrame("Report Wizard");
		 		  
		  panelGauche = new JPanel(new BorderLayout());
		  panelGauche.setBackground(new Color(255, 255, 255));
		  panelGauche.setLayout(null);
		  //panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.Y_AXIS));
		  panelGauche.setBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createTitledBorder("Contents"),
                  BorderFactory.createEmptyBorder(10,10,10,10)));
		  		  		  
		  panelDroite = new JPanel();
		  panelDroite.setLayout(null);
		  panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.Y_AXIS));
		  panelDroite.setBorder(BorderFactory.createCompoundBorder
				               (BorderFactory.createTitledBorder
				                ("Step Data Interval and Data Type Selection"),
				               BorderFactory.createEmptyBorder(1,1,1,1)));
          
		  
		  
		  //on créé le splitPane avec une separation Horizontal (barre à la vertical)
		  splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panelGauche,panelDroite);
		  //Place la barre de séparation a 200 px
		  splitPane.setDividerLocation(200);
		  //Permet de placer directement a gauche ou a droite la barre grace a un clic
		  splitPane.setOneTouchExpandable(true);
		  
		  
		  panelboutton = new JPanel(new BorderLayout());
		  panelboutton.setLayout(null);
		  panelboutton.setSize(50, 100);
		  
		  pre = new JButton ("<html><body><u>P</u>revious</body></html>");
		  pre.setBounds(295, 15, 90, 25);
		  panelboutton.add(pre);
		  
		  nex = new JButton ("<html><body><u>N</u>ext</body></html>");
		  nex.setBounds(390, 15, 90, 25);
		  nex.addActionListener(this);
		  //nex.setEnabled(false);
		  panelboutton.add(nex);
		  pre.addActionListener(this);
		  
		  fin = new JButton ("<html><body><u>F</u>inish</body></html>");
		  fin.setBounds(485, 15, 90, 25);
		  fin.setEnabled(false);
		  panelboutton.add(fin);
		  
		  can = new JButton ("<html><body><u>C</u>ancel</body></html>");
		  can.setBounds(580, 15, 90, 25);
		  panelboutton.add(can);
		  
		  splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitPane,panelboutton);
		  splitPane2.setDividerLocation(455);
		  		  
		  fenetre1.add(splitPane2);
		  fenetre1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  fenetre1.setResizable(false);
		  fenetre1.setBounds(150, 150, 685, 550);
		  fenetre1.setVisible(true);
			
	}
		
	public void actionPerformed(ActionEvent e) {
		
	}

	public static class exemple1 extends JFrame {
		public static void main (String[] args){
			new JsplitPane ();
		}
	}
	
}