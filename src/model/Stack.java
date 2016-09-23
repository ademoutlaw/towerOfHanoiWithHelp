<<<<<<< HEAD
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
public class Stack extends ArrayList<Disc>{
    
    private int lastDiskSize;
    
    
    protected Stack() {
        this(0);
    }

    protected Stack(int size ) {
        lastDiskSize = size>0? 1:0;
        for(int i=size;i>0;i--){
            add(new Disc(i));
        } 
    }
    
    protected boolean addDisk(Disc disk){
        System.out.print(disk+" ?: ");
        System.out.println(disk.getSize()+" ?: "+lastDiskSize+" ?: "+(lastDiskSize<disk.getSize()));
        if(lastDiskSize>disk.getSize()||lastDiskSize==0){
            lastDiskSize=disk.getSize();
            return add(disk);
        }
        return false;
    }
    
    protected ArrayList<Integer> getDiscksSizes(){
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for(Object disk:this) {
            Disc disck = (Disc)disk;
            intList.add(disck.getSize());
        } 
      return intList;
    }

    protected Disc getLasDisck() {
        if(isEmpty())
            return null;
        Disc disck = get(size() - 1);
        
        remove(disck);
        if(isEmpty())
            lastDiskSize=0;
        else 
            lastDiskSize=get(size() - 1).getSize();
        return  disck; 
    }

    protected boolean accept(Disc disck) {
        return disck.getSize()<lastDiskSize;
    }

    ArrayList<Integer> getStack() {
        ArrayList a= new ArrayList();
        for(Disc disc:this){
            a.add(disc.getSize());
        }
        return a;
    }
   

    
    
}
=======
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
>>>>>>> 345a305dc6e6f9fa62c849f2c87d5d0d823dbfcf
