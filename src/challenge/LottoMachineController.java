package challenge;

import java.util.ArrayList;
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
		while (totalTickets > 0) {
			if (numPickThree > 0 && !pickThreeSoldOut) {
				validatePickThree(numPickThree);
			}
			if (numPickFour > 0 && !pickFourSoldOut) {
				validatePickFour(numPickFour);
			}
			if (numPickFive > 0 && !pickFiveSoldOut) {
				validatePickFive(numPickFive);
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
					int newTicketType = ThreadLocalRandom.current().nextInt(0, LottoConstants.NUMTICKETTYPES);
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

	/**
	 * Complete the drawing for the lottery.
	 */
	public void draw() {
		
	}


}
