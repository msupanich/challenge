package challenge;

public class LottoPickFour extends LottoPicker {
	private int maxFour = LottoMachineConstants.PICKFOURMAX;
	private int fourCount = 1;
	
	public LottoPickFour() {
		super();
	}
	
	/**
	 * Pick and set a Lotto Four Ticket and add to the total ticket count.
	 * @return
	 */
	public LottoPickFourTicket pickFour () {
		LottoPickFourTicket ticket = new LottoPickFourTicket(fourCount);
		int newTicketNumber = picker(ticket);
		
		//If the number is 0 then return null so no ticket is assigned.
		if (newTicketNumber == 0) {
			return null;
		}
		
		LottoTicket.setTicketNumber(picker(ticket));
		
		fourCount++;
		
		return ticket;
	}
}
