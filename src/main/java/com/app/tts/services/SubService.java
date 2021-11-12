package com.app.tts.services;

import com.app.tts.error.exception.OracleException;
import com.app.tts.util.AppParams;
import com.app.tts.util.DBProcedureUtil;
import com.app.tts.util.ParamUtil;
import io.netty.handler.codec.http.HttpResponseStatus;
import oracle.jdbc.OracleTypes;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class SubService extends MasterService{
	
	public static final String GET_USER_BY_EMAIL = "{call PKG_USER.get_user_by_email(?,?,?,?)}";
	
	public static List<Map> getUserByEmail(String email) throws SQLException{
		List<Map> result = excuteQuery(GET_USER_BY_EMAIL, new Object [] {email});
		return result;
	}
	
	
//	public static final String GET_ALL_USER = "{call PKG_REGISTER.get_all_user(?,?,?)}";
//	public static final String INSERT_USER = "{call PKG_REGISTER.insert_user(?,?,?,?,?,?,?,?,?,?,?)}";
//	public static final String GET_USER_BY_EMAIL = "{call PKG_REGISTER.get_user_by_email(?,?,?,?)}";
//	
//	public static List<Map> getAllUser() throws SQLException{
//		Map inputParams = new LinkedHashMap<Integer, String>();
//		
//		Map<Integer, Integer> outputParamsTypes = new LinkedHashMap<>();
//		outputParamsTypes.put(1, OracleTypes.NUMBER);
//		outputParamsTypes.put(2, OracleTypes.VARCHAR);
//		outputParamsTypes.put(3, OracleTypes.CURSOR);
//		
//		Map<Integer, String> outputParamsNames = new LinkedHashMap<>();
//		outputParamsNames.put(1, AppParams.RESULT_CODE);
//		outputParamsNames.put(2, AppParams.RESULT_MSG);
//		outputParamsNames.put(3, AppParams.RESULT_DATA);
//		
//		Map searchResultMap = DBProcedureUtil.execute(dataSource, INSERT_USER, inputParams, outputParamsTypes,
//				outputParamsNames);
//
//		int resultCode = ParamUtil.getInt(searchResultMap, AppParams.RESULT_CODE);
//
//
//		if (resultCode != HttpResponseStatus.OK.code()) {
//			throw new OracleException(ParamUtil.getString(searchResultMap, AppParams.RESULT_MSG));
//		}
//
//		Map resultMap = new HashMap<>();
//		List<Map> result = new ArrayList();
//		List<Map> resultDataList = ParamUtil.getListData(searchResultMap, AppParams.RESULT_DATA);
//
//		for (Map c : resultDataList) {
//			c = format(c);
//			result.add(c);
//		}
//
//		return result;
//	}
//	
//	public static List<Map> insertUser(String username, String password, String firstName, String lastName, String phone, 
//			String email, String address, String postal) throws SQLException {
//		Map inputParams = new LinkedHashMap<Integer, String>();
//		inputParams.put(1, username);
//		inputParams.put(2, password);
//		inputParams.put(3, firstName);
//		inputParams.put(4, lastName);
//		inputParams.put(5, phone);
//		inputParams.put(6, email);
//		inputParams.put(7, address);
//		inputParams.put(8, postal);
//		
//		Map<Integer, Integer> outputParamsTypes = new LinkedHashMap<>();
//		outputParamsTypes.put(9, OracleTypes.NUMBER);
//		outputParamsTypes.put(10, OracleTypes.VARCHAR);
//		outputParamsTypes.put(11, OracleTypes.CURSOR);
//		
//		Map<Integer, String> outputParamsNames = new LinkedHashMap<>();
//		outputParamsNames.put(9, AppParams.RESULT_CODE);
//		outputParamsNames.put(10, AppParams.RESULT_MSG);
//		outputParamsNames.put(11, AppParams.RESULT_DATA);
//		
//		Map searchResultMap = DBProcedureUtil.execute(dataSource, INSERT_USER, inputParams, outputParamsTypes,
//				outputParamsNames);
//
//		int resultCode = ParamUtil.getInt(searchResultMap, AppParams.RESULT_CODE);
//
//
//		if (resultCode != HttpResponseStatus.OK.code()) {
//			throw new OracleException(ParamUtil.getString(searchResultMap, AppParams.RESULT_MSG));
//		}
//
//		Map resultMap = new HashMap<>();
//		List<Map> result = new ArrayList();
//		List<Map> resultDataList = ParamUtil.getListData(searchResultMap, AppParams.RESULT_DATA);
//		for (Map c : resultDataList) {
//			c = format(c);
//			result.add(c);
//		}
//
//		return result;
//	}
//	
//	public static List<Map> getUserByEmail(String email) throws SQLException {
//		Map inputParams = new LinkedHashMap<Integer, String>();
//		inputParams.put(1, email);
//		
//		Map<Integer, Integer> outputParamsTypes = new LinkedHashMap<>();
//		outputParamsTypes.put(2, OracleTypes.NUMBER);
//		outputParamsTypes.put(3, OracleTypes.VARCHAR);
//		outputParamsTypes.put(4, OracleTypes.CURSOR);
//		
//		Map<Integer, String> outputParamsNames = new LinkedHashMap<>();
//		outputParamsNames.put(2, AppParams.RESULT_CODE);
//		outputParamsNames.put(3, AppParams.RESULT_MSG);
//		outputParamsNames.put(4, AppParams.RESULT_DATA);
//
//		Map searchResultMap = DBProcedureUtil.execute(dataSource, GET_USER_BY_EMAIL, inputParams, outputParamsTypes,
//				outputParamsNames);
//
//		int resultCode = ParamUtil.getInt(searchResultMap, AppParams.RESULT_CODE);
//
//
//		if (resultCode != HttpResponseStatus.OK.code()) {
//			throw new OracleException(ParamUtil.getString(searchResultMap, AppParams.RESULT_MSG));
//		}
//
//		Map resultMap = new HashMap<>();
//		List<Map> result = new ArrayList();
//		List<Map> resultDataList = ParamUtil.getListData(searchResultMap, AppParams.RESULT_DATA);
//		for (Map c : resultDataList) {
//			c = format(c);
//			result.add(c);
//		}
//
//		return result;
//	}
//		
//		private static Map format(Map queryData) {
//
//			Map resultMap = new LinkedHashMap<>();
//			Map printTable = new LinkedHashMap<>();
//			Map image = new LinkedHashMap<>();
//			
//			//them postal code 
//			resultMap.put(AppParams.USERNAME, ParamUtil.getString(queryData, AppParams.S_USERNAME));
//			resultMap.put(AppParams.PASSWORD, ParamUtil.getString(queryData, AppParams.S_PASSWORD));
//			resultMap.put(AppParams.FIRST_NAME, ParamUtil.getString(queryData, AppParams.S_FIRST_NAME));
//			resultMap.put(AppParams.LAST_NAME, ParamUtil.getString(queryData, AppParams.S_LAST_NAME));
//			resultMap.put(AppParams.PHONE, ParamUtil.getString(queryData, AppParams.S_PHONE));
//			resultMap.put(AppParams.EMAIL, ParamUtil.getString(queryData, AppParams.S_EMAIL));
//			resultMap.put(AppParams.ADDRESS, ParamUtil.getString(queryData, AppParams.S_ADDRESS));
//			resultMap.put(AppParams.POSTAL_CODE, ParamUtil.getString(queryData, AppParams.S_POSTAL_CODE));
//			
//			//printable
//			printTable.put("front_top", ParamUtil.getString(queryData, AppParams.S_PRINTABLE_FRONT_TOP));
//			printTable.put("front_left", ParamUtil.getString(queryData, AppParams.S_PRINTABLE_FRONT_LEFT));
//			printTable.put("front_width", ParamUtil.getString(queryData, AppParams.S_PRINTABLE_FRONT_WIDTH));
//			printTable.put("front_height", ParamUtil.getString(queryData, AppParams.S_PRINTABLE_FRONT_HEIGHT));
//			printTable.put("back_top", ParamUtil.getString(queryData, AppParams.S_PRINTABLE_BACK_TOP));
//			printTable.put("back_left", ParamUtil.getString(queryData, AppParams.S_PRINTABLE_BACK_LEFT));
//			printTable.put("back_width", ParamUtil.getString(queryData, AppParams.S_PRINTABLE_BACK_WIDTH));
//			printTable.put("back_height", ParamUtil.getString(queryData, AppParams.S_PRINTABLE_BACK_HEIGHT));
//			//image
//			image.put("icon_url", ParamUtil.getString(queryData, AppParams.S_ICON_IMG_URL));
//			image.put("front_url", ParamUtil.getString(queryData, AppParams.S_FRONT_IMG_URL));
//			image.put("front_width", ParamUtil.getString(queryData, AppParams.S_FRONT_IMG_WIDTH));
//			image.put("front_height", ParamUtil.getString(queryData, AppParams.S_FRONT_IMG_HEIGHT));
//			image.put("back_url", ParamUtil.getString(queryData, AppParams.S_BACK_IMG_URL));
//			image.put("back_width", ParamUtil.getString(queryData, AppParams.S_BACK_IMG_WIDTH));
//			image.put("back_height", ParamUtil.getString(queryData, AppParams.S_BACK_IMG_HEIGHT));
//
//			resultMap.put(AppParams.IMAGE, image);
//			resultMap.put(AppParams.PRINTABLE, printTable);
//			return resultMap;
//		}
//

		private static final Logger LOGGER = Logger.getLogger(BaseService.class.getName());
}
