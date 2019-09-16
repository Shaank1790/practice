/**
 * 
 */
package com.sapient.coding.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sapient.coding.model.TransactionAttributes;
import com.sapient.coding.model.TransactionType;
import com.sapient.coding.utils.TransactionFee;

/**
 * @author Shashank
 *
 */
@Component
public class TransactionProcessorImpl implements ITransactionProcessor {
	
	/**
	 * @see com.sapient.coding.service.ITransactionProcessor#generateReport()
	 */
	@Override
	public void generateReport(List<TransactionAttributes> transactions) {
		// By Default the records are sorted by ClientID. in utils pkg we have different sorters  
		Collections.sort(transactions, (txn1,txn2) -> {
			
			if((txn1.getClientID().compareTo(txn2.getClientID()))==0) {
				
				if((txn1.getTransactionType().compareTo(txn2.getTransactionType()))==0) {
					
					if((txn1.getTransactionDate().compareTo(txn2.getTransactionDate()))==0) {
						
						return txn1.getPriorityFlag().compareTo(txn2.getPriorityFlag());
					}
					return txn1.getTransactionDate().compareTo(txn2.getTransactionDate());
				}
				return txn1.getTransactionType().compareTo(txn2.getTransactionType());
			}
			return txn1.getClientID().compareTo(txn2.getClientID());
		});
		
		System.out.println("Transaction Fee sorted by Client Id then Transaction Type then Transaction Date and then Priority");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.printf("%10s %10s %10s %10s %10s","Client Id" , "Transaction Type" , "Transaction Date" , "Priority" , "Processing Fee");
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		for (TransactionAttributes transaction : transactions) {
			System.out.format("%10s %10s %10s %10s %10f",transaction.getClientID(),transaction.getTransactionType(),
					transaction.getTransactionDate(),transaction.getPriorityFlag(),
					transaction.getTransactionFee());
			System.out.println();
		}
		System.out.println("--------------------------------------------------------------------------------");
		
		Map<String, List<TransactionAttributes>> groupByClientID = transactions.stream().collect(Collectors.groupingBy(TransactionAttributes::getClientID));
		System.out.println("------------------- Grouped By ClientID -------------------");
		groupByClientID.entrySet().stream().forEach(e->System.out.println(e.getKey()+"-->"+e.getValue()));
		
		Map<TransactionType, List<TransactionAttributes>> groupByTransactionType = transactions.stream().collect(Collectors.groupingBy(TransactionAttributes::getTransactionType));
		System.out.println("------------------- Grouped By Transaction Type -------------------");
		groupByTransactionType.entrySet().stream().forEach(e->System.out.println(e.getKey()+"-->"+e.getValue()));
		
		Map<Date, List<TransactionAttributes>> groupByTransactionDate = transactions.stream().collect(Collectors.groupingBy(TransactionAttributes::getTransactionDate));
		System.out.println("------------------- Grouped By Transaction Date -------------------");
		groupByTransactionDate.entrySet().stream().forEach(e->System.out.println(e.getKey()+"-->"+e.getValue()));
		
		Map<String, List<TransactionAttributes>> groupByPriority = transactions.stream().collect(Collectors.groupingBy(TransactionAttributes::getPriorityFlag));
		System.out.println("------------------- Grouped By Priority -------------------");
		groupByPriority.entrySet().stream().forEach(e->System.out.println(e.getKey()+"-->"+e.getValue()));

	}

	/**
	 * @see com.sapient.coding.service.ITransactionProcessor#processTransactions()
	 */
	@Override
	public List<TransactionAttributes> processTransactions(List<TransactionAttributes> attributesList) {
		// TODO Auto-generated method stub
		
		double transaction = 0.0;
		boolean isIntraDayTransaction = false;
		List<TransactionAttributes> updatedTransactions = new ArrayList<>();
		
		for (int i = 0; i < attributesList.size(); i++) {
			if ((i + 1) >= attributesList.size())
				break;
			TransactionAttributes txn = attributesList.get(i);

			if (txn.getClientID().equals(attributesList.get(i + 1).getClientID())) { // 1
				if (txn.getSecurityID().equals(attributesList.get(i + 1).getSecurityID())) {// 2
					if (txn.getTransactionDate().equals(attributesList.get(i + 1).getTransactionDate())) {// 3
						if ((txn.getTransactionType().equals(TransactionType.BUY)
								|| txn.getTransactionType().equals(TransactionType.SELL))
								&& (attributesList.get(i + 1).getTransactionType().equals(TransactionType.BUY)
										|| attributesList.get(i + 1).getTransactionType()
												.equals(TransactionType.SELL))) {// 4
							//transaction += 10;
							txn.setTransactionFee(TransactionFee.valueOf("TEN").getTransactionFeeValue());
							isIntraDayTransaction = true;
							txn.setIsIntraDayTransaction(isIntraDayTransaction);
						} // end 4
					} // end 3
				} // end 2
			} // end 1
			else {
				if (txn.getPriorityFlag().equals("Y")) {
					//transaction += 500;
					txn.setTransactionFee(TransactionFee.valueOf("FIVE_HUNDRED").getTransactionFeeValue());
				} else if (txn.getPriorityFlag().equals("N") && (txn.getTransactionType().equals(TransactionType.SELL)
															|| txn.getTransactionType().equals(TransactionType.WITHDRAW))) {

					//transaction += 100;
					txn.setTransactionFee(TransactionFee.valueOf("HUNDRED").getTransactionFeeValue());
				} else if (txn.getPriorityFlag().equals("N") && (txn.getTransactionType().equals(TransactionType.BUY)
															|| txn.getTransactionType().equals(TransactionType.DEPOSIT))) {
					//transaction += 50;
					txn.setTransactionFee(TransactionFee.valueOf("FIFTY").getTransactionFeeValue());
				}
			} // end of else
			
			updatedTransactions.add(txn);// transactions updated processing fee

		} // end of for
		return updatedTransactions;
		
	} // end of processTransactions

}
