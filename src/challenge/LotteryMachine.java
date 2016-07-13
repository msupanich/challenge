package challenge;



public class LotteryMachine {

	public static void main (String[] args) throws InterruptedException {
		LottoMachineController lottoController = new LottoMachineController();
		

		while(true) {
			printPurchasePrompt(lottoController);
			
			lottoController.newCustomer(args[0]);
			lottoController.assignTickets(Integer.parseInt(args[1]), Integer.parseInt(args[2]), 
										  Integer.parseInt(args[3]));
			
			//Check if there are any tickets remaining. If not, stop the loop.
			if (!lottoController.ticketsRemaining()) {
				System.out.println("No tickets remaining. Beginning the Lottery Draw...");
				break;
			}
		}
		
		lottoController.draw();
		lottoController.report();
		lottoController.goodBye();
	}
	
	/**
	 * Print the message prompt for the user to select tickets.
	 * @param lottoController
	 */
	public static void printPurchasePrompt(LottoMachineController lottoController) {
		System.out.println("Please enter your name and desired ticket amounts in the following format: ");
		System.out.println("Name " + LottoConstants.PICKTHREE + " " + LottoConstants.PICKFOUR + " " + LottoConstants.PICKFIVE);
		System.out.println("There are " + lottoController.pickThreeRemaining() + " Pick 3 Tickets, "
							+ lottoController.pickFourRemaining() + " Pick 4 Tickets and " 
							+ lottoController.pickFiveRemaining() + "Pick 5 Tickets for purchase.");
		System.out.println("You may choose between 1 and 5 tickets TOTAL.");
	}
}
