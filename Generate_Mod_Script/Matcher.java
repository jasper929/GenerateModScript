import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;  
import java.awt.event.*;
 
 
public class Matcher {
    static JTextField tf1,tf2; 
    public static String filepath=null;
    public static int filecount=0;
    static Button b2start;
    public static JFrame f;
    public static File[] files;
    public static String folderpath;
    public static File folder;
      static{
        folderpath ="C:\\Users\\Pogi\\Documents\\generated";
        

          folder =new File(folderpath);
        if(!folder.exists()){
            folder.mkdirs();
            files = folder.listFiles();
            filecount = files.length +1;
         }else{
            files = folder.listFiles();
            filecount = files.length +1;
         }
    }
    public static void main(String args[]) 
    {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e)
        {

        }
        Scanner scanner = new Scanner(System.in);

         
         

        //UI

          f= new JFrame(); 
          
          f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
             }
         }
        );


        JLabel  l1=new JLabel("Enter desire mod?");  
        l1.setBounds(50,30, 150,20); 

        JLabel  l2=new JLabel("What is the next item after your desire mod?");  
        l2.setBounds(50,80, 300,20);

        JLabel  l3=new JLabel();  
        l3.setBounds(50,150, 300,50);

        tf1=new JTextField();  
        tf1.setBounds(50,50,150,20);  

        tf2=new JTextField();
        tf2.setBounds(50,100,150,20);  
       
        

        Button b1=new Button("choose items_game.txt file");  

        b1.setBounds(50,125,140,30);  
        b1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    
                    JFileChooser fc=new JFileChooser();   
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                    fc.setFileFilter(filter); 
                    int i=fc.showOpenDialog(fc);    
                    if(i==JFileChooser.APPROVE_OPTION){    
                        File f=fc.getSelectedFile();    
                          filepath=f.getPath();  
                          l3.setText(filepath);
                          
                    }
                
            }  
            });    

            b2start=new Button("start");  
               
            b2start.setBounds(50,200,80,30);  
            b2start.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    String mod1=tf1.getText();  
                    String mod2=tf2.getText();             
                    if(tf1.getText().equals("")||tf2.getText().equals("")||filepath==null)
                    {
                        JOptionPane.showMessageDialog(f,"NULL");  
                    }else
                    {
                        try {
                            go(mod1, mod2,filecount,filepath);
                            tf1.setText("");
                            tf2.setText("");
                            //b2start.setEnabled(false);
    
                        } catch (IOException p) {
                            // TODO Auto-generated catch block
                           p.printStackTrace();
                       }  
                    }
                }  
                }); 
            
          

        f.add(tf1);f.add(tf2);f.add(b1);f.add(b2start);f.add(l1);f.add(l2);f.add(l3);
        f.setSize(300,300);  
        f.setLayout(null);  
        f.setLocationRelativeTo(null);
        f.setVisible(true);  

        //UI




    }
    




    public static void go(String mod1, String mod2, int filecount1, String filepath)throws IOException 
    {
        FileReader reader = new FileReader(filepath);
        
        BufferedReader file = new BufferedReader(reader);
  
        String line;
        String oo;
        while ((line = file.readLine()) != null)
        {
          oo = line;

         if(oo.lastIndexOf(mod2) >= oo.indexOf(mod1)){
          if(oo.indexOf(mod1)>0 && oo.lastIndexOf(mod2) >0)
          {
            System.out.println(oo.indexOf(mod1));
          System.out.println(oo.lastIndexOf(mod2));
          String result = getStringBetweenTwoCharacters(oo,mod1,mod2);
          
          String add = "{    \"name\"   \"";
         String textitem1 = add+result;
          
        try {
            go2(mod1, mod2, filecount1, textitem1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
          }else{
            JOptionPane.showMessageDialog(f,"No word");  
        }
        }else{
            JOptionPane.showMessageDialog(f,"No word");  
        }

        }

       
        reader.close();
    }

    public static void go2(String mod11, String mod21, int filecount2, String textitem1)throws IOException 
    {
        FileReader reader = new FileReader("orig items.txt");
        FileWriter writer = new FileWriter(folderpath+"/script "+ filecount2 +".txt", true);
        BufferedReader file = new BufferedReader(reader);
  
        String line;
        String oo;
        


        while ((line = file.readLine()) != null)
        {
          oo = line;
          String result = getStringBetweenTwoCharacters(oo,mod11,mod21);
          
          String add = "{    \"name\"   \"";
        
          
         writer.write( "\"item\" "+
         '\n'+"{"+
         '\n'+"	\"from\""+
         '\n'+"		"+add+result+
         '\n'+"	\"to\""+
         '\n'+"		"+textitem1+
         '\n'+"}");
         JOptionPane.showMessageDialog(f,"Success generated files on "+ folderpath );
         files = folder.listFiles();
         filecount = files.length +1;
           
        }
       
        writer.close();
        reader.close();
    }


    public static String getStringBetweenTwoCharacters(String input, String to, String from)
    {
        return input.substring(input.indexOf(to), input.lastIndexOf(from)-22);
    }

    public static void countsc()
    {
        for(File filess: files)
        {
           filecount=filecount+1;
        }
           filecount=filecount+1;
    }
}
