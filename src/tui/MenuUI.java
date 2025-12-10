package tui;

import java.util.Scanner;
import controller.OrderController;
import controller.ProductController;
import controller.StaffController;
import model.Customer;
import model.CustomerStatus;
import model.Order;
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
	            case "2":
	            	createStaffMenu(); 
	                break;
	            case "3":
	                createCustomerMenu();   
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
            if (!retryPrompt()) break; 
            continue;
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
       //Order order = orderCtrl.createOrder(staff, null, staff, null, null, null, 0, staffId, staffId);
	}
	}
	
	private void createStaffMenu() {
	    System.out.println("*** Opret Medarbejder ***");
	    
	    String name = prompt("Navn:");
	    String address = prompt("Adresse:");
	    String email = prompt("Email:");
	    String phone = prompt("Telefon:");
	    
	    String staffId = prompt("Medarbejder ID:");
	    String department = prompt("Afdeling:");
	    
	    boolean success = staffCtrl.createStaff(name, address, email, phone, staffId, department);
	    
	    if (success) {
	        System.out.println("Medarbejder oprettet: " + name);
	    } else {
	        System.out.println("Fejl: Kunne ikke oprette medarbejder.");
	    }
	}
	
	private void createCustomerMenu() {
	    System.out.println("*** Opret Kunde ***");

	    String name = prompt("Navn:");
	    String address = prompt("Adresse:");
	    String email = prompt("Email:");
	    String phone = prompt("Telefon:");
	 
	    String idStr = prompt("Kunde ID (CPR eller CVR):");
	    int id = Integer.parseInt(idStr);
	    
	    double discount = 0.0;
        boolean validDiscount = false;
        
        while (!validDiscount) {
            String input = prompt("Personlig rabat (%) (Max 20):");
            discount = Double.parseDouble(input);
            
            if (discount <= 20.0) {
                validDiscount = true;
            } else {
                System.out.println("Fejl: Rabatten må højst være 20%. Prøv igen.");
            }
        }
        
        CustomerStatus selectedStatus = null;
        
        while (selectedStatus == null) {
            System.out.println("Vælg kundestatus:");
            System.out.println("1. Private");
            System.out.println("2. Business");
            
            String choice = prompt("Indtast valg (1 eller 2):");
            
            switch (choice) {
                case "1":
                    selectedStatus = CustomerStatus.Private;
                    break;
                case "2":
                    selectedStatus = CustomerStatus.Business;
                    break;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
                    break;
            }
        }
        
	    Customer customer = customerCtrl.createCustomer(name, address, email, phone, id, discount, selectedStatus);
	    
	    if (customer != null) {
	        System.out.println("Kunde oprettet: " + customer.getName());
	    } else {
	        System.out.println("Fejl: Kunne ikke oprette kunde");
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
	    StringBuilder sb = new StringBuilder();
	    sb.append("****** Vestbjerg Byggecenter ******\n");
	    sb.append("(1) Opret ordre\n");
	    sb.append("(2) Opret medarbejder\n");      // Ny
	    sb.append("(3) Opret kunde\n"); // Ny
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
