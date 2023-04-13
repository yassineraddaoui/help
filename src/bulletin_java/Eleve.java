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

public class Eleve extends JFrame {
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JTable table,table2;
	JScrollPane scroll,scroll2;
	JLabel lbtitre1,lbnum_eleve,lbnom,lbsexe,lbclasse;
	JTextField tfnum_eleve,tfnom;
	JButton btenrg,btsupp;
	JComboBox combosexe,comboclasse;
	
	public Eleve(){
		this.setTitle("chcode_appli");
		this.setSize(800,450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		add(pn);
		pn.setBackground(new Color(200,250,190));
		
		lbtitre1=new JLabel("Formulaire d'enregistrement des éléves");
		lbtitre1.setBounds(50,10,800,30);
		lbtitre1.setFont(new Font("Arial",Font.BOLD,18));
		lbtitre1.setForeground(new Color(100,150,100));
		pn.add(lbtitre1);
		
		//num�ro eleve
				lbnum_eleve=new JLabel("Numéro éléve");
				lbnum_eleve.setBounds(60,60,100,25);
				lbnum_eleve.setFont(new Font("Arial",Font.BOLD,14));
				lbnum_eleve.setForeground(new Color(100,150,100));
				pn.add(lbnum_eleve);
				
				tfnum_eleve=new JTextField();
				tfnum_eleve.setBounds(190,60,150,25);
				pn.add(tfnum_eleve);
		//nom prenom
				lbnom=new JLabel("Nom et Prénom");
				lbnom.setBounds(52,90,150,25);
				lbnom.setFont(new Font("Arial",Font.BOLD,14));
				lbnom.setForeground(new Color(100,150,100));
				pn.add(lbnom);
				
				tfnom=new JTextField();
				tfnom.setBounds(190,90,150,25);
				pn.add(tfnom);
		//sexe
				lbsexe=new JLabel("Sexe");
				lbsexe.setBounds(125,120,150,25);
				lbsexe.setFont(new Font("Arial",Font.BOLD,14));
				lbsexe.setForeground(new Color(100,150,100));
				pn.add(lbsexe);
				
				combosexe=new JComboBox();
				combosexe.addItem("");
				combosexe.addItem("FEMININ");
				combosexe.addItem("MASCULIN");
				combosexe.setBounds(190,120,100,25);
				pn.add(combosexe);
		//classe
				lbclasse=new JLabel("Classe");
				lbclasse.setBounds(115,150,150,25);
				lbclasse.setFont(new Font("Arial",Font.BOLD,14));
				lbclasse.setForeground(new Color(100,150,100));
				pn.add(lbclasse);
				
				comboclasse=new JComboBox();
				comboclasse.addItem("");
				comboclasse.addItem("6EME");
				comboclasse.addItem("5EME");
				comboclasse.addItem("4EME");
				comboclasse.addItem("3EME");
				comboclasse.addItem("2NDE L");
				comboclasse.addItem("2NDE S");
				comboclasse.addItem("1ERE L");
				comboclasse.addItem("1ERE S");
				comboclasse.addItem("TA");
				comboclasse.addItem("TD");
				comboclasse.addItem("TC");
				comboclasse.setBounds(190,150,100,25);
				pn.add(comboclasse);
				
				//bouton pour enregistrer les eleves
				btenrg=new JButton("ENREGISTRER");
				btenrg.setBounds(20,200,150,25);
				btenrg.setFont(new Font("Arial",Font.BOLD,14));
				btenrg.setForeground(Color.white);
				btenrg.setBackground(new Color(100,200,100));
				btenrg.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String num,nom,sexe,classe;
						num=tfnum_eleve.getText();
						nom=tfnom.getText();
						sexe=combosexe.getSelectedItem().toString();
						classe=comboclasse.getSelectedItem().toString();
			String rq="insert into tb_eleve(num_eleve,nom,classe,sexe) values('"+num+"','"+nom+"','"+classe+"','"+sexe+"')";
					try{
						st=con.laConnection().createStatement();
						st.executeUpdate(rq);
						JOptionPane.showMessageDialog(null,"Enregistrement �ffectu� avec succ�s !",null,JOptionPane.INFORMATION_MESSAGE);
					}
					catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
				    }
					dispose();
					Eleve elv=new Eleve();
					elv.setVisible(true);
					
					}
					
				});
				pn.add(btenrg);
				
				//bouton pour supprimer un enregistrement d'eleve
				btsupp=new JButton("SUPPRIMER");
				btsupp.setBounds(190,200,150,25);
				btsupp.setFont(new Font("Arial",Font.BOLD,14));
				btsupp.setForeground(Color.white);
				btsupp.setBackground(new Color(100,200,100));
				btsupp.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String num=tfnum_eleve.getText();
				String rq="delete from tb_eleve where num_eleve='"+num+"'";
				try{
					st=con.laConnection().createStatement();
					st.executeUpdate(rq);
					JOptionPane.showMessageDialog(null,"Suppr�ssion �ffectu�e avec succ�s !",null,JOptionPane.INFORMATION_MESSAGE);
				}
				catch(SQLException ex){
			    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
			    }
				dispose();
				Eleve elv=new Eleve();
				elv.setVisible(true);	
					}
					
				});
				pn.add(btsupp);
				//liste des eleves enregistres
				 DefaultTableModel df2=new  DefaultTableModel();
				  init2();
				  pn.add(scroll2);
				 df2.addColumn("Numéro");
				 df2.addColumn("Nom et Prénom");
				 df2.addColumn("Sexe");
				 df2.addColumn("Classe");
				
				 table2.setModel(df2);
				 String req2="select * from tb_eleve order by num_eleve desc  ";
				 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(req2);
					 while(rst.next()){
						 df2.addRow(new Object[]{
		rst.getString("num_eleve"),rst.getString("nom"),rst.getString("sexe"),rst.getString("classe")

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
		scroll2.setBounds(10,250,770,150);
		scroll2.setViewportView(table2);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Eleve elv=new Eleve();
     elv.setVisible(true);
	}

}
