package challenge;

public class LottoPickFour extends LottoPicker {
	private int maxFour = LottoConstants.PICKFOURMAX;
	private int fourCount = 1;
	
	public LottoPickFour() {
		super();
	}
	
	/**
	 * Pick and set a Lotto Four Ticket and add to the total ticket count.
	 * @return
	 */
	public LottoPickFourTicket pickFour () {
		LottoPickFourTicket ticket = new LottoPickFourTicket(fourCount, LottoConstants.PICKFOUR);
		int newTicketNumber = picker(ticket);
		
		//If the number is 0 then return null so no ticket is assigned.
		if (newTicketNumber == 0) {
			return null;
		}
		
		ticket.setTicketNumber(newTicketNumber);
		
		fourCount++;
		
		return ticket;
	}
	
	public int getRemaining() {
		return maxFour - fourCount + 1;
	}
	
	public boolean hasRemaining() {
		return areTicketsRemaining(fourCount, maxFour);
	}
}
