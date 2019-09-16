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
public class TransactionTypeSorter implements Comparator<TransactionAttributes> {

	@Override
	public int compare(TransactionAttributes o1, TransactionAttributes o2) {
		//Sorting according to TransactionType
		return o1.getTransactionType().compareTo(o2.getTransactionType());
	}

}
