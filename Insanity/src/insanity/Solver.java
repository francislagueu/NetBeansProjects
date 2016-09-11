/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insanity;


import java.io.*;
import java.util.*;

public class Solver {
    int [][]A;
    int [][]B;
    int [][]C;

    int []sol1;
    int []sol2;

    boolean start = false;

    Stack stack1;
    Stack stack2;

    Vector second;

    public Solver(String filename){
        int size = InsanitySize(filename);
        System.out.println(""+size);
        if(size==-1)
            System.exit(-1);
        
        A = new int[size][2];
        B = new int[size][2];
        C = new int[size][2];
        
        sol1 = new int[size];
        sol2 = new int[size];
        
        stack1 = new Stack();
        stack2 = new Stack();
        
        second = new Vector();
        
        getData(filename);
                
           
        for(int i=0;i<size; i++)
            sol1[i] =0;

    }
    
    public void getFirstSolution(){
        boolean val = getSolution(sol1);
        boolean solved = false;
        
        for(int i=0; i<sol1.length; i++)
            System.out.print(sol1[1]+ ",");
        System.out.println();
        
        for(int i = 0; i<sol2.length;i++)
            sol2[i] = sol1[i];
        sol2 = get_next_test(sol2);
        boolean val2 = getSolution(sol2);
        for(int i =0;i<sol1.length;i++)
            System.out.print(sol2[i]+",");
        System.out.println();
        solved = isSolution(sol1, sol2);
        if(solved){
            System.out.println();
            System.out.println("Solution found");
            for (int i=0; i< sol1.length; i++)
                System.out.print(sol1[i]+",");
            System.out.println();
            for (int i=0; i<sol1.length;i++)
                System.out.print(sol2[i]+",");
            System.out.println();
            System.out.println();
            return;
        }
        while(!solved){
            int[] temp = sol1.clone();
            second.add(temp);
            sol1 = sol2.clone();
            sol2=get_next_test(sol2);
            val2 = getSolution(sol2);
            for(int i=0; i<sol1.length;i++)
                System.out.print(sol2[i]+",");
            System.out.println();
            solved = isSolution(sol1, sol2);
            if(solved){
                System.out.println();
                System.out.println("Solution found");
                for (int i=0; i<sol1.length; i++)
                    System.out.print(sol1[i]+",");
                System.out.println();
                for (int i=0; i<sol1.length;i++)
                    System.out.print(sol2[i]+",");
                System.out.println();
                System.out.println();
                return;
            }
            int[] temp2 = solution(sol1, second);
            if(temp2 !=null){
                System.out.println();
                System.out.println("Solution found");
                for(int i=0; i<sol1.length;i++)
                    System.out.print(sol1[i]+",");
                System.out.println();
                for (int i=0; i<sol1.length;i++)
                    System.out.print(temp2[i]+",");
                System.out.println();
                System.out.println();
                return;
            }
        }
    }

    public void getAllSoluions(){
        boolean val = getSolution(sol1);
        boolean solved = false;
        boolean temp = true;
        for (int i=0; i< sol1.length; i++)
            System.out.print(sol1[i]+",");
        System.out.println();
        for (int i=0; i<sol2.length; i++)
            sol2[i]=sol1[i];
        sol2 = get_next_test(sol2);
        boolean val1 = getSolution(sol2);
        for (int i=0; i< sol1.length; i++)
            System.out.print(sol2[i]+",");
        System.out.println();
        solved = isSolution(sol1, sol2);
        if(solved){
            System.out.println();
            System.out.println("Solution found");
            for(int i=0; i<sol1.length; i++)
                System.out.print(sol1[i]+",");
            System.out.println();
            for (int i = 0; i<sol1.length; i++)
                System.out.print(sol2[i]+",");
            System.out.println();
            System.out.println();
            return;
        }
        while(!start){
            solved = false;
            while(!solved){
                int[] temp1 = sol1.clone();
                second.add(temp1);
                sol1 = sol2.clone();
                sol2 = get_next_test(sol2);
                val1 = getSolution(sol2);
                for (int i=0; i<sol1.length; i++){
                    System.out.print(sol2[i]+",");
                    if(sol2[i]!=0)
                        temp = false;
                }
                if(temp){
                    System.out.print("....End of data set");
                    start = true;
                    break;
                }
                System.out.println();
                temp = true;
                solved = isSolution(sol1, sol2);
                if(solved){
                    System.out.println();
                    System.out.println("Solution found");
                    for(int i=0; i<sol1.length; i++)
                        System.out.print(sol1[i]+",");
                    System.out.println();
                    for(int i=0; i<sol1.length; i++)
                        System.out.print(sol2[i]+",");
                    System.out.println();
                    System.out.println();
                    break;
                }

                int[] temp2 = solution(sol2, second);
                if(temp2!=null){
                    System.out.println();
                    System.out.println("Solution found");
                    for(int i=0; i< sol1.length; i++)
                        System.out.print(sol1[i]+",");
                    System.out.println();
                    for (int i=0;i<sol1.length; i++)
                        System.out.print(temp2[i]+",");
                    System.out.println();
                    System.out.println();
                    return;
                }
            }
        }

    }

