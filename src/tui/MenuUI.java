package tui;

import java.util.Scanner;
import controller.OrderController;
import controller.ProductController;
import controller.StaffController;
import model.Customer;
import model.CustomerStatus;
import model.DeliveryStatus;
import model.Order;
import model.OrderLine;
import model.OrderStatus;
import model.Product;
import model.Staff;
import controller.CustomerController;

public class MenuUI {
	private OrderController orderCtrl;
	private ProductController productCtrl;
	private CustomerController customerCtrl;
	private StaffController staffCtrl;
	
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
	                System.out.println("Ugyldigt valg.");
	        }
	    }
	    System.out.println("Lukker system...");
	}
	
	private void createOrderMenu() {
		boolean created = false;
        System.out.println("*** Opretter ordre ***");
		while (!created) {
		// Step 0: Medarbejder logger ind i systemet
        String staffId = prompt("Indtast medarbejder id:");
        Staff staff = staffCtrl.findStaff(staffId);
        
        if (staff == null) {
            System.out.println("Ukendt medarbejder");
            return;
        }
        
        //Step 1: Opret ordren med Staff
        Order currOrder = orderCtrl.createOrder(staff);
        System.out.println("Ordre oprettet for medarbejder: " + staff.getName());
        
        //Step 2: Find product/Add product loop process
        boolean running = true;
        while (running) {
            String sku = prompt("Indtast SKU (eller tryk enter for at stoppe):");
            
            if (sku.isEmpty()) {
                running = false; 
            } else {
                Product product = productCtrl.findProduct(sku);
                if (product != null) {
                    String qtyString = prompt("Indtast antal:");
                    int quantity = Integer.parseInt(qtyString); 
                    
                    orderCtrl.addProduct(currOrder, product, quantity);
                    System.out.println("Produkt tilføjet!");
                } else {
                    System.out.println("Produkt ikke fundet.");
                }
            }
        }
        // Step 4 og 5 i use case find/set customer
        String customerId = prompt("Find kunden ved at indtaste CPR/CVR:");
        int customerIdStr = Integer.parseInt(customerId);
        Customer customer = customerCtrl.findCustomer(customerIdStr);

        if (customer != null) {
            	String add = prompt("Vil du tilføje kunden til ordren? Ja/Nej:");
            
            	if (add.equalsIgnoreCase("ja")) {
                	currOrder.setCustomer(customer);
                	System.out.println("Kunde tilføjet til ordren /n");
               		customerCtrl.printCustomerInfo(customer);
            		} 
        	} else {
        		  	System.out.println("Kunde blev ikke tilføjet");
        		  	break;
        		}
		//Step 6 i use case, change order status to offer
		orderCtrl.changeOrderStatus(currOrder, OrderStatus.offer);
		System.out.println("Order status ændres til offer, kunden har 7 dage til at acceptere");
		System.out.println("Offer er blevet accepteret af kunden.");
		orderCtrl.changeOrderStatus(currOrder, OrderStatus.order);
		// Would you like it delivered? y n
		String deliveryOption = prompt("Skal denne ordre leveres? Ja/Nej");
		// yes = 
		if (deliveryOption.equalsIgnoreCase("ja")) {
			System.out.println("Leveringsomkostninger beregnes...");
			orderCtrl.changeDeliveryStatus(currOrder, DeliveryStatus.delivered);
			orderCtrl.finishOrder(currOrder);
			printInvoice(currOrder);
		} else {
			System.out.println("Der er ingen leveringsomkostninger.. Ordre færdiggøres.");
			orderCtrl.changeDeliveryStatus(currOrder, DeliveryStatus.inStore);
			orderCtrl.finishOrder(currOrder);
			printInvoice(currOrder);
			break;
		}
	}	
}
	
	public void printInvoice(Order order) {
	    System.out.println("\n================ FAKTURA ================");
	    System.out.println("Ordre ID:    " + order.getOrderId());
	    System.out.println("Dato:        " + order.getDate()); 
	    
	    if (order.getCustomer() != null) {
	        System.out.println("Kunde:       " + order.getCustomer().getName());
	        System.out.println("Adresse:     " + order.getCustomer().getAddress());
	    } else {
	        System.out.println("Kunde:       [Ingen kunde tildelt]");
	    }
	    
	    if (order.getStaff() != null) {
	        System.out.println("Sælger:      " + order.getStaff().getName());
	    }

	    System.out.println("-----------------------------------------");
	    // %-20s betyder venstrejusteret tekst på 20 pladser
	    System.out.printf("%-20s %-10s %-10s\n", "Produkt", "Antal", "Pris");
	    System.out.println("-----------------------------------------");

	    if (order.getOrderLine() != null) {
	        for (OrderLine line : order.getOrderLine()) {
	            Product p = line.getProduct();
	            String pName = (p != null) ? p.getInfo() : "Ukendt"; 
	            
	            System.out.printf("%-20s %-10d %-10.2f\n", 
	                (pName.length() > 20 ? pName.substring(0, 17) + "..." : pName), 
	                line.getQuantity(), 
	                line.getUnitPrice() 
	            );
	        }
	    }

	    System.out.println("-----------------------------------------");
	    
	    order.recalculateTotal(); 
	    System.out.printf("TOTAL BELØB:                  %-10.2f\n", order.getTotal());
	    System.out.println("=========================================\n");
	

        System.out.println("-----------------------------------------");
        // Ensure total is up to date
        order.recalculateTotal(); 
        System.out.printf("TOTAL AMOUNT:                 %-10.2f\n", order.getTotal());
        System.out.println("=========================================\n");
    }

		
	private String prompt(String message) {
	    System.out.println(message);
	    System.out.print("> ");
	    return new Scanner(System.in).nextLine();
	}
	
	private boolean retryPrompt() {
	    System.out.println("Ønsker du at prøve igen Ja/Nej");
	    String in = getUserInput().toLowerCase();
	    return in.equals("ja");
	}
	
	public String options() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("****** Vestbjerg Byggecenter ******\n");
	    sb.append("(1) Opret ordre\n");
	    sb.append("(0) Luk system\n");
	    return sb.toString();
	}

	public String getUserInput() {
		System.out.print("> ");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();
		return userInput;
	}
}
