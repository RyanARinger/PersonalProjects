/* 
 * Author: Ryan Ringer
 * Created on: July 2nd, 2019 at 2:28am
 * File: ConnectFour.java
 * Purpose: Implementing a four in a row game
 */

package connectfour;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ConnectFour {

    public static void main(String[] args) {
        // Seed random number or initialize scanner


        // Constant Variables
        final int WSIZE = 800;
        final int HSIZE = 600;
        
        final int NUMLINES = 4;
        final int NUMTILES = NUMLINES * NUMLINES;
        final int YWIN = (NUMLINES*1);
        final int RWIN = (NUMLINES*10);
        
        // Declare all Variables Here
        JFrame window;
        JPanel board;
        JPanel panel;
        SquareButton[] tiles;
        GridBagConstraints g = new GridBagConstraints();
        boolean done = false;
        boolean p1 = false;
        int redTiles = 0;
        int yellowTiles = 0;
        int rt, yt, c;
        Color p1C, p2C;
        
        
        // Input or initialize values Here
        window = new JFrame("Four in a Row");
        window.setSize(WSIZE, HSIZE);
        board = new JPanel(new GridBagLayout());
        tiles = new SquareButton[NUMTILES];
        g.insets = new Insets(5,5,5,5);
        
        board.setBackground(Color.DARK_GRAY);
        
        for (int i = 0; i < NUMTILES; i++) {
            tiles[i] = new SquareButton();
        }
        
        for (int i = 0; i < NUMLINES; i++) {
            for (int j = 0; j < NUMLINES; j++) {
                addStuff(board, tiles[(i*NUMLINES) + j], g, i, j);
            }
        }
        
        p1C = tiles[0].getAc1();
        p2C = tiles[0].getAc2();
        
        window.add(board);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Process/Calculations Here
        
        while(!done){
            rt = 0;
            yt = 0;
            c = 0;
            for (int i = 0; i < NUMLINES; i++) {
                for (int j = 0; j < NUMLINES; j++) {
                    c += tiles[(i*NUMLINES)+j].getValue();
                    
                }
                if(c == YWIN){
                    System.out.println("Yellow Wins");
                    done = true;
                }
                else if(c == RWIN){
                    System.out.println("Red Wins");
                    done = true;
                }
                c = 0;
            }
            c = 0;
            for (int i = 0; i < NUMLINES; i++) {
                for (int j = 0; j < NUMLINES; j++) {
                    c += tiles[(j*NUMLINES)+i].getValue();
                    
                }
                if(c == YWIN){
                    System.out.println("Yellow Wins");
                    done = true;
                }
                else if(c == RWIN){
                    System.out.println("Red Wins");
                    done = true;
                }
                c = 0;
            }
            c = 0;
            for (int i = 0; i < NUMLINES; i++) {
                int j = i;
                c += tiles[(i*NUMLINES) + j].getValue();
                
                if(c == YWIN){
                    System.out.println("Yellow Wins");
                    done = true;
                }
                else if(c == RWIN){
                    System.out.println("Red Wins");
                    done = true;
                }
            }
            c = 0;
            
            for (int i = 0; i < NUMLINES; i++) {
                c += tiles[(NUMLINES -1) * (i+1)].getValue();
                if(c == YWIN){
                    System.out.println("Yellow Wins");
                    done = true;
                }
                else if(c == RWIN){
                    System.out.println("Red Wins");
                    done = true;
                }
            }
            c = 0;
            for (int i = 0; i < NUMTILES; i++) {
                if(!tiles[i].getLocked()){
                    tiles[i].setP1(p1);
                }
                else{
                    c++;
                }
            }
            if(c == NUMTILES){
                done = true;
                System.out.println("DRAW");
            }
            
            for (int i = 0; i < NUMTILES; i++) {
                if(tiles[i].getBackground().equals(p1C)){
                    rt++;
                }
                if(rt > redTiles){
                    p1 = false;
                    redTiles = rt;
                }
            }
            
            for (int i = 0; i < NUMTILES; i++) {
                
                if(tiles[i].getBackground().equals(p2C)){
                    yt++;
                }
                if(yt > yellowTiles){
                    p1 = true;
                    yellowTiles = yt;
                }
            }
            
            
            
        }
        window.dispose();
        // Output Located Here


        // Exit
    }
    public static void addStuff(Container c, Component l, GridBagConstraints gbc, int x, int y){
        gbc.gridx = x;
        gbc.gridy = y;
        
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        c.add(l, gbc);
    }
}