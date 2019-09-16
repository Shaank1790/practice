/**
 * 
 */
package com.sapient.coding.utils;

/**
 * @author Shashank
 *
 */
public enum TransactionFee {
	
	FIVE_HUNDRED(500.00), HUNDRED(100.00), FIFTY(50.00), TEN(10.00);
	
	private Double value;
	
	private TransactionFee(Double value) {
		this.value = value;
	}
	
	public Double getTransactionFeeValue() {
		return value;
	}
	

}
