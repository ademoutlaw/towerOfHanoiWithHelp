package model;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.Tower;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Loader {
    
    static int helpTotal;
    static int helpUsed;
    static int currentLevel;
    static int lastLevel;
    static Tower towerA;
    static Tower towerB;
    static Tower towerC;
    static int steps;
    static boolean freeDestin;
    static boolean saved;
    static Stack stack;
    
    static {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();            	
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
	    final Document document= builder.parse(
                    new File("C:\\Users\\outlaw\\AppData\\Local\\tower of hanoi/towers.xml"));		
	    final Element towers = document.getDocumentElement();
            try{
                saved = Boolean.getBoolean(towers.getAttribute("saved"));
                //-----------------------------------------------------
                saved = true;
                loadTower(towers);
                setHelp(towers);
                setLevel(towers);
                setFree(towers);
                setSteps(towers);
                stack = new Stack();
            }catch(NumberFormatException e){
                System.out.println(" RELOAD ALL");
            }            
            System.out.println("helpTotal: "+helpTotal);
            System.out.println("helpUsed: "+helpUsed);
            System.out.println("levelCurrent: "+currentLevel);
            System.out.println("lastLevel:"+lastLevel);
        }
        catch (final ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }		
    }
        
    static void loadTower(Element towers)throws NumberFormatException{
        final NodeList tower = towers.getElementsByTagName("tower");        
        int length = tower.getLength();
        for(int i=0;i<length;i++){
           final Element towerE = (Element) tower.item(i);
            switch(towerE.getAttribute("name")){
                case "A":
                    towerA = loadDiscs(towerE);
                    break;                
                case "B":
                    towerB = loadDiscs(towerE);
                    break;                
                case "C":
                    towerC = loadDiscs(towerE);
                    break;                        
            }                                                
        }        
    }
    
    private static Tower loadDiscs(Element towerE)throws NumberFormatException {
        Tower tower = new Tower();        
        NodeList discs = towerE.getElementsByTagName("disc");		    
        int length = discs.getLength();        
        for(int i = 0; i<length; i++) {
            tower.add(Integer.parseInt(((Element) discs.item(i)).getTextContent()));                        
        }    
        return tower;        
    }

    private static void setHelp(Element towers) {
        final Element help = (Element) towers.getElementsByTagName("help").item(0);
        helpTotal = Integer.parseInt(help.getAttribute("total"));
        helpUsed = Integer.parseInt(help.getAttribute("used"));
    }

    private static void setLevel(Element towers) {
        final Element level = (Element) towers.getElementsByTagName("level").item(0);
        currentLevel = Integer.parseInt(level.getAttribute("current"));
        lastLevel = Integer.parseInt(level.getAttribute("last"));
    }

    private static void setFree(Element towers) {
	final NodeList free = towers.getElementsByTagName("free");
        System.out.println(free);
        freeDestin = true;                    
    }

    private static void setSteps(Element towers) {
	final NodeList step = towers.getElementsByTagName("steps");
        System.out.println(step);
        steps=0;
    }

   

    
    
}
