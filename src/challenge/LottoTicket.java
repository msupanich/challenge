package challenge;

public abstract class LottoTicket {
	private static int ticketNumber;
	private static int ticketId;
	private static String ticketType;
	
	public LottoTicket (int ticketId, String ticketType) {
		LottoTicket.ticketId = ticketId;
		LottoTicket.ticketType = ticketType;
	}

	/**
	 * @return the ticketNumber
	 */
	public int getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * @param ticketNumber the ticketNumber to set
	 */
	public void setTicketNumber(int ticketNumber) {
		LottoTicket.ticketNumber = ticketNumber;
	}

	/**
	 * @return the ticketId
	 */
	public int getTicketId() {
		return ticketId;
	}

	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(int ticketId) {
		LottoTicket.ticketId = ticketId;
	}
	
	
}
