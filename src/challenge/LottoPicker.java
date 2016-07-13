package challenge;

import java.util.ArrayList;

public abstract class LottoPicker {
	RandomNumbers randNum;
	
	public LottoPicker() {
		 randNum = new RandomNumbers();
	}
	
	/**
	 * Pick a lottery ticket for the specified type.
	 * Ticket number cannot be duplicated.
	 * @return
	 */
	public int picker(LottoTicket ticket, ArrayList<Integer> allTickets) {
		if (ticket instanceof LottoPickThreeTicket) {
			int number = randNum.pickThree();
			if (allTickets.contains(number)) {
				picker(ticket, allTickets);
			}
			
			return number;
			
		}
		
		if (ticket instanceof LottoPickFourTicket) {
			int number = randNum.pickFour();
			if (allTickets.contains(number)) {
				picker(ticket, allTickets);
			}
			
			return number;
		}
		
		if (ticket instanceof LottoPickFiveTicket) {
			int number = randNum.pickFive();
			if (allTickets.contains(number)) {
				picker(ticket, allTickets);
			}
			
			return number;
		}
		
		return 0;
	}
	
	/**
	 * Compare the current total tickets given to the max given.
	 * @return
	 */
	public boolean areTicketsRemaining(int current, int max) {
		return (current < max) ? true : false;
	}
}