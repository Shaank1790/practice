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
public class TransactionPrioritySorter implements Comparator<TransactionAttributes> {

	@Override
	public int compare(TransactionAttributes o1, TransactionAttributes o2) {
		//Sorting according to PriorityFlag
		return o1.getPriorityFlag().compareTo(o2.getPriorityFlag());
	}

}
