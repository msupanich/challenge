package challenge;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LottoMachine {

	public static void Main (String[] args) { 
		int currentCustId = 0;
		ArrayList<LottoCustomer> customers = new ArrayList<LottoCustomer>();
		LottoPickThree pickThree = new LottoPickThree();
		LottoPickFour pickFour = new LottoPickFour();
		LottoPickFive pickFive = new LottoPickFive();
		boolean soldOutAttempt = false;
		
		while (true) {
			String name = args[0];
			int numPickThree = Integer.parseInt(args[1]);
			int numPickFour = Integer.parseInt(args[2]);
			int numPickFive = Integer.parseInt(args[3]);
			int pickThreeLeft = pickThree.getRemaining();
			int pickFourLeft = pickFour.getRemaining();
			int pickFiveLeft = pickFive.getRemaining();
			
			int ticketsToPurchase = numPickThree + numPickFour + numPickFive;
			
			while (ticketsToPurchase > 0) {
				
				if ((numPickThree - pickThreeLeft) < 0) {
					ticketsToPurchase -= numPickThree;
				}
				if ((numPickFour - pickFourLeft) < 0) {
					ticketsToPurchase -= numPickFour;
				}
				if ((numPickFive - pickFiveLeft) < 0) {
					ticketsToPurchase -= numPickFive;
				}
				
				
				
			}

			LottoCustomer newCustomer = new LottoCustomer(name, currentCustId);
			
			for (int i = 0;  i < numPickThree; i++) {
				if (!pickThree.checkRemaining()) {
					int newTicketType = ThreadLocalRandom.current().nextInt(0, LottoMachineConstants.NUMTICKETTYPES);
					int pickThreeRemaining = numPickThree - i;
					
					switch (newTicketType) {
					case 1: numPickFour += pickThreeRemaining;
					case 2: numPickFive += pickThreeRemaining;
					}
				}
				LottoPickThreeTicket threeTicket = pickThree.pickThree();
				
				if (threeTicket != null) {
					newCustomer.addPickThreeTicket(threeTicket);
				}
			}
			
			for (int i = 0;  i < numPickFour; i++) {
				LottoPickFourTicket fourTicket = pickFour.pickFour();
				
				if (fourTicket != null) {
					newCustomer.addPickFourTicket(fourTicket);
				}
			}
			
			for (int i = 0;  i < numPickFive; i++) {
				LottoPickFiveTicket fiveTicket = pickFive.pickFive();
				
				if (fiveTicket != null) {
					newCustomer.addPickFiveTicket(fiveTicket);
				}
			}
			
			customers.add(newCustomer);
		}
	}
}
