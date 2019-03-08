/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emily Anas
 */
public class AdventOfCode {
    
    Scanner s = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
    
    public void dayTwentyFive(){
        int size = s.nextInt();
        int position = size;
        int count = 0;
        int [] band = new int [size * 2];
        char state = 'A';
        for(int i = 0; i<size; i++){
            if(state=='A'){
                if(band[position]==0){
                    band[position] = 1;
                    position++;
                    state = 'B';
                }
                else if(band[position]==1){
                    band[position]=0;
                    position--;
                    state = 'B';
                }
            }
            else if(state=='B'){
                if(band[position]==0){
                    band[position]=1;
                    position--;
                    state ='C';
                }
                else if(band[position]==1){
                    band[position]=0;
                    position++;
                    state='E';
                }
            }
            else if(state=='C'){
                if(band[position]==0){
                    band[position]=1;
                    position++;
                    state ='E';
                }
                else if(band[position]==1){
                    band[position]=0;
                    position--;
                    state='D';
                }
            }
            else if(state == 'D'){
                if(band[position]==0){
                    band[position]=1;
                    position--;
                    state='A';
                }
                else if(band[position]==1){
                    position--;
                    state='A';
                }
            }
            else if(state == 'E'){
                if(band[position]==0){
                    position++;
                    state='A';
                }
                else if(band[position]==1){
                    band[position]=0;
                    position++;
                    state='F';
                }
            }
            else if(state == 'F'){
                if(band[position]==0){
                    band[position]=1;
                    position++;
                    state='E';
                }
                else if(band[position]==1){
                    position++;
                    state='A';
                }
            }
        }
        for(int i = 0; i<band.length; i++){
            if(band[i]==1)
                count++;
        }
        System.out.println(count);
    }
    
    public void dayThree(){
        int num = s.nextInt();
        int size = 1, count = 0;
        while(num>(size*size)){
            size = size+2;
            count++;
        }
        System.out.println(size);
        
        int [][] board = new int [size][size];
        int add = 1, value = 2, y = count, x = count;
        char dir = 'R';
        board[count][count] = 1;
        while(add<size){
            for(int i = 0; i<add; i++){
                x++;
                board[y][x] = value;
                value++;
                dir = 'R';
            }
            for(int i = 0; i<add; i++){
                y--;
                board[y][x] = value;
                value++;
                dir = 'U';
            }
            add++;
            for(int i = 0; i<add; i++){
                x--;
                board[y][x] = value;
                value++;
                dir = 'L';
            }
            for(int i = 0; i<add; i++){
                y++;
                board[y][x] = value;
                value++;
                dir = 'D';
            }
            add++;
        }
        add--;
        switch (dir) {
            case 'R':
                for(int i = 0; i<add; i++){
                    y--;
                    board[y][x] = value;
                    value++;
                }   break;
            case 'U':
                for(int i = 0; i<add; i++){
                    x--;
                    board[y][x] = value;
                    value++;
                }   break;
            case 'L':
                for(int i = 0; i<add; i++){
                    y++;
                    board[y][x] = value;
                    value++;
                }   break;
            default:
                for(int i = 0; i<add; i++){
                    x++;
                    board[y][x] = value;
                    value++;
                }   break;
        }
        
        int y2 = 0, x2 = 0;
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                if(board[i][j]==num){
                    y2 = i;
                    x2 = j;
                }
            }
        }
        
        int dist = Math.abs(count-y2) + Math.abs(count-x2);
        System.out.println(dist);
    }
    
    public void dayFive(){
        try {
            Scanner sc = new Scanner(new File("jump.txt"));
            ArrayList <Integer> list = new ArrayList();
            while(sc.hasNext()){
                list.add(sc.nextInt());
            }
            int position = 0, count = 0;
            while(position<list.size()){
                int oldPos = position;
                position = position + list.get(position);
                list.set(oldPos, (list.get(oldPos)+1));
                count++;
            }
            System.out.println(count);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdventOfCode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dayFour(){
        try {
            Scanner sc = new Scanner(new File("passcodes.txt"));
            int count = 0;
        while(sc.hasNext()){
            Boolean valid = true;
            String line = sc.nextLine();
            String [] words = line.split(" ");
            for(int i = 0; i<words.length; i++){
                for(int j = 0; j<words.length; j++){
                    if(words[i].equals(words[j]) && i!=j){
                        valid = false;
                    }
                }
            }
            if(valid)
                count++;
        }
        System.out.println(count);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdventOfCode.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("file not found");
        }
    }
    
    public void dayTwo(){
        int [] nums = new int [16];
        int high, low, sum = 0;
        while(s.hasNextLine()){
            for(int i = 0; i<nums.length; i++){
                nums[i] = s.nextInt();
            }
            s.nextLine();
            high = nums[0];
            low = nums[0];
            for(int j = 1; j<nums.length; j++){
                if(nums[j]>high)
                    high = nums[j];
                else if(nums[j]<low)
                    low = nums[j];
            }
            sum = sum + (high-low);
            System.out.println(sum);
        }
    }
    
    public void dayOne(){
        String num = s.nextLine();
        int [] nums = new int [num.length()];
        for(int i = 0; i<num.length(); i++){
            nums[i] = num.charAt(i)-48;
        }
        int sum = 0;
        for(int i = 0; i<num.length()-1; i++){
            if(nums[i]==nums[i+1]){
                sum = sum + nums[i];
            }
        }
        if(num.charAt(num.length()-1)==num.charAt(0))
            sum = sum + nums[0];
        System.out.println(sum);
    }
}
    
