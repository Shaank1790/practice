/**
 * 
 */
package com.sapient.coding.model;

import java.util.Date;

/**
 * @author Shashank
 *
 */
public class TransactionAttributes {
	
	private String externalTransactionID;
	private String clientID;
	private String securityID;
	private TransactionType transactiontype;
	private Date transactiondate;
	private Double marketValue;
	private String priorityFlag;
	private Double transactionFee;
	private Boolean isIntraDayTransaction; 
	
	
	/**
	 * 
	 */
	public TransactionAttributes() {
		
	}
	/**
	 * @param externalTransactionID
	 * @param clientID
	 * @param securityID
	 * @param transactiontype
	 * @param transactiondate
	 * @param marketValue
	 * @param priority
	 */
	public TransactionAttributes(String externalTransactionID, String clientID, String securityID,
			TransactionType transactiontype, Date transactiondate, Double marketValue, 
			String priorityFlag, Double transactionFee, Boolean isIntraDayTransaction) {
		super();
		this.externalTransactionID = externalTransactionID;
		this.clientID = clientID;
		this.securityID = securityID;
		this.transactiontype = transactiontype;
		this.transactiondate = transactiondate;
		this.marketValue = marketValue;
		this.priorityFlag = priorityFlag;
		this.transactionFee = transactionFee;
		this.isIntraDayTransaction = isIntraDayTransaction;
	}
	/**
	 * @return the externalTransactionID
	 */
	public String getExternalTransactionID() {
		return externalTransactionID;
	}
	/**
	 * @param externalTransactionID the externalTransactionID to set
	 */
	public void setExternalTransactionID(String externalTransactionID) {
		this.externalTransactionID = externalTransactionID;
	}
	/**
	 * @return the clientID
	 */
	public String getClientID() {
		return clientID;
	}
	/**
	 * @param clientID the clientID to set
	 */
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	/**
	 * @return the securityID
	 */
	public String getSecurityID() {
		return securityID;
	}
	/**
	 * @param securityID the securityID to set
	 */
	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}
	/**
	 * @return the transactiontype
	 */
	public TransactionType getTransactionType() {
		return transactiontype;
	}
	/**
	 * @param transactiontype the transactiontype to set
	 */
	public void setTransactionType(TransactionType transactiontype) {
		this.transactiontype = transactiontype;
	}
	/**
	 * @return the transactiondate
	 */
	public Date getTransactionDate() {
		return transactiondate;
	}
	/**
	 * @param transactiondate the transactiondate to set
	 */
	public void setTransactionDate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}
	/**
	 * @return the marketValue
	 */
	public Double getMarketValue() {
		return marketValue;
	}
	/**
	 * @param marketValue the marketValue to set
	 */
	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}
	/**
	 * @return the priority
	 */
	public String getPriorityFlag() {
		return priorityFlag;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}
	
	/**
	 * @return the transactionFee
	 */
	public Double getTransactionFee() {
		return transactionFee;
	}
	/**
	 * @param transactionFee the transactionFee to set
	 */
	public void setTransactionFee(Double transactionFee) {
		this.transactionFee = transactionFee;
	}
	
	/**
	 * @return the isIntraDayTransaction
	 */
	public Boolean getIsIntraDayTransaction() {
		return isIntraDayTransaction;
	}
	/**
	 * @param isIntraDayTransaction the isIntraDayTransaction to set
	 */
	public void setIsIntraDayTransaction(Boolean isIntraDayTransaction) {
		this.isIntraDayTransaction = isIntraDayTransaction;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionAttributes [externalTransactionID=" + externalTransactionID + ", clientID=" + clientID
				+ ", securityID=" + securityID + ", transactiontype=" + transactiontype + ", transactiondate="
				+ transactiondate + ", marketValue=" + marketValue + ", priorityFlag=" + priorityFlag
				+ ", transactionFee=" + transactionFee + ", isIntraDayTransaction=" + isIntraDayTransaction + "]";
	}

}
