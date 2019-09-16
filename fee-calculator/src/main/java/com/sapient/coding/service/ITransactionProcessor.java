/**
 * 
 */
package com.sapient.coding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.coding.model.TransactionAttributes;

/**
 * @author Shashank
 *
 */
@Service
public interface ITransactionProcessor {
	
	public void generateReport(List<TransactionAttributes> transactions);
	
	public List<TransactionAttributes> processTransactions(List<TransactionAttributes> attributesList);

}
