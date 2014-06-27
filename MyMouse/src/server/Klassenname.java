package server;

import java.io.File;
import java.io.FilenameFilter;

public class Klassenname {

	public String[] getKlassen(){
		String klasse = new File("").getAbsolutePath();
		klasse += "/src/figur";
		File f = new File(klasse);
		String[] files = f.list(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				if(name.equals("AbstractMouse.java")){
					return false;
				}else{
					return true;
				}
			}	
		});
		
		for(int i = 0; i < files.length; i++){
			int length = files[i].length()-5;
			files[i] = files[i].substring(0, length);
		}


		return files;
	}
}
