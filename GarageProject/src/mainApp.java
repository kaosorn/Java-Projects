import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class mainApp {
	
	public static void main(String[] args) {
		
		/**
		 * Create a menu with options
		 * 1 ) user can enter the license plate number and car will be added to garage
		 * 2 ) user can take out the car with license plate number
		 * 3 ) user can see what cars are in the current garage
		 * 4 ) user can load a previously saved file of a garage (check in dates do not work)
		 * 5 ) user can save the current garage set and put it in a txt file
		 * 6 ) this will generate a new file of checked out/removed cars from the garage 
		 * 		and exits the program
		 */
		Scanner input = new Scanner(System.in);
        boolean mainLoop = true;
        int choice;
        String lpn;
        garageSet g1 = new garageSet();
		System.out.println("Welcome! Please make your choice!");
        do {

        	System.out.print("1.) Add your car to garage \n");
        	System.out.print("2.) Take out your car.\n");
        	System.out.print("3.) See cars parked in garage.\n");
        	System.out.print("4.) Load your garage.\n");
        	System.out.print("5.) Save your garage.\n");
        	System.out.print("6.) Exit.");
        	System.out.print("\nSelect an option: ");
        	choice = input.nextInt();
        	input.nextLine();
        
        	if(choice == 1) {
        	   	System.out.println("Enter your license plate number.");
            	//input.nextLine();
            	lpn = input.nextLine();
            	addCar(g1, lpn);
            	System.out.println("You have successfully added your car to the garage.");
        	}else if(choice ==2) {
        		System.out.println("Enter the license plate of the car you wish to take out.");
        		lpn = input.nextLine();
        		g1.checkOutCar(lpn);
        		System.out.println("You have successfully checked out your car.");
        		

        	}else if(choice ==4) {
        		System.out.println("Enter the filename to load your garage.");
        		String filename = input.nextLine();
        		File f = new File(filename);
        		
        		try {
					g1.loadGSData(f);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else if(choice == 3) {
        		System.out.println("Here are the cars in the garage: " + g1.toString());
        		
        	}else if(choice == 5) {
        		
        		System.out.println("Enter the file name and extension in order to save your garage.");
        		String filename = input.nextLine();
        		System.out.println("Successfully saved!");
        		try {
					g1.saveGSData(filename);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else if(choice == 6) {
        		g1.getExitBag();
        		System.out.println("Have a nice day!");
        	}
        }while(choice != 6);
			
		
		
	}
	
	/**
	 * adds the car node to the current garage with license plate from user input
	 * @param g current garage
	 * @param lpn license plate number
	 */
	public static void addCar(garageSet g, String lpn) {
		carDataNode newCar = new carDataNode(lpn);
		g.checkInCar(newCar);
	}


}
