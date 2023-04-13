package bulletin_java;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
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
public class Notes extends JFrame {
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JTable table,table2;
	JScrollPane scroll,scroll2;
	JLabel lbtitre1,lbnum_eleve,lbmatiere,lbdevoir1,lbdevoir2,lbdevoir3,lbcompo,lbtrimestre;
	JTextField tfnum_eleve,tfdevoir1,tfdevoir2,tfcompo,tfid,tftrimestre;
	JButton bteleve,btmatiere,btbulletin,btenrg,btmodif,btsupp;
	JComboBox combomatiere;
	public Notes(){
		this.setTitle("chcode_appli");
		this.setSize(1000,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		add(pn);
		pn.setBackground(new Color(200,250,190));
		
		lbtitre1=new JLabel("Formulaire d'enregistrement des notes");
		lbtitre1.setBounds(50,50,800,30);
		lbtitre1.setFont(new Font("Arial",Font.BOLD,18));
		lbtitre1.setForeground(new Color(100,150,100));
		pn.add(lbtitre1);
		
		bteleve=new JButton("Enregistrement des �l�ves");
		bteleve.setBounds(30,10,300,25);
		bteleve.setFont(new Font("Arial",Font.BOLD,14));
		bteleve.setForeground(Color.white);
		bteleve.setBackground(new Color(100,200,100));
		bteleve.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				Eleve elv=new Eleve();
				elv.setVisible(true);
			}
		});
		pn.add(bteleve);
		//bouton pour enregistrer les matieres
		btmatiere=new JButton("Enregistrement des mati�res");
		btmatiere.setBounds(400,10,300,25);
		btmatiere.setFont(new Font("Arial",Font.BOLD,14));
		btmatiere.setForeground(Color.white);
		btmatiere.setBackground(new Color(100,200,100));
		btmatiere.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				Matiere mtr=new Matiere();
				mtr.setVisible(true);
			}
		});
		pn.add(btmatiere);
		//bouton pour afficher les bulletins
		btbulletin=new JButton("Bulletins de notes");
		btbulletin.setBounds(750,10,180,25);
		btbulletin.setFont(new Font("Arial",Font.BOLD,14));
		btbulletin.setForeground(Color.white);
		btbulletin.setBackground(new Color(100,200,100));
		btbulletin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				Bulletin bt=new Bulletin();
				bt.setVisible(true);
				
			}
			
		});
		pn.add(btbulletin);
		
		//matiere
				lbmatiere=new JLabel("Mati�re");
				lbmatiere.setBounds(60,90,100,25);
				lbmatiere.setFont(new Font("Arial",Font.BOLD,14));
				lbmatiere.setForeground(new Color(100,150,100));
				pn.add(lbmatiere);
				
				combomatiere=new JComboBox();
				combomatiere.setBounds(190,90,150,25);
				combomatiere.addItem("");
				pn.add(combomatiere);
				//remplissage du combomatiere
				String rq="select nom_mat from tb_matiere";
				try{
					st=con.laConnection().createStatement();
					rst=st.executeQuery(rq);
					while(rst.next()){
					combomatiere.addItem(rst.getString("nom_mat"));
					}
				}
				catch(SQLException ex){
			    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
			    }
		//num�ro eleve
		lbnum_eleve=new JLabel("Num�ro �l�ve");
		lbnum_eleve.setBounds(60,120,100,25);
		lbnum_eleve.setFont(new Font("Arial",Font.BOLD,14));
		lbnum_eleve.setForeground(new Color(100,150,100));
		pn.add(lbnum_eleve);
		
		tfnum_eleve=new JTextField();
		tfnum_eleve.setBounds(190,120,150,25);
		pn.add(tfnum_eleve);
		//devoir1
		lbdevoir1=new JLabel("D�voir 1");
		lbdevoir1.setBounds(60,150,100,25);
		lbdevoir1.setFont(new Font("Arial",Font.BOLD,14));
		lbdevoir1.setForeground(new Color(100,150,100));
		pn.add(lbdevoir1);
		
		tfdevoir1=new JTextField();
		tfdevoir1.setBounds(190,150,150,25);
		pn.add(tfdevoir1);
		//devoir2
				lbdevoir2=new JLabel("D�voir 2");
				lbdevoir2.setBounds(60,180,100,25);
				lbdevoir2.setFont(new Font("Arial",Font.BOLD,14));
				lbdevoir2.setForeground(new Color(100,150,100));
				pn.add(lbdevoir2);
				
				tfdevoir2=new JTextField();
				tfdevoir2.setBounds(190,180,150,25);
				pn.add(tfdevoir2);
		//composition
				lbcompo=new JLabel("Composition");
				lbcompo.setBounds(60,210,100,25);
				lbcompo.setFont(new Font("Arial",Font.BOLD,14));
				lbcompo.setForeground(new Color(100,150,100));
				pn.add(lbcompo);
				
				tfcompo=new JTextField();
				tfcompo.setBounds(190,210,150,25);
				pn.add(tfcompo);
		//trimestre
				lbtrimestre=new JLabel("Trimestre");
				lbtrimestre.setBounds(60,240,100,25);
				lbtrimestre.setFont(new Font("Arial",Font.BOLD,14));
				lbtrimestre.setForeground(new Color(100,150,100));
				pn.add(lbtrimestre);
				
				tftrimestre=new JTextField();
				tftrimestre.setBounds(190,240,150,25);
				pn.add(tftrimestre);
				
				
				//bouton pour enregistrer les notes
				btenrg=new JButton("ENREGISTRER");
				btenrg.setBounds(20,290,150,25);
				btenrg.setFont(new Font("Arial",Font.BOLD,14));
				btenrg.setForeground(Color.white);
				btenrg.setBackground(new Color(100,200,100));
				btenrg.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String mat,num,dev1,dev2,compo,trim;
						mat=combomatiere.getSelectedItem().toString();
						num=tfnum_eleve.getText();
						dev1=tfdevoir1.getText();
						dev2=tfdevoir2.getText();
						compo=tfcompo.getText();
						trim=tftrimestre.getText();
					String rq="insert into tb_notes(num_elv,matiere,devoir1,devoir2,compo,trimestre) values('"+num+"','"+mat+"','"+dev1+"','"+dev2+"','"+compo+"','"+trim+"')";
						try{
							st=con.laConnection().createStatement();
							st.executeUpdate(rq);
							JOptionPane.showMessageDialog(null,"Enregistrement �ffectu� avec succ�s !",null,JOptionPane.INFORMATION_MESSAGE);
						}
						catch(SQLException ex){
					    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
					    }
						dispose();
						Notes nt=new Notes();
						nt.setVisible(true);
						
					}
				});
				pn.add(btenrg);
				
				//bouton pour modifier les notes
				btmodif=new JButton("MODIFIER");
				btmodif.setBounds(200,290,150,25);
				btmodif.setFont(new Font("Arial",Font.BOLD,14));
				btmodif.setForeground(Color.white);
				btmodif.setBackground(new Color(100,200,100));
				btmodif.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String mat,num,dev1,dev2,compo,trim,id;
						mat=combomatiere.getSelectedItem().toString();
						num=tfnum_eleve.getText();
						dev1=tfdevoir1.getText();
						dev2=tfdevoir2.getText();
						compo=tfcompo.getText();
						trim=tftrimestre.getText();
						id=tfid.getText();
						String rq1="update tb_notes set matiere='"+mat+"' where id='"+id+"' ";
						String rq2="update tb_notes set num_elv='"+num+"' where id='"+id+"' ";
						String rq3="update tb_notes set devoir1='"+dev1+"' where id='"+id+"' ";
						String rq4="update tb_notes set devoir2='"+dev2+"' where id='"+id+"' ";
						String rq5="update tb_notes set compo='"+compo+"' where id='"+id+"' ";
						String rq6="update tb_notes set trimestre='"+trim+"' where id='"+id+"' ";
						try{
							st=con.laConnection().createStatement();
							//update matiere
							if(!mat.equals("")){
							st.executeUpdate(rq1);
							}
							//update num�ro eleve
							if(!num.equals("")){
							st.executeUpdate(rq2);
							}
							//update devoir1
							if(!dev1.equals("")){
							st.executeUpdate(rq3);
							}
							//update devoir2
							if(!dev2.equals("")){
							st.executeUpdate(rq4);
							}
							//update compo
							if(!compo.equals("")){
							st.executeUpdate(rq5);
							}
							//update trimestre
							if(!trim.equals("")){
							st.executeUpdate(rq6);
							}
							JOptionPane.showMessageDialog(null,"Modification �ffectu�e avec succ�s !",null,JOptionPane.INFORMATION_MESSAGE);
						}
						catch(SQLException ex){
					    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
					    }
						dispose();
						Notes nt=new Notes();
						nt.setVisible(true);
						
					}
				});
				pn.add(btmodif);
				
				//bouton pour supprimer les notes
				btsupp=new JButton("SUPPRIMER");
				btsupp.setBounds(380,290,150,25);
				btsupp.setFont(new Font("Arial",Font.BOLD,14));
				btsupp.setForeground(Color.white);
				btsupp.setBackground(new Color(100,200,100));
				btsupp.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String id=tfid.getText();
						String rq="delete from tb_notes where id='"+id+"'";
						try{
							st=con.laConnection().createStatement();
							st.executeUpdate(rq);
							JOptionPane.showMessageDialog(null,"Suppr�ssion �ffectu�e avec succ�s !",null,JOptionPane.INFORMATION_MESSAGE);
						}
						catch(SQLException ex){
					    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
					    }
						dispose();
						Notes nt=new Notes();
						nt.setVisible(true);
						
					}
				});
				pn.add(btsupp);
				
				tfid=new JTextField("ID");
				tfid.setBounds(532,290,50,25);
				pn.add(tfid);
				//liste des notes
				DefaultTableModel df2=new  DefaultTableModel();
				  init2();
				  pn.add(scroll2);
				 df2.addColumn("Mati�re");
				 df2.addColumn("Num�ro �l�ve");
				 df2.addColumn("Nom et Pr�nom");
				 df2.addColumn("D�voir1");
				 df2.addColumn("D�voir2");
				 df2.addColumn("Composition");
				 df2.addColumn("Trimestre");
				 df2.addColumn("ID");
				
				 table2.setModel(df2);
				// String req2="select * from tb_notes";
	 String req2="select matiere,num_elv,nom,devoir1,devoir2,compo,trimestre,id from tb_eleve inner join tb_notes on tb_eleve.num_eleve=tb_notes.num_elv order by id desc";
				 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(req2);
					 while(rst.next()){
						 df2.addRow(new Object[]{
		rst.getString("matiere"),rst.getString("num_elv"),rst.getString("nom"),rst.getString("devoir1"),rst.getString("devoir2")
		,rst.getString("compo"),rst.getString("trimestre"),rst.getString("id")

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
		scroll2.setBounds(10,330,970,230);
		scroll2.setViewportView(table2);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Notes nt=new Notes();
		nt.setVisible(true);

	}

}
