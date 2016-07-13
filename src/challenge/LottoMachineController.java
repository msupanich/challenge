package challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * The main Lottery controller. 
 * @author supanichm
 * @since  7/12/2016
 *
 */
public class LottoMachineController {
	int currentCustId;
	ArrayList<LottoCustomer> customers;
	LottoCustomer currentCustomer;
	
	//LottoPicker classes
	LottoPickThree pickThree;
	LottoPickFour pickFour;
	LottoPickFive pickFive;
	
	boolean soldOutAttempt;
	boolean pickThreeSoldOut;
	boolean pickFourSoldOut;
	boolean pickFiveSoldOut;
	
	HashMap<Integer, LottoCustomer> allCustomerTickets;
	
	public LottoMachineController() {
		customers = new ArrayList<LottoCustomer>();
		currentCustId = 1;
		pickThree = new LottoPickThree();
		pickFour = new LottoPickFour();
		pickFive = new LottoPickFive();
		soldOutAttempt = false;
		pickThreeSoldOut = false;
		pickFourSoldOut = false;
		pickFiveSoldOut = false;
		allCustomerTickets = new HashMap<Integer, LottoCustomer>();
	}
	
	/**
	 * Set up a new customer with a current ID.
	 * @param name
	 */
	public void newCustomer(String name) {
		currentCustomer = new LottoCustomer(name, currentCustId);
		currentCustId++;
	}
	
	/**
	 * Assign tickets for each type for the given amounts. If there are not enough tickets of
	 * a certain type then automatically and randomly choose a new type. If not enough tickets remain
	 * to complete full purchase then complete what is available.
	 * 
	 * @param numPickThree
	 * @param numPickFour
	 * @param numPickFive
	 */
	public void assignTickets(int numPickThree, int numPickFour, int numPickFive) {
		int totalTickets = numPickThree + numPickFour + numPickFive;
		if (totalTickets <= 0 || totalTickets > 5) {
			System.out.println("Please select between 1 and 5 tickets.");
			return;
		}
		
		checkSoldOut();
		int leftOverTickets = 0;
		while (totalTickets > 0) {
			int threeRemaining = pickThree.getRemaining();
			if (!pickThreeSoldOut && numPickThree > 0 && numPickThree > threeRemaining) {
				leftOverTickets =+ numPickThree - threeRemaining;
				totalTickets =- threeRemaining;
				pickThreeSoldOut = true;
			}
			
			int fourRemaining = pickFour.getRemaining();
			if (!pickFourSoldOut && numPickFour > 0 && numPickFour > fourRemaining) {
				leftOverTickets =+ numPickFour - fourRemaining;
				totalTickets =- fourRemaining;
				pickFourSoldOut = true;
			}
			
			int fiveRemaining = pickFive.getRemaining();
			if (!pickFiveSoldOut && numPickFive > 0 && numPickFive > fiveRemaining) {
				leftOverTickets =+ numPickFive - fiveRemaining;
				totalTickets =- fiveRemaining;
				pickFiveSoldOut = true;
			}
			//TODO Need to handle what happens if follow checks put it over the max.
			//nextInt() is exclusive on max.
			int newTicketType = ThreadLocalRandom.current().nextInt(1, LottoConstants.NUMTICKETTYPES + 1);
			switch (newTicketType) {
				case 1: if (!pickThreeSoldOut) {
							numPickThree =+ leftOverTickets;
						}
				case 2: if (!pickFourSoldOut) {
							numPickFour =+ leftOverTickets;
						}
				case 3: if (!pickFiveSoldOut) {
							numPickFive =+ leftOverTickets;
						}
			}
		}
		
		
		if (numPickThree > 0) {
			buyPickThreeTickets(numPickThree);
		}
		if (numPickFour > 0) {
			buyPickFourTickets(numPickFour);
		}
		if (numPickFive > 0) {
			buyPickFiveTickets(numPickFive);
		}
		
		customers.add(currentCustomer);
	}
	
	private void checkSoldOut() {
		pickThreeSoldOut = pickThree.hasRemaining();
		pickFourSoldOut = pickFour.hasRemaining();
		pickFiveSoldOut = pickFive.hasRemaining();
	}

	/**public void Main (String[] args) { 

		LottoPickThree pickThree = new LottoPickThree();
		LottoPickFour pickFour = new LottoPickFour();
		LottoPickFive pickFive = new LottoPickFive();

		
		while (true) {
				
			}

			LottoCustomer newCustomer = new LottoCustomer(name, currentCustId);
			
			for (int i = 0;  i < numPickThree; i++) {
				if (!pickThree.checkRemaining()) {
					int newTicketType = ThreadLocalRandom.current().nextInt(1, LottoConstants.NUMTICKETTYPES + 1);
					int pickThreeRemaining = numPickThree - i;
					
					switch (newTicketType) {
					case 1: numPickFour += pickThreeRemaining;
					case 2: numPickFive += pickThreeRemaining;
					}
				}
				LottoPickThreeTicket threeTicket = pickThree.pickThree();
				
				if (threeTicket != null) {
					newCustomer.addPickThreeTicket(threeTicket);
				}
			}
			
			for (int i = 0;  i < numPickFour; i++) {
				LottoPickFourTicket fourTicket = pickFour.pickFour();
				
				if (fourTicket != null) {
					newCustomer.addPickFourTicket(fourTicket);
				}
			}
			
			for (int i = 0;  i < numPickFive; i++) {
				LottoPickFiveTicket fiveTicket = pickFive.pickFive();
				
				if (fiveTicket != null) {
					newCustomer.addPickFiveTicket(fiveTicket);
				}
			}
			
			customers.add(newCustomer);
		}
	}**/

