package com.src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FolderWatcher implements Subject, Runnable {
	
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    
    private String path;
    private File filesArray [];
    private  Map<String,Long> dir = new HashMap<String,Long>();
    

    public FolderWatcher(String path){
    	this.path = path;
    }
    
    
	public void getLastModifiedTime() {
		// transfer to the hashmap be used a reference and keep the lastModfied
		// value

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		if (listOfFiles != null) {
			System.out.println("List of files are ::");
			for (int i = 0; i < listOfFiles.length; i++) {
				System.out.println(listOfFiles[i]);
				dir.put(listOfFiles[i].getName(), new Long(listOfFiles[i].lastModified()));
			}
		}
	}
    
    

	@Override
    public void registerObserver(Observer observer) {
           observers.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {
           observers.remove(observer);

    }

	@Override
	public void notifyObservers() {
		for (Observer ob : observers) {
			System.out.println("Notifying Observers on change of folder");
			ob.update();
		}
;
	}

	@Override
	public void run() {
		getLastModifiedTime();

		while (true) {
			Set<File> checkedFiles = new HashSet<File>();
			filesArray = new File(path).listFiles();

			// scan the files and check for modification/addition

			if (filesArray != null) {
				for (int i = 0; i < filesArray.length; i++) {
					Long current = (Long) dir.get(filesArray[i].getName());
					checkedFiles.add(filesArray[i]);

					if (current == null) {
						// new file
						dir.put(filesArray[i].getName(), new Long(filesArray[i].lastModified()));
						onChange(filesArray[i], "add");
					} else if (current.longValue() != filesArray[i].lastModified()) {
						// modified file
						dir.put(filesArray[i].getName(), new Long(filesArray[i].lastModified()));
						onChange(filesArray[i], "modify");
					} else {
						if (!filesArray[i].exists()) {
							dir.remove(filesArray[i]);
							onChange(filesArray[i], "deleted");
						}

					}
				}
			}
		}

	}

	private void onChange(File file, String string) {
		notifyObservers();
	}
}


