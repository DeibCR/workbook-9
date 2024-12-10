package com.pluralsight.NorthwindTradersSpringBoot;
import dao.SimpleProductDAO;

import dao.ProductDAO;
import model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = "dao")
public class NorthwindTradersSpringBootApplication implements CommandLineRunner {

	@Autowired
	private ProductDAO productDAO;

	public static void main(String[] args) {
		SpringApplication.run(NorthwindTradersSpringBootApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			// Display menu
			System.out.println("Choose an option:");
			System.out.println("1. List Products");
			System.out.println("2. Add Product");
			System.out.println("3. Exit");
			System.out.print("Enter choice: ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:

					System.out.println("\nProducts in the system:");
					System.out.printf("%-10s %-20s %-15s %-10s\n", "ID", "Name", "Category", "Price");
					System.out.println("------------------------------------------------------------");


					if (productDAO.getAll().isEmpty()) {
						System.out.println("No products available.");
					} else {
						for (Product product : productDAO.getAll()) {
							System.out.printf("%-10d %-20s %-15s $%-10.2f\n",
									product.getProductID(),
									product.getName(),
									product.getCategory(),
									product.getPrice());
						}
					}
					break;

				case 2:
					System.out.print("Enter product ID: ");
					int productID = scanner.nextInt();
					scanner.nextLine();

					System.out.print("Enter product name: ");
					String name = scanner.nextLine();

					System.out.print("Enter product category: ");
					String category = scanner.nextLine();

					System.out.print("Enter product price: ");
					double price = 0.0;
					boolean validPrice = false;


					while (!validPrice) {
						if (scanner.hasNextDouble()) {
							price = scanner.nextDouble();
							scanner.nextLine();
							validPrice = true;
						} else {
							System.out.print("Invalid input. Please enter a valid product price: ");
							scanner.nextLine();
						}
					}


					Product newProduct = new Product(productID,name,category, price);
					productDAO.add(newProduct);
					System.out.println("Product added: " + name);
					break;

				case 3:
					System.out.println("Exiting...");
					break;

				default:
					System.out.println("Invalid choice. Please try again.");
			}

		} while (choice != 3);

		scanner.close();

	}
}
