import java.io.File;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Filer {
	static HashMap<String, Vector<String>> files;
	static private Vector<String>temp1;
	static Lock lock=new ReentrantLock(),lock1=new ReentrantLock();
	
	Filer(){
		// TODO Auto-generated method stub
		File file=new File("E:\\lab3"); 
		files=new HashMap<String, Vector<String>>();
		myThread thread1=new myThread();
		thread1.file=file;
		thread1.start();
	}
	
	static void search(File file){
		if (file.isDirectory()) {
			//do you have permission to read this directory?
			files.put(file.getAbsolutePath(),new Vector());
			if (file.canRead()) {
				for (final File temp : file.listFiles()) {
					files.get(file.getAbsolutePath()).add(temp.getName());
					if (temp.isDirectory()) {
						if(myThread.count<myThread.max){
							myThread thread1=new myThread();
							thread1.file=new File(temp.getAbsolutePath());
							thread1.start();
						}
						else{
							search(new File(temp.getAbsolutePath()));
						}
		    		}
		    		else{
		    			lock.lock();
		    			if(files.containsKey(temp.getName())){
		    				files.get(temp.getName()).add(temp.getAbsolutePath());
		    			}
		    			else{
		    				temp1 =new Vector<String>();
		    				temp1.add(temp.getAbsolutePath());
		    				files.put(temp.getName(),temp1 );
		    			}
		    			lock.unlock();
		    		}
		    	}
		    } 
		    else {
		    	System.out.println(file.getAbsoluteFile() + "Permission Denied");
		    }
		}
	}
}
