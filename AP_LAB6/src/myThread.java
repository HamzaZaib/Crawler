import java.io.File;
import java.util.HashMap;
import java.util.Timer;
import java.util.Vector;

class myThread extends Thread {
	static int count=0,max=4;
	private Thread t;
	HashMap<String,Vector<String>> files=Filer.files;
	File file; 
	
	myThread(){
		count++;
	}
	
	public void run() {
		Filer.lock1.lock();
		Filer.search(file);
		Filer.lock1.unlock();
		new Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	Filer.lock1.lock();
		            	localSpider.filer=new Filer();
		            	Filer.lock1.unlock();
		            }
		        }, 
		        5000 
		);
		count--;
	}
	
	public void start (){
		if (t == null){
			t = new Thread (this);
			t.start ();
		}
	}

	
}