package tui;

import java.util.ArrayList;
import java.util.Scanner;
import controller.OrderController;
import controller.ProductController;
import controller.StaffController;
import model.Customer;
import model.CustomerStatus;
import model.DeliveryStatus;
import model.Inventory;
import model.InventoryContainer;
import model.Order;
import model.OrderLine;
import model.OrderStatus;
import model.Product;
import model.Staff;
import controller.CustomerController;
import controller.InventoryController;

public class MenuUI {
	private OrderController orderCtrl;
	private ProductController productCtrl;
	private CustomerController customerCtrl;
	private StaffController staffCtrl;
	private InventoryController inventoryCtrl;
	private InventoryContainer inventoryCont = InventoryContainer.getInstance();
	
	private Scanner scanner;
	
	public MenuUI() {
		orderCtrl = new OrderController();
		productCtrl = new ProductController();
		customerCtrl = new CustomerController();
		staffCtrl = new StaffController();
		inventoryCtrl = new InventoryController();
		
		scanner = new Scanner(System.in);
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
	            		stockMenu();
	            		break;
	            case "0":
	                done = true;
	                break;
	            default:
	                System.out.println("Ugyldigt valg.");
	        }
	    }
	    System.out.println("Lukker system...");
	    scanner.close();
	}
	
	private void stockMenu() {
		boolean created = false;
		System.out.println("*** Lagerstyring ***");
		System.out.println("Nuværende lagerstatus:");
		
        System.out.println("-----------------------------------------");
        System.out.printf("%-30s %-10s %-10s\n", "Produkt", "Lager", "Pris pr stk.");
        System.out.println("-----------------------------------------");

        ArrayList<Product> products = productCtrl.getAllProducts();

        if (products != null && !products.isEmpty()) {
            for (Product p : products) {
                String pName = (p.getInfo() != null) ? p.getInfo() : "Ukendt";
                
                int stock = (p.getInventory() != null) ? p.getInventory().getCurrStock() : 0;
                
                double price = (p.getPrice() != null) ? p.getPrice().getPrice() : 0.0;
                
                System.out.printf("%-30s %-10d %-10.2f\n", 
                    (pName.length() > 30 ? pName.substring(0, 27) + "..." : pName), 
                    stock, 
                    price 
                );
            }
        } else {
            System.out.println("   (Ingen produkter i systemet)");
        }
        System.out.println("-----------------------------------------");
		
        String checkStock = prompt("Vil du køre automatisk lagertjek og sende mails til manager? (Ja/Nej)");
        if (checkStock.equalsIgnoreCase("ja")) {
            System.out.println("Kører tjek...");
            stockWarning();
        } else {
            System.out.println("Returnerer til hovedmenu.");
        }
	}
	
 	public void stockWarning() {
        ArrayList<Inventory> lowStockItems = inventoryCont.getLowStockInventories();
        
        for (Inventory inv : lowStockItems) {
            sendEmailToManager(inv);
        }
    }
 	
 	private void sendEmailToManager(Inventory inventory) {
        Product p = inventory.getProduct();
        String productName = (p != null) ? p.getInfo() : "Ukendt Produkt";
        
        System.out.println("--- EMAIL TIL ANDERS OLESEN ---");
        System.out.println("Emne: GENBESTIL ADVARSEL!");
        System.out.println("Produkt: " + productName);
        System.out.println("Nuværende lager: " + inventory.getCurrStock());
        System.out.println("Minimumslager antal: " + inventory.getMinStock());
        System.out.println("Dette produkt er under minimumsbeholdning!");
        System.out.println("------------------------");
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
                    
                    int quantity = 0;
                    boolean validInput = false;

                    while (!validInput) {
                        String qtyString = prompt("Indtast antal (Lager: " + product.getInventory().getCurrStock() + "):");

                        try {
                            quantity = Integer.parseInt(qtyString);

                            if (quantity <= 0) {
                                System.out.println("Antal skal være mindst 1.");
                            }

                            else if (quantity > product.getInventory().getCurrStock()) {
                                System.out.println("Fejl: Der er kun " + product.getInventory().getCurrStock() + " stk. på lager.");
                            }
                            else {
                                validInput = true;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Fejl: Ugyldigt input. Indtast venligst et tal.");
                        }
                    }

                    orderCtrl.addProduct(currOrder, product, quantity);
                    System.out.println("Produkt tilføjet!");

                } else {
                    System.out.println("Produkt ikke fundet.");
                }
            }
        }
        
        if (currOrder.getOrderLine().isEmpty()) {
            System.out.println("Intet produkt tilføjet. Ordren annulleres.");
            return; 
        }
        
        //Step 4&5 find og tilføj customer
        while (true) {
            String inputVal = prompt("Find kunden ved at indtaste CPR/CVR (eller tryk Enter for at springe over):");

            if (inputVal.isEmpty()) {
                System.out.println("Ingen kunde valgt. Fortsætter...");
                break; 
            }

            try {
                int customerId = Integer.parseInt(inputVal);
                Customer customer = customerCtrl.findCustomer(customerId);

                if (customer != null) {
                    String add = prompt("Vil du tilføje kunden til ordren? Ja/Nej:");
                    if (add.equalsIgnoreCase("ja")) {
                        currOrder.setCustomer(customer);
                        System.out.println("Kunde tilføjet.");
                        printCustomerInfo(customer);
                    }
                    break; 
                } else {
                    System.out.println("Ingen kunde fundet med ID: " + customerId + ". Prøv igen.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Fejl: ID skal være et tal, ikke bogstaver. Prøv igen.");
            }
        }
		orderCtrl.changeOrderStatus(currOrder, OrderStatus.offer);
		System.out.println("Order status ændres til offer, kunden har 7 dage til at acceptere");
		System.out.println("Offer er blevet accepteret af kunden.");
		
		// Step 7
		orderCtrl.changeOrderStatus(currOrder, OrderStatus.order);
		String deliveryOption = prompt("Skal denne ordre leveres? Ja/Nej"); 
		
		// Step 8 og 9
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
		created = true;
	}
}
	
	public void printInvoice(Order order) {
	    System.out.println("\n================ FAKTURA ================");
	    System.out.println("Ordre ID:    " + order.getOrderId());
	    System.out.println("Dato:        " + order.getDate()); 
	    System.out.println("Status:      " + order.getOrderStatus());
	    System.out.println("Levering:    " + order.getDeliveryStatus());
	    
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
	    if (order.getDeliveryStatus() == DeliveryStatus.delivered) {
	        System.out.printf("Levering (Gebyr):             %10.2f\n", 500.00);
	    }

	    System.out.printf("TOTAL BELØB:                  %10.2f\n", order.getTotal());
	    System.out.println("=========================================\n");
    }

	public Customer printCustomerInfo(Customer customer) {
	    System.out.println("****** Kunde Info ******");
	    System.out.println("Navn:          " + customer.getName());
	    System.out.println("Adresse:       " + customer.getAddress());
	    System.out.println("Email:         " + customer.getEmail());
	    System.out.println("Telefon:       " + customer.getPhone());
	    System.out.println("Kunde ID:      " + customer.getCustomerId());
	    System.out.println("Rabat:         " + customer.getPersonalDiscount() + " %");
	    System.out.println("Status:        " + customer.getCustomerStatus());
	    System.out.println("************************");
	    
	    return customer;
	}
	
	private String prompt(String message) {
	    System.out.println(message);
	    System.out.print("> ");
	    return scanner.nextLine();
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
	    sb.append("(2) Lagerstyring\n");
	    sb.append("(0) Luk system\n");
	    return sb.toString();
	}

	public String getUserInput() {
		System.out.print("> ");
		return scanner.nextLine();
	}
}
