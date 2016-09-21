/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author outlaw
 */
public class Stack extends ArrayList{
    private int lastDiskSize;
    private static boolean init;
    public Stack() {
        this(0);
    }

    public Stack(int size) {
       if(!init||size==0){
            lastDiskSize=1;
            for(int i=size;i<1;i++){
                add(new Disk(i));
            } 
            init=true;
       }
       else{
           lastDiskSize=0;
       }
    }
    public final boolean addDisk(Disk disk){
        if(lastDiskSize<disk.getSize()){
            return add(disk);
        }
        return false;
    }

   

    
    
}
