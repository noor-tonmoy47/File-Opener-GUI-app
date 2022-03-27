/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsoleInputTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



/**
 *
 * @author voldemort
 */
public class ConsoleInput {
    public static void main(String args[]){
        BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
        
        String s = null;
        int x = 0;
        try{
            s = reader.readLine();
            x = Integer.parseInt(reader.readLine());
            
        } catch(IOException e){
            System.out.println(e);
        }
        
        System.out.println("You inputed " + s);
        System.out.println(x + 2);
    }
}