    private int[] solution(int[] sol1, Vector second) {
        int ln = second.toArray().length;
        for(int i=0; i<ln; i++){
            boolean good = true;
            int[] test = (int[]) second.get(i);
            for (int j=0; j < sol1.length; j++){
                if(sol1[j]==test[i]){
                    good = false;
                    break;
                }
            }
            if(good){
                return test;
            }
        }
        return null;
    }

    private boolean isSolution(int[] sol1, int[] sol2) {
        for (int i =0; i<sol1.length; i++){
            if(sol1[i]==sol2[i])
                return false;
        }
        return true;
    }

    private int[] get_next_test(int[] sol2) {
        int[] out = sol2;
        boolean carry = true;
        int index = out.length-1;
        while(carry){
            if(index<0)
                return null;
            out[index]+=1;
            if(out[index]%3 ==0){
                out[index--]=0;
                carry = true;
            }
            else
                carry = false;
        }
        return out;
    }

    private boolean getSolution(int[] sol1) {
        int[] edge = new int[sol1.length];
        for(int i=0; i<edge.length; i++)
            edge[i] = 0;
        boolean val=getSolution(sol1, edge,0);
        return val;
    }
    private boolean getSolution(int[] sol1, int[] edge, int level){
        boolean solved = true;
        if(level==edge.length)
            return true;
        do{
            if(!solved){
                Trace trace = Trace.pop(stack1);
                int n = trace.getArray();
                int m = trace.getIndex();
                if(n==0){
                    edge[A[m][0]]--;
                    edge[A[m][1]]--;
                    sol1[m]++;
                }
                if(n==1){
                    edge[B[m][0]]--;
                    edge[B[m][1]]--;
                    sol1[m]++;
                }
                if(n==2){
                    edge[C[m][0]]--;
                    edge[C[m][1]]--;
                    sol1[m]++;
                    return false;

                }
            }
            if(sol1[level]==0){
                if((A[level][0]==A[level][1])&&(edge[A[level][0]]>=1))
                    sol1[level]++;
                else if((edge[A[level][0]]<2)&&(edge[A[level][1]]<2)){
                    new Trace(0, level).push(stack1);
                    edge[A[level][0]]++;
                    edge[A[level][1]]++;
                }
                else
                    sol1[level]++;
            }
            if (sol1[level]==1){
                if((B[level][0]==B[level][1])&&(edge[B[level][0]]>=1))
                    sol1[level]++;
                else if((edge[B[level][0]]<2)&&(edge[B[level][0]]<2)){
                    new Trace(1, level).push(stack1);
                    edge[B[level][0]]++;
                    edge[B[level][1]]++;
                }
                else
                    sol1[level]++;
            }
            if (sol1[level]==2){
                if((C[level][0]==C[level][1])&&(edge[C[level][0]]>=1)) {
                    sol1[level]=0;
                    return false;
                }
                else if((edge[C[level][0]]<2)&&(edge[C[level][0]]<2)){
                    new Trace(2, level).push(stack1);
                    edge[C[level][0]]++;
                    edge[C[level][1]]++;
                }
                else {
                    sol1[level]=0;
                    return false;
                }
            }
            solved = getSolution(sol1, edge, level+1);
        }while(!solved);
        return true;
    }

    private void getData(String filename) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            int index = 0;
            String temp = in.readLine();
            while(temp!=null){
                if(temp.charAt(0)=='(')
                    temp = temp.substring(1, temp.length()-1);
                String[] num = temp.split(",");
                for(int i=0; i<num.length; i++)
                    num[i] = num[i].trim();
                int[] number = new int[6];
                for(int i =0; i<6;i++)
                    number[i] = Integer.parseInt(num[i])-1;
                A[index][0] =number[0];
                A[index][1] =number[1];
                B[index][0] =number[2];
                B[index][1] =number[3];
                C[index][0] =number[4];
                C[index][1] =number[5];
                index++;
                temp = in.readLine();
                System.out.println(temp);
            }
            in.close();
        } catch (Exception  e) {
            System.exit(0);
        }
    }

    private int InsanitySize(String filename) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            int size = 0;
            String temp = in.readLine();
            while(temp!=null){
                size++;
                temp = in.readLine();
            }
            in.close();

        }catch(Exception e){
            System.exit(0);
        }
        return -1;
    }
    public int [][] convertSolution(int[] sol){
        int[][] solved = new int[sol.length][sol.length];
        for (int i=0; i< sol.length; i++){
            if(sol[i]==0){
                solved[i][0]=A[i][0];
                solved[i][1]=A[i][1];
            }else if(sol[i]==1){
                solved[i][0]=B[i][0];
                solved[i][1]=B[i][1];
            }else if(sol[i]==2){
                solved[i][0]=C[i][0];
                solved[i][1]=C[i][1];
            }
        }
        return solved;
    }

}

