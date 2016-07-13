package challenge;

import java.util.ArrayList;

public class LottoPickFive extends LottoPicker {
	private int maxFive = LottoConstants.PICKFIVEMAX;
	private int fiveCount;
	private ArrayList<Integer> allPickFiveTickets;
	
	public LottoPickFive() {
		super();
		fiveCount = 1;
		allPickFiveTickets = new ArrayList<>();
	}
	
	/**
	 * Pick and set a Lotto Five Ticket and add to the total ticket count.
	 * @return
	 */
	public LottoPickFiveTicket pickFive() {
		LottoPickFiveTicket ticket = new LottoPickFiveTicket(fiveCount, LottoConstants.PICKFIVE);
		int newTicketNumber = picker(ticket, allPickFiveTickets);
		
		//If the number is 0 then return null so no ticket is assigned.
		if (newTicketNumber == 0) {
			return null;
		}
		
		ticket.setTicketNumber(newTicketNumber);
		allPickFiveTickets.add(newTicketNumber);
		
		fiveCount++;
		
		return ticket;
	}
	
	public int getRemaining() {
		return maxFive - fiveCount + 1;
	}
	
	public boolean hasRemaining() {
		return areTicketsRemaining(fiveCount, maxFive);
	}
	
	public ArrayList<Integer> getAllPickFiveTickets() {
		return allPickFiveTickets;
	}
}
