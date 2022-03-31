package com.company.star.exception;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.apptcom.kidstar.exception.ApiSubError;
import com.apptcom.kidstar.exception.ApiValidationError;
import com.company.star.utils.GsonProvider;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.apptcom.kidstar.exception.type.ValidationErrorException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
	private static final Logger l = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR);
		// || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		if (httpResponse.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
			String response;
			try (InputStream body = httpResponse.getBody()) {
				response = IOUtils.toString(body, StandardCharsets.UTF_8);
			}
			l.debug("response: " + response);

			if (response != null) {
				try {
					JSONObject responseJo = new JSONObject(response);
					JSONArray arr = new JSONArray();
					if (responseJo.has("subErrors")) {
						arr = responseJo.getJSONArray("subErrors");
					}
					if (responseJo.has("errors")) {
						arr = responseJo.getJSONArray("errors");
					}
					ArrayList<ApiSubError> errors = new ArrayList<>();

					for (int i = 0; i < arr.length(); i++) {
						ApiValidationError error = GsonProvider.getGson().fromJson(arr.getJSONObject(i).toString(),
								ApiValidationError.class);
						errors.add(error);
					}
					throw new ValidationErrorException("Invalid Data", errors);
				} catch (JSONException e) {
					l.error(e.getMessage(), e);
				}
			}
		}
	}

}
