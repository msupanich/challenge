package challenge;

public class LottoPickFive extends LottoPicker {
	private int maxFive = LottoConstants.PICKFIVEMAX;
	private int fiveCount = 1;
	
	public LottoPickFive() {
		super();
	}
	
	/**
	 * Pick and set a Lotto Five Ticket and add to the total ticket count.
	 * @return
	 */
	public LottoPickFiveTicket pickFive() {
		LottoPickFiveTicket ticket = new LottoPickFiveTicket(fiveCount, LottoConstants.PICKFIVE);
		int newTicketNumber = picker(ticket);
		
		//If the number is 0 then return null so no ticket is assigned.
		if (newTicketNumber == 0) {
			return null;
		}
		
		ticket.setTicketNumber(newTicketNumber);
		
		fiveCount++;
		
		return ticket;
	}
	
	public int getRemaining() {
		return maxFive - fiveCount + 1;
	}
	
	public boolean hasRemaining() {
		return areTicketsRemaining(fiveCount, maxFive);
	}
}
