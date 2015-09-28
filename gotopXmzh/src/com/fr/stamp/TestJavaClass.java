package com.fr.stamp;

public class TestJavaClass {
	   public static void main(String[] args) {
	        //
		   getLineNumber();
	    }

	    public static int getLineNumber() {
	        StackTraceElement[] ste = new Throwable().getStackTrace();
	        System.out.println("---ste-"+ste.length);
	        for(int i=0;i<ste.length;i++){
	        	StackTraceElement  st = ste[i];
	        	System.out.println("---getClassName-"+st.getClassName());
	        	System.out.println("---getFileName-"+st.getFileName());
	        	System.out.println("---getMethodName-"+st.getMethodName());
	        	System.out.println("---getLineNumber-"+st.getLineNumber());
	        	System.out.println("\n");
	        }
	        return 1;
	    }
}
