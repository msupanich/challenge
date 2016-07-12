package challenge;

public class LotteryMachine {

	public static void Main (String[] args) {
		LottoMachineController lottoController = new LottoMachineController();
		

		while(true) {
			System.out.println("Please enter your name and desired ticket amounts in the following format: ");
			System.out.println("Name " + LottoConstants.PICKTHREE + " " + LottoConstants.PICKFOUR + " " + LottoConstants.PICKFIVE);
			System.out.println("There are " + lottoController.pickThreeRemaining() + " Pick 3 Tickets, "
								+ lottoController.pickFourRemaining() + " Pick 4 Tickets and " + lottoController.pickFiveRemaining() "Pick 5 Tickets for purchase.");	
			
			lottoController.newCustomer(args[0]);
			boolean purchaseSuccessful = lottoController.assignTickets(Integer.parseInt(args[1]), Integer.parseInt(args[2]), 
										  Integer.parseInt(args[3]));
			
			if (!purchaseSuccessful) {
				System.out.println("Unable to purchase tickets of type(s) ");
				System.out.println("Please try again.");
			}
			
			//Check if there are any tickets remaining. If not, stop the loop.
			if (!lottoController.ticketsRemaining()) {
				System.out.println("No tickets remaining. Beginning the Lottery Draw...");
				break;
			}
		}
	}
}
