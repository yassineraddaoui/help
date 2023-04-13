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
/*Application Java SE/MySQL pour établir automatiquement les bulletins de notes des élèves
 * réalisée du 05 au 08 Juin 2021 à N'djaména au Tchad par TARGOTO Christian
 * Contact: ct@chrislink.net/ 23560316682*/
public class Bulletin extends JFrame {
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JTable table,table2;
	JScrollPane scroll,scroll2;
	JLabel lbtitre1,lbnum_eleve,lbnom,lbnom2,lbsexe,lbsexe2,lbclasse,lbclasse2,lbtrim,lblettre,
	lbscience,lbmoyg,lbrang;
	JTextField tfnum_eleve;
	JButton brech,btactu;
	JComboBox combotrim;
	public Bulletin(){
		this.setTitle("chcode_appli");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		add(pn);
		pn.setBackground(new Color(200,250,190));
		
		lbtitre1=new JLabel("Bulletin de notes");
		lbtitre1.setBounds(320,10,800,30);
		lbtitre1.setFont(new Font("Arial",Font.BOLD,18));
		lbtitre1.setForeground(new Color(50,100,50));
		pn.add(lbtitre1);
		
		//numéro eleve
		lbnum_eleve=new JLabel("Numéro élève");
		lbnum_eleve.setBounds(20,40,100,25);
		lbnum_eleve.setFont(new Font("Arial",Font.BOLD,14));
		lbnum_eleve.setForeground(new Color(50,100,50));
		pn.add(lbnum_eleve);
		
		tfnum_eleve=new JTextField();
		tfnum_eleve.setBounds(130,40,70,25);
		pn.add(tfnum_eleve);
		//trimestre
		lbtrim=new JLabel("Trimestre");
		lbtrim.setBounds(20,70,100,25);
		lbtrim.setFont(new Font("Arial",Font.BOLD,14));
		lbtrim.setForeground(new Color(50,100,50));
		pn.add(lbtrim);
		
		combotrim=new JComboBox();
		combotrim.addItem("1");
		combotrim.addItem("2");
		combotrim.addItem("3");
		combotrim.setBounds(130,70,70,25);
		pn.add(combotrim);
		//bilan lettre
		lblettre=new JLabel("Bilan lettre");
		lblettre.setBounds(10,320,180,25);
		lblettre.setFont(new Font("Arial",Font.BOLD,14));
		lblettre.setForeground(new Color(50,100,50));
		pn.add(lblettre);
		//bilan science
		lbscience=new JLabel("Bilan science");
		lbscience.setBounds(10,350,180,25);
		lbscience.setFont(new Font("Arial",Font.BOLD,14));
		lbscience.setForeground(new Color(50,100,50));
		pn.add(lbscience);
		//moyenne générale
		lbmoyg=new JLabel("Moyenne générale");
		lbmoyg.setBounds(10,380,180,25);
		lbmoyg.setFont(new Font("Arial",Font.BOLD,14));
		lbmoyg.setForeground(new Color(50,100,50));
		pn.add(lbmoyg);
		//rang
				lbrang=new JLabel("Rang");
				lbrang.setBounds(10,410,180,25);
				lbrang.setFont(new Font("Arial",Font.BOLD,14));
				lbrang.setForeground(new Color(50,100,50));
				pn.add(lbrang);
		
		//bouton pour chercher et afficher le bulletin d'un eleve
		brech=new JButton("CHERCHER");
		brech.setBounds(50,105,130,25);
		brech.setFont(new Font("Arial",Font.BOLD,14));
		brech.setForeground(Color.white);
		brech.setBackground(new Color(100,200,100));
		brech.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String num=tfnum_eleve.getText(),
						trim=combotrim.getSelectedItem().toString();
				String rq="select * from v_notes where num_elv='"+num+"' and trimestre='"+trim+"'";
				try{
					st=con.laConnection().createStatement();
					rst=st.executeQuery(rq);
					if(rst.next()){
						lbnom2.setText(rst.getString("nom"));
						lbsexe2.setText(rst.getString("sexe"));
						lbclasse2.setText(rst.getString("classe"));
					}
					
				}
				catch(SQLException ex){
			    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
			    }
				//affichage des notes
				DefaultTableModel df2=new  DefaultTableModel();
				  init2();
				  pn.add(scroll2);
				 df2.addColumn("Matière");
				 df2.addColumn("Moyenne devoir");
				 df2.addColumn("Note compo");
				 df2.addColumn("Moyenne des deux");
				 df2.addColumn("Coefficient");
				 df2.addColumn("Moyenne coeff");
				 df2.addColumn("Appréciation");
				 table2.setModel(df2);
	 String req2="select matiere,moyds,compo,moydeux,coeff,moycoeff,case when moydeux>=18 then 'EXCELLENT' "
					 		+ "when moydeux>=16 then 'TRES-BIEN' when moydeux>=14 then 'BIEN' "
					 		+ "when moydeux>=12 then 'ASSEZ-BIEN' when moydeux>=10 then 'PASSABLE' else 'INSUFFISANT'"
					 		+ "end as appreciation from v2_notes  "
					 		+ " WHERE trimestre='"+trim+"' and num_elv='"+num+"' order by matiere";

	 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(req2);
					 while(rst.next()){
						 df2.addRow(new Object[]{
		rst.getString("matiere"),rst.getString("moyds"),rst.getString("compo"),rst.getString("moydeux"),
		rst.getString("coeff"),rst.getString("moycoeff"),rst.getString("appreciation")
		
		

								 });
						 
					 } 
					 }
					 
