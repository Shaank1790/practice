/**
 * 
 */
package com.sapient.coding.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.sapient.coding.model.TransactionAttributes;
import com.sapient.coding.model.TransactionType;
import com.sapient.coding.utils.Utils;

/**
 * @author Shashank
 *
 */
@Service
public class CSVFormatImpl implements IFormatParser {
	
	/**
	 * @author Shashank
	 */
	public CSVFormatImpl() {
		super();
	}

	private List<TransactionAttributes> listTransactionAttributes;
	
	@Autowired
	ResourceLoader resourceLoader;

	@Override
	public List<TransactionAttributes> parse() throws IOException {
		// TODO Auto-generated method stub
		//System.out.println("--------------------------"+listTransactionAttributes);
		return listTransactionAttributes;
	}
	
	@PostConstruct
	public void init() throws IOException {
		
		System.out.println("------- Loading SampleData.csv into Context -------");
		
		Resource resource = resourceLoader.getResource("classpath:Worksheet.csv");
		InputStream inputStream = resource.getInputStream();
		Reader inputStreamReader = new InputStreamReader(inputStream);
		CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().withIgnoreSurroundingSpaces().parse(inputStreamReader);
		
		listTransactionAttributes = parser.getRecords().stream().map(rec->{
			TransactionAttributes extTransaction = new TransactionAttributes();
			extTransaction.setExternalTransactionID(rec.get("External Transaction Id"));
			extTransaction.setClientID(rec.get("Client Id"));
			extTransaction.setSecurityID(rec.get("Security Id"));
			extTransaction.setTransactionType(Enum.valueOf(TransactionType.class, rec.get("Transaction Type")));
			extTransaction.setTransactionDate(Utils.stringToDate(rec.get("Transaction Date")));
			extTransaction.setMarketValue(Double.valueOf(rec.get("Market Value")));
			extTransaction.setPriorityFlag(rec.get("Priority Flag"));
			return extTransaction;
			
		}).collect(Collectors.toList());
		
	}

}