	/**
	 * Create and assign tickets for the number of tickets requested
	 * @param numPickThree
	 */
	private void buyPickThreeTickets(int numPickThree) {
		for (int i = 0;  i < numPickThree; i++) {
			LottoPickThreeTicket threeTicket = pickThree.pickThree();
			
			if (threeTicket != null) {
				currentCustomer.addPickThreeTicket(threeTicket);
				allCustomerTickets.put(threeTicket.getTicketNumber(), currentCustomer);
			}
		}
		
	}

	/**
	 * Create and assign tickets for the number of tickets requested
	 * @param numPickFour
	 */
	private void buyPickFourTickets(int numPickFour) {
		for (int i = 0;  i < numPickFour; i++) {
			LottoPickFourTicket fourTicket = pickFour.pickFour();
			
			if (fourTicket != null) {
				currentCustomer.addPickFourTicket(fourTicket);
				allCustomerTickets.put(fourTicket.getTicketNumber(), currentCustomer);
			}
		}
	}
	
	/**
	 * Create and assign tickets for the number of tickets requested
	 * @param numPickFive
	 */
	private void buyPickFiveTickets(int numPickFive) {
		for (int i = 0;  i < numPickFive; i++) {
			LottoPickFiveTicket fiveTicket = pickFive.pickFive();
			
			if (fiveTicket != null) {
				currentCustomer.addPickFiveTicket(fiveTicket);
				allCustomerTickets.put(fiveTicket.getTicketNumber(), currentCustomer);
			}
		}
	}

	/**
	 * Returns true if there are tickets remaining.
	 * @return true
	 */
	public boolean ticketsRemaining() {
		if (pickThreeSoldOut || pickFourSoldOut || pickFiveSoldOut) {
			return true;
		}
		return false;
	}

	public String pickThreeRemaining() {
		return Integer.toString(pickThree.getRemaining());
	}
	
	public String pickFourRemaining() {
		return Integer.toString(pickFour.getRemaining());
	}
	
	public String pickFiveRemaining() {
		return Integer.toString(pickFive.getRemaining());
	}
	

	/**
	 * Complete the drawing for the lottery.
	 * Print the winner's name and ticket number.
	 */
	public void draw() {
		int pickThreeWinningNumber = pickThreeWinningNumber();
		LottoCustomer pickThreeWinner = allCustomerTickets.get(pickThreeWinningNumber);
		System.out.println("The Pick 3 winner is " + pickThreeWinner.getCustName() + " with ticket number " + pickThreeWinningNumber);
		
		int pickFourWinningNumber = pickFourWinningNumber();
		LottoCustomer pickFourWinner = allCustomerTickets.get(pickFourWinningNumber);
		System.out.println("The Pick 4 winner is " + pickFourWinner.getCustName() + " with ticket number " + pickFourWinningNumber);
		
		int pickFiveWinningNumber = pickFiveWinningNumber();
		LottoCustomer pickFiveWinner = allCustomerTickets.get(pickFiveWinningNumber);
		System.out.println("The Pick 5 winner is " + pickFiveWinner.getCustName() + " with ticket number " + pickFiveWinningNumber);
	}
	
	/**
	 * Pick the winning Pick 3 ticket number from the map of tickets.
	 * @return
	 */
	private int pickThreeWinningNumber() {
		int ticketPoolSize = pickThree.getAllPickThreeTickets().size();
		int pickRandom = ThreadLocalRandom.current().nextInt(0, ticketPoolSize);
		return pickThree.getAllPickThreeTickets().get(pickRandom);
	}
	
	/**
	 * Pick the winning Pick 4 ticket number from the map of all tickets.
	 * @return
	 */
	private int pickFourWinningNumber() {
		int ticketPoolSize = pickFour.getAllPickFourTickets().size();
		int pickRandom = ThreadLocalRandom.current().nextInt(0, ticketPoolSize);
		return pickFour.getAllPickFourTickets().get(pickRandom);
	}
	
	/**
	 * Pick the winning Pick 5 ticket number from the map of all tickets.
	 * @return
	 */
	private int pickFiveWinningNumber() {
		int ticketPoolSize = pickFive.getAllPickFiveTickets().size();
		int pickRandom = ThreadLocalRandom.current().nextInt(0, ticketPoolSize);
		return pickFive.getAllPickFiveTickets().get(pickRandom);
	}
	
	/**
	 * Print the report and statistics for the lottery drawing.
	 * - How many customers purchased tickets?
	 * - What type of tickets did each customer purchase?
	 * - Did customers attempt to purchase sold out ticket types?
	 */
	public void report() {
		System.out.println("Tickets were purchased by " + allCustomerTickets.size() + " customers.");
		System.out.println();
	}


	/**
	 * Print goodbye message.
	 * @throws InterruptedException
	 */
	public void goodBye() throws InterruptedException {
		System.out.println("The lottery is now complete. Thank you for playing.");
		System.out.println("The system will self-destruct in 5 seconds");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("5...");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("4...");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("3...");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("2...");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("1...");
		System.out.println("BOOOM!!");
		System.out.println("Just kidding :) Have a nice day!");
	}

}
