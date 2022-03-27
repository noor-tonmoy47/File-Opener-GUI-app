/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.FileOpenerProject;



import java.awt.Color;
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
public class AppFrame extends JFrame implements ActionListener{
    
    private JButton openButton,saveButton;
    private JTextField inputText;
    private JTextArea display;
    private final Font font;
    private File f;
    private JScrollPane scroll;
    AppFrame(){
        font = new Font(Font.DIALOG, Font.PLAIN, 20);
       
        
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
        
        this.add(inputText);
        this.add(openButton);
        //this.add(display);
        this.add(scroll);  
        this.add(saveButton);
        this.setVisible(true);
    }
    
    private void initInput(){
        inputText = new JTextField();
        inputText.setBounds(200, 20, 300, 40);
        inputText.setCaretPosition(0);
        inputText.setFont(font);
        
    }
    
    private void initOpenButton(){
        openButton = new JButton("Open");
        openButton.addActionListener(this);
        openButton.setBounds(120, 440, 90,40);
        openButton.setFocusable(false);
        openButton.setFont(font);
    }
    
    private void initSaveButton(){
        saveButton = new JButton("Save");
        saveButton.setBounds(500, 440, 90, 40);
        saveButton.addActionListener(this);
        saveButton.setFocusable(false);
        saveButton.setFont(font);
    }
    
    private void initFileDisplay(){
        
        
        display = new JTextArea();
        
        // Making the text area scrollable
        scroll = new JScrollPane(display);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scroll.setBounds(20,70, 640, 350);
        
        display.setFont(font);
        //display.setForeground(Color.CYAN);
        display.setEditable(false);
    }
    
    private void openFile() throws IOException, FileNotFoundException{
        display.setText("");
        String location = "/home/voldemort/Desktop/" + inputText.getText();
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
        new AppFrame();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == openButton){
           try {
               openFile();
           } catch (FileNotFoundException ex) {
              f = null;
              System.out.println("We encountered " + ex);
              JOptionPane.showMessageDialog(this, "No such file is Found", "Warning", JOptionPane.WARNING_MESSAGE);
           } catch (IOException ex) {
              System.out.println("We encountered " + ex);
              JOptionPane.showMessageDialog(this, "Couldn't read the file", "Warning", JOptionPane.WARNING_MESSAGE);
           } catch(Exception ex){
               System.out.println("We encountered " + ex);
               JOptionPane.showMessageDialog(this, "No file Name entered", "Warning", JOptionPane.WARNING_MESSAGE);
           }
       }
       else if(e.getSource() == saveButton){
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
