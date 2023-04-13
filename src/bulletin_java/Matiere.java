package bulletin_java;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/*Application Java SE/MySQL pour �tablir automatiquement les bulletins de notes des �l�ves
 * r�alis�e du 05 au 08 Juin 2021 � N'djam�na au Tchad par TARGOTO Christian
 * Contact: ct@chrislink.net/ 23560316682*/
public class Matiere extends JFrame {
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JTable table,table2;
	JScrollPane scroll,scroll2;
	JLabel lbtitre1,lbnom_matiere,lbcoeff;
	JTextField tfnom_matiere,tfcoeff;
	JButton btenrg,btsupp;

	public Matiere(){
		this.setTitle("chcode_appli");
		this.setSize(500,450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		add(pn);
		pn.setBackground(new Color(200,250,190));
		
		lbtitre1=new JLabel("Formulaire d'enregistrement des matiéres");
		lbtitre1.setBounds(50,10,800,30);
		lbtitre1.setFont(new Font("Arial",Font.BOLD,18));
		lbtitre1.setForeground(new Color(100,150,100));
		pn.add(lbtitre1);
		
		//nom matiere
		lbnom_matiere=new JLabel("Nom matiére");
		lbnom_matiere.setBounds(60,60,100,25);
		lbnom_matiere.setFont(new Font("Arial",Font.BOLD,14));
		lbnom_matiere.setForeground(new Color(100,150,100));
		pn.add(lbnom_matiere);
		
		tfnom_matiere=new JTextField();
		tfnom_matiere.setBounds(190,60,150,25);
		pn.add(tfnom_matiere);
		
		//coefficient
		lbcoeff=new JLabel("Coefficient");
		lbcoeff.setBounds(70,105,100,25);
		lbcoeff.setFont(new Font("Arial",Font.BOLD,14));
		lbcoeff.setForeground(new Color(100,150,100));
		pn.add(lbcoeff);
		
		tfcoeff=new JTextField();
		tfcoeff.setBounds(190,105,150,25);
		pn.add(tfcoeff);
		//bouton pour enregistrer les matieres
		btenrg=new JButton("ENREGISTRER");
		btenrg.setBounds(20,170,150,25);
		btenrg.setFont(new Font("Arial",Font.BOLD,14));
		btenrg.setForeground(Color.white);
		btenrg.setBackground(new Color(100,200,100));
		btenrg.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String nom,coeff;
				nom=tfnom_matiere.getText();
				coeff=tfcoeff.getText();
	String rq="insert into tb_matiere(nom_mat,coeff) values('"+nom+"','"+coeff+"')";
			try{
				st=con.laConnection().createStatement();
				st.executeUpdate(rq);
				JOptionPane.showMessageDialog(null,"Enregistrement éffectué avec succés !",null,JOptionPane.INFORMATION_MESSAGE);
			}
			catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
		    }
			dispose();
			Matiere elv=new Matiere();
			elv.setVisible(true);
			
			}
			
		});
		pn.add(btenrg);
		//bouton pour supprimer un enregistrement de matiere
		btsupp=new JButton("SUPPRIMER");
		btsupp.setBounds(190,170,150,25);
		btsupp.setFont(new Font("Arial",Font.BOLD,14));
		btsupp.setForeground(Color.white);
		btsupp.setBackground(new Color(100,200,100));
		btsupp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String nom=tfnom_matiere.getText();
		String rq="delete from tb_matiere where nom_mat='"+nom+"'";
		try{
			st=con.laConnection().createStatement();
			st.executeUpdate(rq);
			JOptionPane.showMessageDialog(null,"Suppréssion éffectuée avec succés !",null,JOptionPane.INFORMATION_MESSAGE);
		}
		catch(SQLException ex){
	    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
	    }
		dispose();
		Matiere elv=new Matiere();
		elv.setVisible(true);	
			}
			
		});
		pn.add(btsupp);
		//liste des matieres
		DefaultTableModel df2=new  DefaultTableModel();
		  init2();
		  pn.add(scroll2);
		 df2.addColumn("Matiére");
		 df2.addColumn("Coefficient");
		
		 table2.setModel(df2);
		 String req2="select * from tb_matiere  ";
		 try{
			 st=con.laConnection().createStatement();
			 rst=st.executeQuery(req2);
			 while(rst.next()){
				 df2.addRow(new Object[]{
rst.getString("nom_mat"),rst.getString("coeff")

						 });
				 
			 }
			 
				 
			 }
			 
		 catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
		    }
	}
	private void init2(){
		table2=new JTable();
		scroll2=new JScrollPane();
		scroll2.setBounds(10,220,470,190);
		scroll2.setViewportView(table2);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
   Matiere mt=new Matiere();
   mt.setVisible(true);
	}

}
