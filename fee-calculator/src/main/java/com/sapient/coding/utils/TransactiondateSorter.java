/**
 * 
 */
package com.sapient.coding.utils;

import java.util.Comparator;

import com.sapient.coding.model.TransactionAttributes;

/**
 * @author Shashank
 *
 */
public class TransactiondateSorter implements Comparator<TransactionAttributes> {

	@Override
	public int compare(TransactionAttributes o1, TransactionAttributes o2) {
		//Sorting according to TransactionDate
		return o1.getTransactionDate().compareTo(o2.getTransactionDate());
	}

}
