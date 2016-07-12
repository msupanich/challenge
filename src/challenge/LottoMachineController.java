package challenge;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The main Lottery controller. 
 * @author supanichm
 * @since  7/12/2016
 *
 */
public class LottoMachineController {
	int currentCustId;
	ArrayList<LottoCustomer> customers;
	boolean soldOutAttempt;
	LottoCustomer currentCustomer;
	
	public LottoMachineController() {
		customers = new ArrayList<LottoCustomer>();
		currentCustId = 1;
		soldOutAttempt = false;
	}
	
	/**
	 * Set up a new customer with an ID.
	 * @param name
	 */
	public void newCustomer(String name) {
		currentCustomer = new LottoCustomer(name, currentCustId);
		currentCustId++;
	}
	
	/**
	 * Assign tickets for each type for the given amounts.
	 * @param numPickThree
	 * @param numPickFour
	 * @param numPickFive
	 */
	public boolean assignTickets(int numPickThree, int numPickFour, int numPickFive) {
		
		return false;
	}
	
	/**
	 * Check availability of desired tickets. Reassign to
	 * @param numPickThree
	 * @param numPickFour
	 * @param numPickFive
	 * @return
	 */
	public boolean checkTypeAvailability(int numPickThree, int numPickFour, int numPickFive) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void Main (String[] args) { 

		LottoPickThree pickThree = new LottoPickThree();
		LottoPickFour pickFour = new LottoPickFour();
		LottoPickFive pickFive = new LottoPickFive();

		
		while (true) {
			String name = args[0];

			int pickThreeLeft = pickThree.getRemaining();
			int pickFourLeft = pickFour.getRemaining();
			int pickFiveLeft = pickFive.getRemaining();
			
			int ticketsToPurchase = numPickThree + numPickFour + numPickFive;
			
			while (ticketsToPurchase > 0) {
				
				if ((numPickThree - pickThreeLeft) < 0) {
					ticketsToPurchase -= numPickThree;
				}
				if ((numPickFour - pickFourLeft) < 0) {
					ticketsToPurchase -= numPickFour;
				}
				if ((numPickFive - pickFiveLeft) < 0) {
					ticketsToPurchase -= numPickFive;
				}
				
				
				
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
	}


	public boolean ticketsRemaining() {
		// TODO Auto-generated method stub
		return false;
	}


}
