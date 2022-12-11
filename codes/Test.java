import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;  
import java.awt.event.*;  

public class Test { 
    static JTextField tf1,tf2;  
   
    public static void main(String args[]) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e)
        {

        }




        JFrame f= new JFrame(); 

        tf1=new JTextField();  
        tf1.setBounds(50,50,150,20);  

        tf2=new JTextField();
        tf2.setBounds(50,100,150,20);  
       
        

        Button b1=new Button("choose items_game.txt file");  

        b1.setBounds(50,150,140,30);  
        b1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    
                    JFileChooser fc=new JFileChooser();   
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                    fc.setFileFilter(filter); 
                    int i=fc.showOpenDialog(fc);    
                    if(i==JFileChooser.APPROVE_OPTION){    
                        File f=fc.getSelectedFile();    
                        String filepath=f.getPath();  
                        System.out.println(filepath);
                    }
                
            }  
            });    

            Button b2start=new Button("start");  

            b2start.setBounds(50,200,80,30);  
            b2start.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        
                }  
                }); 
            
          

        f.add(tf1);f.add(tf2);f.add(b1);f.add(b2start);
        f.setSize(300,300);  
        f.setLayout(null);  
        f.setVisible(true);  
    }

}