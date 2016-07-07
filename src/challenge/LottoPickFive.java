package challenge;

public class LottoPickFive extends LottoPicker {
	private int maxFive = LottoMachineConstants.PICKFIVEMAX;
	private int fiveCount = 1;
	
	public LottoPickFive() {
		super();
	}
	
	/**
	 * Pick and set a Lotto Five Ticket and add to the total ticket count.
	 * @return
	 */
	public LottoPickFiveTicket pickFive() {
		LottoPickFiveTicket ticket = new LottoPickFiveTicket(fiveCount);
		int newTicketNumber = picker(ticket);
		
		//If the number is 0 then return null so no ticket is assigned.
		if (newTicketNumber == 0) {
			return null;
		}
		
		LottoTicket.setTicketNumber(picker(ticket));
		
		fiveCount++;
		
		return ticket;
	}
	
	public int getRemaining() {
		return maxFive - fiveCount + 1;
	}
}
