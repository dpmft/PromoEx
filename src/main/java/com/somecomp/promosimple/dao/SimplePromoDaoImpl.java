package com.somecomp.promosimple.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.somecomp.promosimple.exception.PromoBusinessException;
import com.somecomp.promosimple.exception.PromoBusinessProblem;
import com.somecomp.promosimple.exception.PromoBusinessProblemImpl;
import com.somecomp.promosimple.exception.PromoSystemException;
import com.somecomp.promosimple.model.PromoSimpleRequest;
import com.somecomp.promosimple.model.PromoSimpleResponse;

@Repository
public class SimplePromoDaoImpl implements SimplePromoDao {
	
	private static final Logger logger = LogManager.getLogger(SimplePromoDaoImpl.class);
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public 	List<PromoSimpleResponse> create(PromoSimpleRequest promoRequest,String xAppCorelationId) throws PromoBusinessException{
		final String METHOD_NAME = "create";
		logger.info("aaa");
		List<PromoSimpleResponse> promoSimpleResponseList = new ArrayList<PromoSimpleResponse>();
		Connection conn = null;
		CallableStatement cStmt = null;
		
		PromoSimpleResponse response = null;
		
		String PACKAGE_CALL = "? := CML_RPM_CC_SIMPLE_PROMO_TEST.GENERATE_SIMPLE_PROMOTION(?,?,?);";
		
		StringBuffer buffer = new StringBuffer(200);
		buffer.append("DECLARE\n");
		buffer.append("BEGIN\n");
		buffer.append(PACKAGE_CALL).append("\n");
		buffer.append("END;");
		
		try {
			conn = dataSource.getConnection();
			cStmt = conn.prepareCall(buffer.toString());
			cStmt.registerOutParameter(1, Types.VARCHAR);
			cStmt.setNull(1, Types.VARCHAR);
			
			cStmt.setInt(2, Integer.parseInt(promoRequest.getPromoId()));
			
			cStmt.registerOutParameter(3, Types.VARCHAR);
			cStmt.setNull(3, Types.VARCHAR);
			
			cStmt.registerOutParameter(4, Types.VARCHAR);
			cStmt.setNull(4, Types.VARCHAR);
			
			cStmt.executeUpdate();
			
			/** there could be some validation errors from stored proc **/
			String errorOpVal = cStmt.getString(3);
			/** if everything is good we will get back a list of promo comp detail id **/
			String successOpVal = cStmt.getString(4);
			
			/** if there are any validation errors throw a BusinessException **/
			if(null != errorOpVal && errorOpVal.trim().length() > 0 && errorOpVal.contains("Invalid")) {
				logger.info("we got an error in response {}",errorOpVal);
				
				PromoBusinessProblem problem = new PromoBusinessProblemImpl("invalid_promo_id",errorOpVal) ;
				List<PromoBusinessProblem> problemList = new ArrayList<PromoBusinessProblem>();
				problemList.add(problem);
				throw new PromoBusinessException(problemList);									
			}
			
			if(null != successOpVal && successOpVal.trim().length() > 0 ) {
				logger.info("we have following promo comp id in response {}",successOpVal);
			}
			response = new PromoSimpleResponse();
			response.setPromoComponentId(successOpVal);
			response.setPromoHdrId(promoRequest.getPromoId());
			promoSimpleResponseList.add(response);
		}catch(SQLException sqle) {
			/** all database related operations could run into this exception so have this catch block **/
			sqle.printStackTrace();
			if(logger.isDebugEnabled()) {
				StringBuffer errorMessage = new StringBuffer();
				errorMessage.append("SQL Package call failed: " + buffer + "\n");
				errorMessage.append("SQL Exception");
				errorMessage.append(sqle.toString());
				errorMessage.append("\n\twith errorCode: ");
				errorMessage.append(sqle.getErrorCode());
				errorMessage.append("\n\twith SqlState: ");
				errorMessage.append(sqle.getSQLState());
				errorMessage.append("\n\twith nextException: ");
				errorMessage.append(sqle.getNextException());
				logger.debug(errorMessage.toString(),sqle);
			}
			logger.error("{},{},{} something wrong ! ",SimplePromoDaoImpl.class,METHOD_NAME,sqle);
			/** now wrap this SQLException info a business exception **/
			throw new PromoSystemException(sqle.toString(),sqle);
		}finally {
			try {
				cStmt.close();
				conn.close();
			}catch(SQLException e) {
				logger.error("{},{},{} something wrong while closing db resources ! ",SimplePromoDaoImpl.class,METHOD_NAME,e);
			}
		}
		logger.info("{} END {}",METHOD_NAME,xAppCorelationId);
		return promoSimpleResponseList;
	}

}
