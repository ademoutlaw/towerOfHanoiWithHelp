/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author outlaw
 */
public class Tower extends ArrayList<Integer>{
    
    private int lastDiskSize;
        
    protected Tower() {
        this(0);
    }

    protected Tower(int size ) {
        init(size);
    }
    
    public boolean add(int disc){
        System.out.println("disc :"+disc);
        if( disc == 0 )
            return false;
        if(lastDiskSize>disc||lastDiskSize==0){
            lastDiskSize=disc;
            return super.add(disc);
        }
        return false;
    }
    
    protected List<Integer> getDiscs(){ 
      return (List<Integer>) new ArrayList(this);//.clone();
    }

    protected int getLasDisck() {
        if(isEmpty())
            return 0;
        Integer disck = get(size() - 1);
        
        remove(disck);
        if(isEmpty())
            lastDiskSize=0;
        else 
            lastDiskSize=get(size() - 1);
        return  disck; 
    }

    protected boolean accept(Disc disck) {
        return disck.getSize()<lastDiskSize;
    }
    
    @Override
    public String toString() {
        return "Tower{" + "lastDiskSize=" + lastDiskSize + '}';
    }

    protected void init(int level) {
        lastDiskSize = level>0? 1:0;
        super.clear();
        for(int i=level;i>0;i--){
            super.add(i);
        } 
    }
               
}
