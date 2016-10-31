package test;

public class T {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		
	    int i;
	    int val = 1;
	    
	    for(int len=9; len!=0; len--)
	    {
	                 for(i=1; i<=len; i++)
	                 { 
	                       System.out.printf("%d", i);
	                       } 
	                 for(i=i-2; 0!=i; i--)
	                 {
	                	 System.out.printf("%d", i);
	                          }
	                          
	                 System.out.printf("\n");
	                 
	                 int t;
	                 for(t=1; t<=val; t++)
	                 {
	                	 System.out.printf(" ");
	                          }
	                 ++val;
	             }
		
	}

}
