
import java.util.Date;

public class carDataNode{
	
	private String licensePlateNumber;
	private Date checkIn;
	private Date checkOut;
	public carDataNode next;
	public carDataNode prev;
	
	/**
	 * Constructor for node
	 * @param cdn license plate number
	 */
	
	public carDataNode(String cdn) {
		this.licensePlateNumber = cdn;
		this.checkIn = new Date();
		this.next = null;
		this.prev = null;
	}
	
	/**
	 * Constructor for node with no parameters
	 */
	
	public carDataNode() {
		this.checkIn = new Date();
		this.licensePlateNumber = "DEFAULT";
		this.next = null;
		this.prev = null;
	}
	
	/**
	 * Getter for check in date
	 * @return check in date
	 */
	public Date getCheckIn() {
		return checkIn;
	}
	
	/**
	 * getter for check out
	 * @return check out date
	 */
	
	public Date getCheckOut() {
		return checkOut;
	}
	
	
	/**
	 * Setter for check out date
	 */
	public void setCheckOut() {
		this.checkOut = new Date();
	}
	
	/**
	 * Setter for check in date
	 */
	
	public void setCheckIn() {
		this.checkIn = new Date();
	}
	
	
	/**
	 * Setter for check in date
	 * @param date 
	 */
	public void setCheckIn(Date date) {
		
		this.checkIn = date;
	}
	
	
	/**
	 * Getter for license plate number
	 * @return license plate number
	 */
	
	public String getLPN(){

         return licensePlateNumber;

	}
	
	/**
	 * Setter for license plate number
	 * @param LPN user input license plate
	 */
	
	public void setLPN(String LPN) {
		this.licensePlateNumber = LPN;
	}

	/**
	 * Getter for next node
	 * @return next/pointer node
	 */
	public carDataNode getNext(){

        return next;

	}
	
	/**
	 * Setter for next node
	 * @param next node to set next
	 */
 
	public void setNext(carDataNode next) {
	 this.next = next;
	}
	
	/**
	 * getter for previous node
	 * @return previous node
	 */
	
	public carDataNode getPrev() {
		return prev;
	}
	
	/**
	 * setter for previous node
	 * @param prev sets the previous node
	 */
	
	public void setPrev(carDataNode prev) {
		this.prev = prev;
	}
	
	/**
	 * adds/points to a node after the current node
	 * @param prevNode previous pointer of current node
	 * @param newCar license plate number
	 */
	
	public void addNodeAfter(carDataNode prevNode, String newCar) {
		
		if (prevNode == null) {
			System.out.println("Previous node is null.");
			return;
		}else {
			carDataNode newNode = new carDataNode(newCar);
			newNode.next = prevNode.next;
			prevNode.next = newNode;
			newNode.prev = prevNode;
			if(newNode.next != null) {
				newNode.next.prev = newNode;
			}
			
		}
		
	}
	
	/**
	 * checks to see if a node has the same license plate number
	 * @param comp node to be compared to
	 * @return true license plates match, false if not
	 */
	public boolean equals(carDataNode comp) {
		if(comp.getLPN() == this.licensePlateNumber) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * removes the current calling node
	 */
	public void remove() {
		
		if (getPrev() != null) {
			prev.setNext(next);
		}
		if(getNext() != null) {
			next.setPrev(prev);
		}
		
		next = null;
		prev = null;
	}
	
	/**
	 * toString method for car node, and returns license plate number with check in/out dates with readable string format
	 */
	
	@Override
	public String toString() {
		
		return String.format(this.licensePlateNumber+", check in: "+ this.checkIn + ", check out: " + this.checkOut);
	}

	



	
}
