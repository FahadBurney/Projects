/*   
 * This is A PhotoAlbum Project .
 *  images of right button , left button, home button, the photoimage
 *  and startbutton image used in this Project are  also uploaded along with the program
 *  
 * Also , along with the path of image it also fetches , name of the image, its size and the date when 
 * it was last modified
 * */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

public class PhotoAlbum extends JFrame implements ActionListener 
{
JLabel rootpath,path,name,size,lastmodified,imagel;
JTextField rootpathf,pathf,namef,sizef,lastmodifiedf;
JButton start,home,left,right;		
public int startindex=0;
public boolean controlinstart=false;
public File[] f3=null;
private void createUI()
{
	super.setBounds(200, 10, 1060, 720);
	super.setResizable(false);
	super.setTitle("                                         PHOTOGALLERY                    ");
	Color color = Color.decode("#75ff33");
	super.getContentPane().setBackground(color);
	
	Font font = new Font("Calibri", Font.ITALIC, 25);
	String pth="/home/fahad/Pictures/startbutton.png";
	ImageIcon ic=new ImageIcon(pth);
	start=new JButton(ic);
	start.setBounds(780, 540, 110, 130);
	super.add(start);
	
	pth="/home/fahad/Pictures/homebutton.jpg";
	ic=new ImageIcon(pth);
	home=new JButton(ic);
	home.setBounds(270, 540, 100, 130);
	super.add(home);
	
	pth="/home/fahad/Pictures/leftarrow.png";
	ic=new ImageIcon(pth);
	left=new JButton(ic);
left.setBounds(100, 540, 100, 130);
	super.add(left);
	
	pth="/home/fahad/Pictures/rightarrow.png";
	ic=new ImageIcon(pth);
	right=new JButton(ic);
    right.setBounds(440, 540, 100, 130);
	super.add(right);
	
	pth="/home/fahad/Pictures/image.jpeg";
	ic=new ImageIcon(pth);
	imagel=new JLabel(ic);
    imagel.setBounds(90, 120, 450, 300);
    imagel.setIcon(new ImageIcon(pth));
    super.add(imagel);
	
	rootpath=new JLabel("ROOTPATH");
	path=new JLabel("PATH");
	name=new JLabel("NAME");
	size=new JLabel("SIZE");
	lastmodified=new JLabel("LAST MODIFIED");
	
	rootpathf=new JTextField("ENTER PATH HERE");
	pathf=new JTextField("");
	namef=new JTextField("");
	sizef=new JTextField("");
	lastmodifiedf=new JTextField("");
	
	
	rootpath.setBounds(300,20,170,50);
	rootpath.setFont(font);
	super.add(rootpath);
	
	path.setBounds(700, 80, 100, 50);
	path.setFont(font);
	super.add(path);
	
	name.setBounds(700, 190, 100, 50);
	name.setFont(font);
	super.add(name);
	
	size.setBounds(710, 300, 100, 50);
	size.setFont(font);
	super.add(size);
	
	lastmodified.setBounds(600, 410, 200, 50);
	lastmodified.setFont(font);
	super.add(lastmodified);
	
	rootpathf.setBounds(450, 30, 580, 30);
	super.add(rootpathf);
	pathf.setBounds(800, 90, 230, 30);
	super.add(pathf);
	
	namef.setBounds(800, 200, 230, 30);
	super.add(namef);

	sizef.setBounds(800,310,230,30);
	super.add(sizef);
	
	lastmodifiedf.setBounds(800,420,230,30);
	super.add(lastmodifiedf);

	home.addActionListener(this);	
	start.addActionListener(this);	
	left.addActionListener(this);	
	right.addActionListener(this);	
	super.setLayout(null);
	super.setVisible(true);
	super.setDefaultCloseOperation(3);
}
public void actionPerformed(ActionEvent ae)
{ 
if(ae.getSource()==start)
{
String paTh=rootpathf.getText();
File f=new File(paTh);
if(!f.exists()||f.isFile())
{
rootpathf.setText("ENTER CORRECT PATH OF DIRECTORY");
pathf.setText("");
lastmodifiedf.setText("");
namef.setText("");
sizef.setText("");
}
if(f.isDirectory())
{
	controlinstart=true;
	int i;
	File[] f1=f.listFiles();
	ArrayList<File> al=new ArrayList<File>();
	for(i=0;i<f1.length;i++)
	{
		if(f1[i].getAbsolutePath().endsWith(".png")||f1[i].getAbsolutePath().endsWith(".jpg")||f1[i].getAbsolutePath().endsWith(".jpeg")||f1[i].getAbsolutePath().endsWith(".gif")||f1[i].getAbsolutePath().endsWith(".bmp"))
		{
			al.add(f1[i]);
		}
	}
   f3 = new File[al.size()]; 
   f3= al.toArray(f3);
  pathf.setText(f3[startindex].getAbsolutePath());
  namef.setText(f3[startindex].getName());
  sizef.setText(f3[startindex].length()+"");

  long lastmodified=f3[startindex].lastModified();
  Date date=new Date(lastmodified);
  lastmodifiedf.setText(date+"");
  String pathofcurrentfile= f3[startindex].getAbsolutePath();
	imagel.setIcon(new ImageIcon(pathofcurrentfile));
  imagel.setBounds(90, 120, 450, 300);
  super.add(imagel);
}}
if(ae.getSource()==home)
{
if(controlinstart==true)
{
left.setEnabled(true);
right.setEnabled(true);
startindex=0;
pathf.setText(f3[startindex].getAbsolutePath());
namef.setText(f3[startindex].getName());
sizef.setText(f3[startindex].length()/1000+" KiloBytes");
long lastmodified=f3[startindex].lastModified();
Date date=new Date(lastmodified);
lastmodifiedf.setText(date+"");
String pathofcurrentfile= f3[startindex].getAbsolutePath();
imagel.setIcon(new ImageIcon(pathofcurrentfile));
imagel.setBounds(90, 120, 450, 300);
super.add(imagel);
}
else
{
pathf.setText("PRESS START BUTTON FIRST");
namef.setText("");
sizef.setText("");
lastmodifiedf.setText("");
}
}
if(ae.getSource()==right)
{
	if(controlinstart==true)
	{
	startindex++;
	if(startindex>(f3.length-1))
	{
		right.setEnabled(false);
     }
	else 
	{
		right.setEnabled(true);
        left.setEnabled(true);
	pathf.setText(f3[startindex].getAbsolutePath());
	namef.setText(f3[startindex].getName());
	sizef.setText(f3[startindex].length()/1000+" KiloBytes");
	  long lastmodified=f3[startindex].lastModified();
	  Date date=new Date(lastmodified);
	  lastmodifiedf.setText(date+"");	
	String pathofcurrentfile= f3[startindex].getAbsolutePath();
	imagel.setIcon(new ImageIcon(pathofcurrentfile));
	imagel.setBounds(90, 120, 450, 300);
	super.add(imagel);
	}}
	else
	{
		pathf.setText("PRESS START BUTTON FIRST");
		namef.setText("");
		sizef.setText("");
		lastmodifiedf.setText("");
	}
}
if(ae.getSource()==left)
{
	if(controlinstart==true)
	{
	startindex--;
	if(startindex<0)
	{
	left.setEnabled(false);
	}
	else 
	{
	left.setEnabled(true);
     right.setEnabled(true);
	pathf.setText(f3[startindex].getAbsolutePath());
	namef.setText(f3[startindex].getName());
	sizef.setText(f3[startindex].length()/1000+" KiloBytes ");

	  long lastmodified=f3[startindex].lastModified();
	  Date date=new Date(lastmodified);
	  lastmodifiedf.setText(date+"");	String pathofcurrentfile= f3[startindex].getAbsolutePath();
	imagel.setIcon(new ImageIcon(pathofcurrentfile));
    imagel.setBounds(90, 120, 450, 300);
	super.add(imagel);
}}
	else
	{
		pathf.setText("PRESS START BUTTON FIRST");	
		namef.setText("");
		sizef.setText("");
		lastmodifiedf.setText("");
	}
}
}
	

public static void main(String[] args) {
	PhotoAlbum  pc=new PhotoAlbum();
	pc.createUI();
}
}