
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class garageExitBag {

	private carDataNode head;
	private int size;

	/**
	 * Constructor for garageExitBag
	 * setting head to null and
	 * size to 0
	 */
	public garageExitBag() {

		this.head = null;
		this.size = 0;

	}
	
	/**
	 * if a car/node is checked out/deleted, this method
	 * will add the car/node and make a linked list with updated size and head
	 * @param car car/node to be removed from garage set and added to exit bag
	 */

	public void addNodeToBag(carDataNode car) {

		car.next = head;
		car.prev = null;
		if(head != null) {
			head.prev = car;
		}
		head = car;
		this.size++;
		
		

	}
	
	/**
	 * generates a file name with current date and what cars/nodes
	 * were removed from the garage set, and writes to a file
	 * with license plate number and the check out date
	 */

	public void dumpOutputFile() {

		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		
		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter("GarageDump" + format.format(new Date()) + ".txt"));

			carDataNode temp = head;

			while (temp != null) {
				
				writer.write(temp.toString() + "\n");
				temp = temp.getNext();

			}

			writer.close();

		} catch (IOException e) {

// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}
	
	/**
	 * to string method to see
	 * what cars are checked out the garage
	 */
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (head == null) {
			return "Empty";
		}
		else {
			sb.append("Cars checked out: ");
			carDataNode curr = head;
			sb.append(curr.getLPN());
			curr = curr.getNext();
			  while(curr != null) {
		           sb.append("<-->");
		           sb.append(curr.getLPN());
		           curr = curr.next;
		           
		       }
		     //  sb.append("<--Tail");
		       return sb.toString();
		}
		
		
		
	}

}