				 catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
				    }
	 //BILAN LETTRE BILAN SCIENCE MOYENNE GENERALE ET RANG
	 //BILAN LETTRE
	 String rq1="select round(sum(moycoeff)/sum(coeff),2) as bilanlettre from v2_notes where num_elv='"+num+"' and trimestre='"+trim+"'"
	 		+ " and (matiere='HG' or matiere='IC' or matiere='Anglais' or matiere='Art' or matiere='Français')";
		try{
			st=con.laConnection().createStatement();
			rst=st.executeQuery(rq1);
			if(rst.next()){
				lblettre.setText("Bilan lettre : "+rst.getString("bilanlettre"));
			}
			
		}
		catch(SQLException ex){
	    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
	    }
	 
				////
		 //BILAN SCIENCE
		 String rq2="select round(sum(moycoeff)/sum(coeff),2) as bilanscience from v2_notes where num_elv='"+num+"' and trimestre='"+trim+"'"
		 		+ " and (matiere='SVT' or matiere='MATH' or matiere='PC')";
			try{
				st=con.laConnection().createStatement();
				rst=st.executeQuery(rq2);
				if(rst.next()){
					lbscience.setText("Bilan science : "+rst.getString("bilanscience"));
				}
				
			}
			catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
		    }
		 
					////
			 //MOYENNE GENERALE
			 String rq3="select round(sum(moycoeff)/sum(coeff),2) as moyg from v2_notes where num_elv='"+num+"' and trimestre='"+trim+"'";
			 		
				try{
					st=con.laConnection().createStatement();
					rst=st.executeQuery(rq3);
					if(rst.next()){
						lbmoyg.setText("Moyenne générale : "+rst.getString("moyg"));
					}
					
				}
				catch(SQLException ex){
			    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
			    }
			 
						////
				 //RANG
				 ///////
				 String classe="";
				 String rqc="select classe from v_notes where num_elv='"+num+"' and trimestre='"+trim+"'";
				 try{
						st=con.laConnection().createStatement();
						rst=st.executeQuery(rqc);
						if(rst.next()){
							classe=rst.getString("classe");
						}
						
					}
					catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
				    }
				 ///
				 String rq4="drop table tb_rang;",
						 rq5="create table tb_rang("
						 + "rang smallint auto_increment primary key,"
						 + "classe varchar(20),"
						 + "num_elv int,"
						 + "moyenne decimal(4,2),"
						 + "trimestre smallint)"
						 + "engine=innodb;",
						 rq6="insert into tb_rang(classe,num_elv,moyenne,trimestre) "
						 		+ "select classe,num_elv,moyg,trimestre from v_rang "
						 		+ "where classe='"+classe+"'order by moyg desc",
						rq7="select count(rang) as nbre from tb_rang";
				 try{
						st=con.laConnection().createStatement();
						st.executeUpdate(rq4);
						st.executeUpdate(rq5);
						st.executeUpdate(rq6);
						
					}
					catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
				    }
				 ///////
				 String nbre="";
				 try{
						st=con.laConnection().createStatement();
						rst=st.executeQuery(rq7);
						if(rst.next()){
							nbre=rst.getString("nbre");
						}
						
					}
					catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
				    }
				
				 ///////
			String	 rq8="select rang from tb_rang where num_elv='"+num+"' and trimestre='"+trim+"'";
					try{
						st=con.laConnection().createStatement();
						rst=st.executeQuery(rq8);
						if(rst.next()){
							lbrang.setText("Rang : "+rst.getString("rang")+" sur "+nbre);
						}
						
					}
					catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
				    }
				 
							////
			}
		});
		pn.add(brech);
		//bouton pour actualiser la page
		btactu=new JButton("ACTUALISER");
		btactu.setBounds(550,10,180,25);
		btactu.setFont(new Font("Arial",Font.BOLD,14));
		btactu.setForeground(Color.white);
		btactu.setBackground(new Color(100,200,100));
		btactu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				dispose();
				Bulletin bt=new Bulletin();
				bt.setVisible(true);
				
			}
			
		});
		pn.add(btactu);
		//nom
		lbnom=new JLabel("Nom et Prénom");
		lbnom.setBounds(250,50,150,25);
		lbnom.setFont(new Font("Arial",Font.BOLD,14));
		lbnom.setForeground(new Color(50,100,50));
		pn.add(lbnom);
		
		lbnom2=new JLabel("");
		lbnom2.setBounds(400,50,150,25);
		lbnom2.setFont(new Font("Arial",Font.BOLD,14));
		//lbnom2.setForeground(new Color(100,150,100));
		pn.add(lbnom2);
		//sexe
		lbsexe=new JLabel("Sexe");
		lbsexe.setBounds(250,80,150,25);
		lbsexe.setFont(new Font("Arial",Font.BOLD,14));
		lbsexe.setForeground(new Color(50,100,50));
		pn.add(lbsexe);
		
		lbsexe2=new JLabel("");
		lbsexe2.setBounds(400,80,150,25);
		lbsexe2.setFont(new Font("Arial",Font.BOLD,14));
		//lbsexe2.setForeground(new Color(100,150,100));
		pn.add(lbsexe2);
		//classe
		lbclasse=new JLabel("Classe");
		lbclasse.setBounds(250,110,150,25);
		lbclasse.setFont(new Font("Arial",Font.BOLD,14));
		lbclasse.setForeground(new Color(50,100,50));
		pn.add(lbclasse);
		
		lbclasse2=new JLabel("");
		lbclasse2.setBounds(400,110,150,25);
		lbclasse2.setFont(new Font("Arial",Font.BOLD,14));
		//lbclasse2.setForeground(new Color(100,150,100));
		pn.add(lbclasse2);
	}
	private void init2(){
		table2=new JTable();
		scroll2=new JScrollPane();
		scroll2.setBounds(10,150,775,160);
		scroll2.setViewportView(table2);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Bulletin bt=new Bulletin();
       bt.setVisible(true);
	}

}
