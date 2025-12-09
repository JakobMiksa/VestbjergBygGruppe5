package tui;

import java.util.Scanner;

import controller.OrderController;
import controller.ProductController;
import controller.StaffController;
import model.Order;
import model.Product;
import model.Staff;
import controller.CustomerController;

public class MenuUI {
	private OrderController orderCtrl;
	private ProductController productCtrl;
	private CustomerController customerCtrl;
	private StaffController staffCtrl;
	private Order currOrder;
	
	public MenuUI() {
		orderCtrl = new OrderController();
		productCtrl = new ProductController();
		customerCtrl = new CustomerController();
		staffCtrl = new StaffController();
		createMenu();
	}

	public void createMenu() {
		boolean done = false;

		while (!done) {
			System.out.println(options());
			String choice = getUserInput();
			switch (choice) {
			case "1":
				createOrderMenu();
				break;
			case "0":
				done = true;
				break;
			default:
				System.out.println("Ugyldigt valg, prøv igen.");
				break;
			}
		}
		System.out.println("Lukker system...");
		System.out.println("Farvel");
	}

	private void createOrderMenu() {
		boolean created = false;
        System.out.println("*** Opretter ordre ***");
		while (!created) {
		// Step 1: Medarbejder logger ind i systemet
        String staffId = prompt("Indtast medarbejder id:");
        Staff staff = staffCtrl.findStaff(staffId);
        
        if (staff == null) {
            System.out.println("Ukendt medarbejder");
            if (!retryPrompt()) break; 
            continue;
        }
        
        currOrder.setStaff(staff); //Assigner Staff med ordren
        
        // 2: Find product
        String SKU = prompt("Angiv SKU på produkt der skal tilføjes:");
        System.out.println("Ledige produkter: ");
        Product product = productCtrl.findProduct(SKU);
        
        
       //Order order = orderCtrl.createOrder(staff, null, staff, null, null, null, 0, staffId, staffId);
	}
}
	
	private String prompt(String message) {
	    System.out.println(message);
	    System.out.print("> ");
	    return new Scanner(System.in).nextLine();
	}
	
	private boolean retryPrompt() {
	    System.out.println("Ønsker du at prøve igen Y N");
	    String in = getUserInput().toLowerCase();
	    return in.equals("y");
	}
	
	public String options() {
		String s = "****** Vestbjerg Byggecenter ******\n" + "(1) Opret ordre\n"
				+ "(0) Luk system\n";
		
		return s;
	}

	public String getUserInput() {
		System.out.print("> ");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();
		return userInput;
	}
}
