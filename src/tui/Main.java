package tui;

import model.*; // 

public class Main {
   // private static CustomerController customerCtrl;
    public static void main(String[] args) {
        createTestData();

        new MenuUI();
    }
    
    public static void createTestData() {
    		 Staff staff1 = new Staff("Joe", "Joe Street", "joe@vestbjerg.dk", "101010", "1", "Trælast");
         StaffContainer.getInstance().addStaff(staff1);

         Customer customer1 = new Customer("Christian", "Christiansgade", "prodjody@gmail.com", "202020", 1, 15, CustomerStatus.Private);
         CustomerContainer.getInstance().addCustomer(customer1); 
         
         Location timber1 = new Location("Trælast");
         Price price1 = new Price(300);
         
         Product product1 = new Product("001", price1, "45x220 Spær: Brugt til at bygge tag.", null, timber1);
         
         Inventory inv1 = new Inventory(timber1, product1, 5, 100, 25);
         product1.setInventory(inv1);
         
         ProductContainer.getInstance().addProduct(product1);
    }
}