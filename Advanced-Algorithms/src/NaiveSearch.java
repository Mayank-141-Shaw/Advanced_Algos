import java.util.Scanner;

public class NaiveSearch {
	
	static void search(String txt, String pat)
    {
        int l1 = txt.length();
        int l2 = pat.length();
        int i = 0, j = l2 - 1;
 
        for (i = 0, j = l2 - 1; j < l1;) {
 
            if (pat.equals(txt.substring(i, j + 1))) {
                System.out.println("Pattern found at index "+ i);
            }
            i++;
            j++;
        }
    }
     
      // Driver's code
    public static void main(String args[])
    {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter a text");
        String pat = sc.nextLine();
        System.out.println("Enter a pattern");
        String txt = sc.nextLine();
     
        search(pat, txt);
    }

}
