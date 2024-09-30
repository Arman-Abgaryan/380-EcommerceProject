

import java.util.Scanner;

public class SearchExample
{

   public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    
	    String [] clothes = {"Shirt"};
	    
	    System.out.println("Choose a clothing: ");
		
		String target = scanner.nextLine();
		
		if (Search(clothes,target)){
		    //if clothing is found go to next page
			System.out.println("Found");
		}else {
		    System.out.println("We don't have that");
		}
		
	scanner.close();
		
	}
   
   public static boolean Search(String [] clothes, String target){
//go through clothes array
   for (String item : clothes){
//if the item in the array equals the target 
       if (item.equalsIgnoreCase(target)){
           return true; // if the item is found return true
       }
   }
   
   return false;
}
}


