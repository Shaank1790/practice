package com.sapient.coding.feecalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.coding.model.TransactionAttributes;
import com.sapient.coding.model.TransactionType;
import com.sapient.coding.service.CSVFormatImpl;
import com.sapient.coding.service.TransactionProcessorImpl;
import com.sapient.coding.utils.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionProcessorImplTest {

	@Autowired
	ApplicationContext context;

	@Autowired
	CSVFormatImpl csvFormatImpl;

	@Autowired
	TransactionProcessorImpl transactionProcessorImpl;

	List<TransactionAttributes> txnAttributeList;
	Resource resource;

	@Test
	public void intradayTransactionTest() throws IOException {
		resource = context.getResource("/intradaytxn.csv");
		InputStream inputStream = resource.getInputStream();
		Reader in = new InputStreamReader(inputStream);
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		
		txnAttributeList = StreamSupport.stream(records.spliterator(), false)
		.map(r -> {
			TransactionAttributes extTransaction = new TransactionAttributes();
			extTransaction.setExternalTransactionID(r.get("External Transaction Id"));
			extTransaction.setClientID(r.get("Client Id"));
			extTransaction.setSecurityID(r.get("Security Id"));
			extTransaction.setTransactionType(Enum.valueOf(TransactionType.class, r.get("Transaction Type")));
			extTransaction.setTransactionDate(Utils.stringToDate(r.get("Transaction Date")));
			extTransaction.setMarketValue(Double.valueOf(r.get("Market Value")));
			extTransaction.setPriorityFlag(r.get("Priority Flag"));
			return extTransaction;
		})
		.collect(Collectors.toList());
		
		//txnAttributeList = csvFormatImpl.getTransactionList(records);
		assertNotNull(txnAttributeList);
		txnAttributeList = transactionProcessorImpl.processTransactions(txnAttributeList);
		assertNotNull(txnAttributeList);
		System.out.println("Transaction's List Not Null");
		List<Double> transactionFeeList = txnAttributeList.stream().map(e -> e.getTransactionFee()).collect(Collectors.toList());
		/*for (Double d : transactionFeeList)
			System.out.println(d + "--------------------------");*/
		
		transactionFeeList.forEach(System.out::println);
		assertEquals(true, transactionFeeList.stream().anyMatch(tf -> tf == 500));
	}

	/*@Test
	public void itransactionChargeProcessorTest() throws IOException {
		resource = context.getResource("/transactionChargeProcessorData.csv");
		InputStream inputStream = resource.getInputStream();
		Reader in = new InputStreamReader(inputStream);
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		txnAttributeList = fileParserCSVImpl.getTransactionList(records);
		assertNotNull(txnAttributeList);
		txnAttributeList = transactionServiceImpl.feeCalculationProcessor(txnAttributeList);
		assertNotNull(txnAttributeList);
		txnAttributeList.forEach(extTransactionxt -> {
			if (extTransactionxt.getExternalTransactionId().equals("EX1"))
				assertEquals(500, extTransactionxt.getTransactionFee(), 0.001);
			if (extTransactionxt.getExternalTransactionId().equals("EX2"))
				assertEquals(100, extTransactionxt.getTransactionFee(), 0.001);
			if (extTransactionxt.getExternalTransactionId().equals("EX3"))
				assertEquals(50, extTransactionxt.getTransactionFee(), 0.001);
		});
	}*/

}
