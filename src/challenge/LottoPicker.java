package challenge;

public abstract class LottoPicker {
	RandomNumbers randNum;
	
	public LottoPicker() {
		 randNum = new RandomNumbers();
	}
	
	/**
	 * Pick a lottery ticket for the specified type.
	 * @return
	 */
	public int picker(LottoTicket ticket) {
		if (ticket instanceof LottoPickThreeTicket) {
			return randNum.pickThree();
		}
		
		if (ticket instanceof LottoPickFourTicket) {
			return randNum.pickFour();
		}
		
		if (ticket instanceof LottoPickFiveTicket) {
			return randNum.pickFive();
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