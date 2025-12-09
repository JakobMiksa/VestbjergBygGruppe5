package tui;

import java.util.Scanner;

import controller.OrderController;
import controller.ProductController;
import model.Order;
import controller.CustomerController;

public class MenuUI {
	private OrderController orderController;
	private ProductController productController;
	private CustomerController customerController;
	private Order currOrder;
	
	public MenuUI() {
		orderController = new OrderController();
		productController = new ProductController();
		customerController = new CustomerController();
		createMenu();
	}

	public void createMenu() {
		boolean finished = false;

		while (!finished) {
			System.out.println(options());
			switch (getUserInput()) {
			case "1":
				createOrderMenu();
				break;
			case "0":
				finished = true;
				break;
			default:
				System.out.println("Prøv at vælge igen");
				break;
			}
		}
		System.out.println("Lukker system...");
		System.out.println("Farvel");
	}

	private void createOrderMenu() {
		System.out.println("create order!");
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
