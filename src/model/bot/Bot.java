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

    public Bot(List<Integer>  towerA, List<Integer>  towerB, List<Integer>  towerC){
        grid = new List[3];
        grid[0] = towerA;
        grid[1] = towerB;
        grid[2] = towerC;
        
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

    private void next() {
        initIndexes();
        int safe = 0;
       /* boolean jumped;
        for(int i=0;i<grid[from].size()-1;i++){
            if(grid[from].get(i)+1<grid[from].get(i+1)){
                disc=grid[from].get(i+1);
                jumped = true;
            }
        }*/
        while(true){
            if(hasSupport()){
                if(isFree()){
                    if(hasPlace()){
                        System.out.println("yes move:"+disc+" from:"+from+" to:"+to);
                        break;
                    }
                    else{
                        int j=0;
                        while(grid[to].get(j)>disc)
                            j++;
                        currentIndex=j;
                        to=3-to-from;
                        from=3-to-from;
                        disc = grid[from].get(currentIndex);
                        System.out.print("noPlace!! ");
                        System.out.println(disc+" from:"+from+" to:"+to);
                    }
                }else{
                    currentIndex++;
                    disc=grid[from].get(currentIndex);
                    to=3-from-to;
                    System.out.println("noFree!!");
                }
            }else{
                System.out.println("---------- from:"+from+" to:"+to+" disc:"+disc);
                from=3-from-to;
                disc++;
                currentIndex=grid[from].indexOf(disc);
                System.out.println("---------- from:"+from+" to:"+to+" disc:"+disc+" ====>"+currentIndex);
                System.out.println("noSupport!!");
            }
            safe++;
            if(safe>3375){
              System.out.println("no!!");
              break;
            }
        }
    }

    private void initIndexes() {
        if(grid[0].isEmpty()||(!grid[1].isEmpty()&&grid[1].get(0)>grid[0].get(0)))
            from=1;
        else
            from = 0;
        to = 2;
        currentIndex = 0;
        disc=grid[from].get(0);
    }

    private boolean hasSupport() {
        System.out.println(currentIndex);
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
            System.out.println("no"+grid[from].get(currentIndex)+" "+grid[from].get(currentIndex-1));
        }                     
        return grid[to].indexOf(grid[from].get(currentIndex)+1)>=0;
    }

    private boolean isFree() {        
        return grid[from].size()==currentIndex+1||grid[from].size()==0;
    }

    private boolean hasPlace() {
        return grid[to].isEmpty()||grid[to].get(grid[to].size()-1)>disc;
        
    }

    public void help() {
        next();
    }

    
    
    
}
