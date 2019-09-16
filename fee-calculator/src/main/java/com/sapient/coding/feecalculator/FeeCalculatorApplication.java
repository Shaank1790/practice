package com.sapient.coding.feecalculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sapient.coding.model.TransactionAttributes;
import com.sapient.coding.service.IFormatParser;
import com.sapient.coding.service.ITransactionProcessor;

@SpringBootApplication
@ComponentScan("com.sapient.coding")
public class FeeCalculatorApplication implements CommandLineRunner {
	
	@Autowired
	IFormatParser parser;
	
	@Autowired
	ITransactionProcessor transactionProcessor;
	
	public static void main(String[] args) {
		SpringApplication.run(FeeCalculatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<TransactionAttributes> attributesList = parser.parse();
		attributesList.forEach(System.out::println);
		
		List<TransactionAttributes> updatedTransactions = transactionProcessor.processTransactions(attributesList);
		
		System.out.println("------------- UPDATED TRANSACTIONS -------------");

		updatedTransactions.forEach(System.out::println);
		transactionProcessor.generateReport(updatedTransactions);
		
	}
	
	
}
