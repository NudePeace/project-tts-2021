package com.app.tts.server.handler.base;

import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;

import com.app.tts.services.BaseService;
import com.app.tts.session.redis.SessionStore;
import com.app.tts.util.AppParams;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.web.RoutingContext;

public class RegisterHandler implements Handler<RoutingContext>, SessionStore {

	@Override
	public void handle(RoutingContext routingContext) {
		routingContext.vertx().executeBlocking(future -> {
			try {
				JsonObject jsonRequest = routingContext.getBodyAsJson();
				String username = jsonRequest.getString(AppParams.USERNAME);
				String password = jsonRequest.getString(AppParams.PASSWORD);
				String confirmPassword = jsonRequest.getString("confirmPassword");
				String firstName = jsonRequest.getString(AppParams.FIRST_NAME);
				String lastName = jsonRequest.getString(AppParams.LAST_NAME);
				String phone = jsonRequest.getString(AppParams.PHONE);
				String email = jsonRequest.getString(AppParams.EMAIL);
				String address = jsonRequest.getString(AppParams.ADDRESS);
				String postal = jsonRequest.getString(AppParams.POSTAL_CODE);

				JsonObject data = new JsonObject();

				routingContext.put(AppParams.RESPONSE_CODE, HttpResponseStatus.BAD_REQUEST.code());
				routingContext.put(AppParams.RESPONSE_MSG, HttpResponseStatus.BAD_REQUEST.reasonPhrase());
				data.put("email", email);
				
				LOGGER.info("---email = " + email);
				List <Map> user = BaseService.getUserByEmail(email);
				
				boolean duplicate = false;
				if (!user.isEmpty()) {
					duplicate = true;
				}
				if (!password.equals(confirmPassword)) {
					duplicate = true;
					data.put("message", "register failed, password and confirm password are not matched");
				} else if (!isValid(email)) {
					data.put("message", "register failed, email is not valid");
				} else if (duplicate) {
					data.put("message", "register failed, email is duplicated");
				} else if (!duplicate && isValid(email)) {
					// Đăng ký thành công
					BaseService.insertUser(username, password, firstName, lastName, phone, email, address, postal);
					data.put("message", "register successed");
					routingContext.put(AppParams.RESPONSE_CODE, HttpResponseStatus.CREATED.code());
					routingContext.put(AppParams.RESPONSE_MSG, HttpResponseStatus.CREATED.reasonPhrase());
				} else {
					data.put("message", "register failed");
				}
				routingContext.put(AppParams.RESPONSE_DATA, data);
				future.complete();
			} catch (Exception e) {
				routingContext.fail(e);
			}
		}, asyncResult -> {
			if (asyncResult.succeeded()) {
				routingContext.next();
			} else {
				routingContext.fail(asyncResult.cause());
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public void insert(String username, String password, String firstName, String lastName, String phone, 
			String email, String address, String postal) throws SQLException {
		BaseService.insertUser(username, password, firstName, lastName, phone, email, address, postal);
	}

	public static boolean isValid(String email) {
		boolean valid = false;
		valid = EmailValidator.getInstance().isValid(email);
		return true;
	}
	
	private static final Logger LOGGER = Logger.getLogger(BaseService.class.getName());
}
