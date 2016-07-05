package challenge;

import java.util.List;

public class LottoCustomer {
	private String custName;
	private int custId;
	private List<LottoPickThreeTicket> pickThreeTickets;
	private List<LottoPickFourTicket> pickFourTickets;
	private List<LottoPickFiveTicket> pickFiveTickets;
	
	public LottoCustomer (String custName, int custId) {
		this.custName = custName;
		this.custId = custId;
	}

	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * @return the custID
	 */
	public int getCustID() {
		return custId;
	}

	/**
	 * @param custID the custID to set
	 */
	public void setCustID(int custID) {
		this.custId = custID;
	}

	/**
	 * @return the pickThreeTickets
	 */
	public List<LottoPickThreeTicket> getPickThreeTickets() {
		return pickThreeTickets;
	}

	/**
	 * @param pickThreeTickets the pickThreeTickets to set
	 */
	public void setPickThreeTickets(List<LottoPickThreeTicket> pickThreeTickets) {
		this.pickThreeTickets = pickThreeTickets;
	}
	
	/**
	 * Add a pick three ticket to the pick three list.
	 * @param pickThreeTicket
	 */
	public void addPickThreeTicket(LottoPickThreeTicket pickThreeTicket) {
		pickThreeTickets.add(pickThreeTicket);
	}

	/**
	 * @return the pickFourTickets
	 */
	public List<LottoPickFourTicket> getPickFourTickets() {
		return pickFourTickets;
	}

	/**
	 * @param pickFourTickets the pickFourTickets to set
	 */
	public void setPickFourTickets(List<LottoPickFourTicket> pickFourTickets) {
		this.pickFourTickets = pickFourTickets;
	}
	
	/**
	 * Add a pick four ticket to the pick four list.
	 * @param pickFourTicket
	 */
	public void addPickFourTicket(LottoPickFourTicket pickFourTicket) {
		pickFourTickets.add(pickFourTicket);
	}

	/**
	 * @return the pickFiveTickets
	 */
	public List<LottoPickFiveTicket> getPickFiveTickets() {
		return pickFiveTickets;
	}

	/**
	 * @param pickTFiveTickets the pickTFiveTickets to set
	 */
	public void setPickTFiveTickets(List<LottoPickFiveTicket> pickFiveTickets) {
		this.pickFiveTickets = pickFiveTickets;
	}
	
	/**
	 * Add a pick five ticket to the pick five list.
	 * @param pickFiveTicket
	 */
	public void addPickFiveTicket(LottoPickFiveTicket pickFiveTicket) {
		pickFiveTickets.add(pickFiveTicket);
	}
}
