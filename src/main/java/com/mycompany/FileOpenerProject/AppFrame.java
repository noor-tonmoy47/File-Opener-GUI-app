/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.FileOpenerProject;



import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author voldemort
 */
public class MyFrame extends JFrame implements ActionListener{
    
    private JButton open,save;
    private JTextField input;
    private JTextArea display;
    private final Font font;
    private File f;
    private JScrollPane sc;
    MyFrame(){
        font = new Font("Mv Boil", Font.PLAIN, 20);
        
        this.setTitle("File Opener");
        this.setSize(700, 540);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
       
        //input field initializer
        initInput();
        
        //Open Button initializer
        initOpenButton();
        
        //Display file initializer
        initFileDisplay();
        
        //Save button initializer
        initSaveButton();
        
        this.add(input);
        this.add(open);
        //this.add(display);
        this.add(sc);  
        this.add(save);
        this.setVisible(true);
    }
    
    private void initInput(){
        input = new JTextField();
        input.setBounds(20, 20, 500, 40);
        input.setFont(font);
    }
    
    private void initOpenButton(){
        open = new JButton("Open");
        open.addActionListener(this);
        open.setBounds(570, 20, 90,40);
        open.setFocusable(false);
        open.setFont(font);
    }
    
    private void initSaveButton(){
        save = new JButton("Save");
        save.setBounds(570, 440, 90, 40);
        save.addActionListener(this);
        save.setFocusable(false);
        save.setFont(font);
    }
    
    private void initFileDisplay(){
        
        
        display = new JTextArea();
        
        // Making the text area scrollable
        sc = new JScrollPane(display);
        sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        sc.setBounds(20,70, 640, 350);
        
        display.setFont(font);
        display.setEditable(false);
    }
    
    private void openFile() throws IOException, FileNotFoundException{
        display.setText("");
        String location = "/home/voldemort/Desktop/" + input.getText();
        f = new File(location);
        BufferedReader in = new BufferedReader
                            (new InputStreamReader
                              (new FileInputStream(f)));
        String temp;
        boolean flag  = true;
        while((temp = in.readLine()) != null){
            if(flag) {
                display.setText(display.getText() + temp);
                flag = false;
            }
            else{
                display.setText(display.getText() + "\n"+ temp);
            }
        }
        in.close();
        display.setEditable(true);
    }
    
    private void saveFile() throws FileNotFoundException, IOException{
        
    /* Saves the edited text in a new file
        File newFile = new File("/home/voldemort/Desktop/" + input.getText() + "1");
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
    */
        BufferedWriter out = new BufferedWriter
                             (new OutputStreamWriter
                               (new FileOutputStream(f)));
        out.write(display.getText());
        out.close();
    }
    
    public static void main(String[] args) {
        new MyFrame();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == open){
           try {
               openFile();
           } catch (FileNotFoundException ex) {
              f = null;
              System.out.println("We encountered " + ex);
              JOptionPane.showMessageDialog(this, "No such file is Found", "Warning", JOptionPane.WARNING_MESSAGE);
           } catch (IOException ex) {
              System.out.println("We encountered " + ex);
              JOptionPane.showMessageDialog(this, "Couldn't read the file", "Warning", JOptionPane.WARNING_MESSAGE);
           }
       }
       else if(e.getSource() == save){
           try {
               saveFile();
               JOptionPane.showMessageDialog(this, "Succesfully updated the file");
           } catch (FileNotFoundException ex) {
              System.out.println("We encountered " + ex);
              JOptionPane.showMessageDialog(this, "No such file is Found", "Warning", JOptionPane.WARNING_MESSAGE);
           } catch (IOException ex) {
               System.out.println("We encountered " + ex);
               JOptionPane.showMessageDialog(this, "Couldn't save the file!!", "Warning", JOptionPane.WARNING_MESSAGE);
           } catch (NullPointerException ex){
               System.out.println("We encountered " + ex);
               JOptionPane.showMessageDialog(this, "Nothing to save", "Warning", JOptionPane.WARNING_MESSAGE);
           }
       }
    }
}
