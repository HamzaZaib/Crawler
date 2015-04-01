import java.util.Vector;

public class localSpider {

	static Filer filer;
	localSpider(){
		filer=new Filer();
	}
	public static Vector<String> find(String fileName) {
		if(filer==null)
			filer=new Filer();
		if (filer.files.containsKey(fileName)){
			return filer.files.get(fileName);
		}
		return null;
	}
	

}
