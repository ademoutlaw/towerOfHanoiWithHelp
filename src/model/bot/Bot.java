/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bot;

import java.util.List;

/**
 *
 * @author outlaw
 */
public class Bot {
    
    private final int TOWER_A = 0;
    private final int TOWER_B = 1;
    private final int TOWER_C = 2;
    
    private int from;
    private int to;
    private int disc;
    
    private final List<Integer>  [] grid;
    private int currentIndex;
    private boolean isFreeDistin;

    public Bot(List<Integer>  towerA, List<Integer>  towerB, List<Integer>  towerC){
        grid = new List[3];
        grid[0] = towerA;
        grid[1] = towerB;
        grid[2] = towerC;
        isFreeDistin =true;
        
    }
    
   

    public char getFrom() {
        return (char) ('A'+from);
    }

    public char getTo() {
        return (char) ('A'+to);
    }

    public int getDisc() {
        return disc;
    }

    public void help() {
        initIndexes();
        int safe = 0;
        while(true){
            if(hasSupport()){
                if(isFree()){
                    if(hasPlace()){
                        break;
                    }
                    else{
                        hasNoPlace();
                        
                    }
                }else{
                    isNoFree();
                    
                }
            }else{
                hasNoSupport();                
            }
            safe++;
            if(safe>3375){
              break;
            }
        }
    }

    private void hasNoSupport() {
        from=3-from-to;
        disc++;
        currentIndex=grid[from].indexOf(disc);
    }

    private void isNoFree() {
        currentIndex++;
        disc=grid[from].get(currentIndex);
        to=3-from-to;
    }

    private void hasNoPlace() {
        int j=0;
        while(grid[to].get(j)>disc)
            j++;
        currentIndex=j;
        to=3-to-from;
        from=3-to-from;
        disc = grid[from].get(currentIndex);
    }

    private void initIndexes() {
        
        int a = getAxeOfMaxDisc();
        int x = getDisc(a,0);
        int b = getAxeOfSecondMaxDisc();  
        int y =  getDisc(b,0);
        int c = 3-a-b;
        
        if(a==0){
            from = 0;
            to = isFreeDistin?(x % 2 == y % 2?b:c):2;
            
        }else{
            System.out.println("a!=0 "+a);
            to = isFreeDistin?a:2;
            from = isFreeDistin?b:1;
        }
        currentIndex = 0;
        disc=getDisc(from,0);
        System.out.println("================"+from+" to "+to);
    }

    private boolean hasSupport() {
        //System.out.println(currentIndex);
        if(currentIndex==-1){
           System.out.println("#######################################");
           System.out.println(currentIndex);
           System.out.println(disc);
           System.out.println(from);
           System.out.println(to);
        }
        if (currentIndex == 0)
                return true;
        if(grid[from].get(currentIndex)==
                grid[from].get(currentIndex-1)-1){            
            return true;
        }else{
            //System.out.println("no"+grid[from].get(currentIndex)+" "+grid[from].get(currentIndex-1));
        }                     
        return grid[to].indexOf(grid[from].get(currentIndex)+1)>=0;
    }

    private boolean isFree() {        
        return grid[from].size()==currentIndex+1||grid[from].isEmpty();
    }

    private boolean hasPlace() {
        return grid[to].isEmpty()||grid[to].get(grid[to].size()-1)>disc;
        
    }

    private int getAxeOfMaxDisc() {
        int a = getDisc(0,0);
        int b = getDisc(1,0);
        int c = getDisc(2,0);
        if(a>b){
            if(a>c){
                return 0;
            }
            return 2;
        }
        if(b>c){
            return 1;
        }
        return 2;
    }

    private int getAxeOfSecondMaxDisc() {
        int a = getDisc(0,0);
        int b = getDisc(1,0);
        int c = getDisc(2,0);
        
        if(a>b){
            if(b>c){
                return 1;
            }
            if(c>a){
              return 0;  
            }
            return 2;
        }
        if(c>b){
            return 1;
        }
        if(c>a){
            return 2;  
        }
        return 0;
    }

    private int getDisc(int a, int i) {
        return grid[a].size()>i?grid[a].get(i):0;
    }

    public void setIsFreeDistin(boolean isFreeDistin) {
        this.isFreeDistin = isFreeDistin;
    }

    
    
    
}
