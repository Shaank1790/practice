/**
 * 
 */
package com.sapient.coding.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.coding.model.TransactionAttributes;

/**
 * @author Shashank
 *
 */
@Service
public interface IFormatParser {
	
	public List<TransactionAttributes> parse() throws IOException;

}
