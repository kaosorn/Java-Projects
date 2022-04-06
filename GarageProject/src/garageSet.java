import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class garageSet implements Serializable  {
	
	public static carDataNode head;
	public static carDataNode tail;
	private int size;
	private garageExitBag exitBag = new garageExitBag();
	
	/**
	 * Constructor for garage class, sets head and tail to null, size to 0
	 */
	public garageSet() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		
	}
	
	/**
	 * Getter for head node
	 * @return head node
	 */
	
	public carDataNode getHeadNode() {
		return head;
	}
	
	/**
	 * Getter for tail node
	 * @return tail node
	 */
	
	public carDataNode getTail() {
		return tail;
	}
	
	/**
	 * checks in/adds the car nodes to a doubly linked list
	 * keeping track of head node and tail node
	 * if there is a car with same license plate number already in the list, will
	 * return "car is parked" to user
	 * else it makes a new car node and adds the node to the list and updates size of list
	 * @param lpn license plate number
	 */
	public void checkInCar(String lpn) {
	
		
		carDataNode newCar = new carDataNode(lpn);
		
		
		if(head == null) {
			head = tail = newCar;
			head.prev = null;
			tail.next = null;
			size++;
		}else {
			if(checkDup(newCar)) {
				System.out.println("Car is already parked.");
				return;
			}
			tail.next = newCar;
			newCar.prev = tail;
			tail = newCar;
			tail.next = null;
			size++;
		}
		
		
	}
	
	/**
	 * checks in/adds the car nodes to a doubly linked list
	 * keeping track of head node and tail node
	 * if there is a car with same license plate number already in the list, will
	 * return "car is parked" to user
	 * else adds calling car node to the list and updates size of list
	 * @param newCar car node to be added
	 */
	public void checkInCar(carDataNode newCar) {
		
		if(head == null) {
			head = tail = newCar;
			head.prev = null;
			tail.next = null;
			size++;
		}else {
			if(checkDup(newCar)) {
				System.out.println("Car is already parked.");
				return;
			}
			tail.next = newCar;
			newCar.prev = tail;
			tail = newCar;
			tail.next = null;
			size++;
		}
		
	}
	
	
	/**
	 * when user exits the program,
	 * this method is ran to ensure dumpOutFile() is called in garageExitBag class
	 * makes a file with current date of all the checked out cars/nodes
	 */
	public void getExitBag() {
		
		this.exitBag.dumpOutputFile();
	}
	
	/**
	 * user enters the license plate number corresponding to the car they want to check out/remove from the
	 * garage
	 * @param lpn license plate number
	 */
	public void checkOutCar(String lpn) {
		
		carDataNode rCar = new carDataNode();
		carDataNode temp;
		temp = head;
		
		while(temp!=null) {
			if(temp.getLPN().equals(lpn)) {
				rCar = temp;
			}
			temp = temp.next;
		}
		
		remove(rCar);
		
		
	}
	
	/**
	 * node corresponding to the car that needs to be removed from list and
	 * garage
	 * @param target car node to be removed
	 */
	public void checkOutCar(carDataNode target) {
		
		remove(target);

		
	}
	
	/**
	 * removes the car node in the parameter, and adds the node
	 * to the garage exit bag, and updates head, tail, and size 
	 * in the list
	 * @param target car node to be removed and sent to exit bag
	 */
	public void remove(carDataNode target) {
		
		if(target == head) {
			target.setCheckOut();
			removeAtStart();
			exitBag.addNodeToBag(target);
			size--;
		}else if(target==tail) {
			target.setCheckOut();
			removeAtEnd();
			exitBag.addNodeToBag(target);
			size--;
		}else {
			carDataNode temp;
			temp = head;
			
			while(temp != null) {
				if(temp == target) {
					
					target.prev.next = target.next;
					target.next.prev = target.prev;
					target.setCheckOut();
					exitBag.addNodeToBag(target);
					size--;
				}
				temp = temp.next;
			}
		}
	}
	
	
	/**
	 * if the head node is to be removed
	 * adjusts the links so head points to next head
	 * 
	 */
	public void removeAtStart() {
		if (isEmpty()) {
			return;
		}
		if(head == tail) {
			head = null;
			tail = null;
		}else {
			head = head.next;
			head.prev = null;
		}
	}
	
	/**
	 * if car node to be removed is the tail node, adjust the links
	 * so tail is moved
	 */
	public void removeAtEnd() {
		if(isEmpty()) {
			return;
		}
		
		if(tail == head) {
			tail = null;
			head = null;
		}else {
			tail = tail.prev;
			tail.next = null;
		}
	}
	
	
	/**
	 * checks to see if the list is empty
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * returns the size of the list
	 * @return size
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Traverses the list and gets the index of the car node in parameter
	 * @param car node to be searched for
	 * @return index of where the car node is in the list
	 */
	public int indexOf(carDataNode car) {
		
		carDataNode temp = head;
		int result = 0;
		while(temp != null) {
			if(car.getLPN().equals(car.getLPN())) {
				return result;
			}
			temp = temp.next;
			result++;
		}
		return -1;
	}
	
	/**
	 * takes a user input file name and loads the data from the 
	 * file into a new list
	 * Currently it only loads the license plate number
	 * and not the check in dates, I did not know how
	 * to load the raw string and convert it into a date object
	 * in order to add it as a date object to the list
	 * @param txt file to be read
	 * @throws FileNotFoundException if file is not found or doesn't exist, throws error
	 */
	public void loadGSData(File txt) throws FileNotFoundException {
		
		Scanner s = new Scanner(new FileReader(txt));
		carDataNode temp;
		
		while(s.hasNextLine()) {
			String[] tokens = s.nextLine().split(",");
			String date = tokens[tokens.length - 1];
			String lpn = tokens[0];
			
			checkInCar(new carDataNode(lpn));
			
			System.out.println(lpn);
			
			
		}
		s.close();
		
	}
	
	
	/**
	 * user enters a file name and creates the file and then 
	 * stores the data in it, with license plate number and check in date
	 * separated by a comma
	 * @param filename user input file name
	 * @throws FileNotFoundException if file is not found or does not exist, throws error
	 * @throws IOException
	 */
	public static void saveGSData(String filename) throws FileNotFoundException, IOException {
		
		File f = new File(filename);
		PrintWriter out = null;
		out = new PrintWriter(filename);
		carDataNode curNode = head;
		
		while(curNode != null) {
			out.println(curNode.getLPN() + "," + curNode.getCheckIn());
		
			curNode = curNode.next;
		}
		out.flush();
		out.close();
		
	}
	
	
	/**
	 * checks to see if a car node is already
	 * in the list by comparing license plate numbers
	 * @param car node to be compared to
	 * @return true if a car already exists in the list, false if not
	 */
	public boolean checkDup(carDataNode car) {
		
		carDataNode temp = head;
		while(temp != null) {
			if(temp.getLPN().equals(car.getLPN())) {
				return true;
			}
		
			temp = temp.getNext();
		}
		return false;
	}
	
	/**
	 * is called when the user exits the program and 
	 * dump out file is generated
	 */
	public void exitString() {
		System.out.println(exitBag.toString());
	}
	/**
	 * can see what car/nodes are in the list and the
	 * size of the list
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		if (head == null) {
			return "Empty";
		}
		else {
			sb.append("Cars Parked:  ");
			carDataNode curr = head;
			sb.append(curr.getLPN());
			curr = curr.getNext();
			  while(curr != null) {
		           sb.append("<-->");
		           sb.append(curr.getLPN());
		           curr = curr.next;
		           
		       }
		       return sb.toString();
		}
	}
	

}
