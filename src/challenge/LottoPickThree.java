package challenge;

import java.util.ArrayList;

public class LottoPickThree extends LottoPicker {
	private int maxThree = LottoConstants.PICKTHREEMAX;
	private int threeCount;
	private ArrayList<Integer> allPickThreeTickets;
	
	public LottoPickThree () {
		super();
		threeCount = 1;
		allPickThreeTickets = new ArrayList<>();
	}
	
	/**
	 * Pick and set a Lotto Three Ticket and add to the total ticket count.
	 * @return
	 */
	public LottoPickThreeTicket pickThree () {
		LottoPickThreeTicket ticket = new LottoPickThreeTicket(threeCount, LottoConstants.PICKTHREE);
		int newTicketNumber = picker(ticket, allPickThreeTickets);
		
		//If the number is 0 then return null so no ticket is assigned.
		if (newTicketNumber == 0) {
			return null;
		}
		
		ticket.setTicketNumber(newTicketNumber);
		allPickThreeTickets.add(newTicketNumber);
		
		threeCount++;
		
		return ticket;
	}
	
	public int getRemaining() {
		return maxThree - threeCount + 1;
	}
	
	public boolean hasRemaining() {
		return areTicketsRemaining(threeCount, maxThree);
	}
	
	public ArrayList<Integer> getAllPickThreeTickets() {
		return allPickThreeTickets;
	}
}
