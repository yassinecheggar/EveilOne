
public class Tes {
	
	public Tes() {
		exp01("lakdj45");
	}
	
	

	public static void main(String[] args) {

		new Tes();
		
	}

	public String exp01(String code) {
		char a[] = code.toCharArray();
		String UUid="";
		System.out.println(a.length);
		for (int i = 0; i < 32; i++) {
			if((i==8)|| (i==12)||(i==16)||(i==20)){ UUid = UUid.concat("-");}	
			if(i<a.length ) {UUid = UUid.concat(String.valueOf(a[i]));}
			else {UUid = UUid.concat("a");}	
		}
		System.out.println(UUid);
		return code;

	}

}
