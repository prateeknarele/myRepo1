package com.src;

public class Test {


		   public static void main(String[] args) {
		    FolderListener person1=new FolderListener("Prateek");
		    FolderListener person2=new FolderListener("Manish");
		    
		    FolderWatcher folderChange =new FolderWatcher("K:\\folderChangeTest");
		    
		    //When you opt for option "Notify me when product is available".Below registerObserver method
		    //get executed   
		    folderChange.registerObserver(person1);
		    folderChange.registerObserver(person2);
		    
		    Thread folderChangeThread = new Thread(folderChange);
		    
		    System.out.println("Execution started");
		    
		    //Now product is available
		    folderChangeThread.start();
		    
		    System.out.println("Execution Done");
		   }

}
