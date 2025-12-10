package tui;

import model.*;

public class Main {
    public static void main(String[] args) {
        createTestData();
        new MenuUI();
    }

    public static void createTestData() {
        // Opret medarbejder
    	 Staff staff1 = new Staff("Joe", "Joe Street", "joe@vestbjerg.dk", "101010", "1", "Trælast");
         Staff staff2 = new Staff("Anna", "Anna Avenue", "anna@vestbjerg.dk", "303030", "2", "Byggevare");
         Staff staff3 = new Staff("Mark", "Mark Road", "mark@vestbjerg.dk", "404040", "3", "Murer");

         StaffContainer.getInstance().addStaff(staff1);
         StaffContainer.getInstance().addStaff(staff2);
         StaffContainer.getInstance().addStaff(staff3);

        // Opret kunde
        Customer customer1 = new Customer("Christian", "Christiansgade", "prodjody@gmail.com", "202020", 1, 15, CustomerStatus.Private);
        Customer customer2 = new Customer("Sofie", "Sofiegade", "sofie@gmail.com", "303030", 2, 10, CustomerStatus.Private);
        Customer customer3 = new Customer("Lars", "Larsvej", "lars@gmail.com", "404040", 3, 5, CustomerStatus.Private);

        CustomerContainer.getInstance().addCustomer(customer1);
        CustomerContainer.getInstance().addCustomer(customer2);
        CustomerContainer.getInstance().addCustomer(customer3);

        // Opret én lokation
        Location timber1 = new Location("Trælast");

        // Opret priser
        Price price1 = new Price(300);
        Price price2 = new Price(450);
        Price price3 = new Price(200);

        // Opret produkter på samme lokation
        Product product1 = new Product("001", price1, "45x220 Spær", null, timber1);
        Product product2 = new Product("002", price2, "45x450 lægter", null, timber1);
        Product product3 = new Product("003", price3, "11cmx20m murepap", null, timber1);

        // Opret inventory og tilknyt til produkter
        Inventory inv1 = new Inventory(timber1, product1, 5, 100, 25);
        Inventory inv2 = new Inventory(timber1, product2, 10, 200, 50);
        Inventory inv3 = new Inventory(timber1, product3, 15, 150, 30);

        product1.setInventory(inv1);
        product2.setInventory(inv2);
        product3.setInventory(inv3);

        // Tilføj produkter til containeren
        ProductContainer.getInstance().addProduct(product1);
        ProductContainer.getInstance().addProduct(product2);
        ProductContainer.getInstance().addProduct(product3);
    }
}
