package challenge;

public class LottoPickThree extends LottoPicker {
	private int maxThree = LottoMachineConstants.PICKTHREEMAX;
	private int threeCount = 1;
	
	public LottoPickThree () {
		super();
	}
	
	/**
	 * Pick and set a Lotto Three Ticket and add to the total ticket count.
	 * @return
	 */
	public LottoPickThreeTicket pickThree () {
		LottoPickThreeTicket ticket = new LottoPickThreeTicket(threeCount);
		int newTicketNumber = picker(ticket);
		
		//If the number is 0 then return null so no ticket is assigned.
		if (newTicketNumber == 0) {
			return null;
		}
		
		LottoTicket.setTicketNumber(picker(ticket));
		
		threeCount++;
		
		return ticket;
	}
	
	public boolean checkRemaining() {
		return areTicketsRemaining(threeCount, maxThree);
	}
}
