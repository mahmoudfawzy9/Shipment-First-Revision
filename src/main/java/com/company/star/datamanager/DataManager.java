package com.company.star.datamanager;

import java.util.Iterator;
import java.util.Map;

import com.company.star.utils.GsonProvider;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.UnprocessableEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.apptcom.kidstar.exception.type.ServerErrorException;
import com.apptcom.kidstar.exception.type.ValidationErrorException;

@Component
public class DataManager {
	private static final Logger l = LoggerFactory.getLogger(DataManager.class);

	@Autowired
	protected RestTemplate restTemplate;

	protected HttpEntity<String> getHttpEntity(Object object) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		if (object != null) {
			JSONObject jo = new JSONObject(GsonProvider.getGson().toJson(object));

			String[] keys = new String[] { "id", "isDeleted", "createdAt" };

			for (String key : keys) {
				if (jo.has(key))
					jo.remove(key);
			}

			return new HttpEntity<>(jo.toString(), headers);
		}

		return new HttpEntity<>(headers);
	}

	protected ResponseEntity<String> getData(String url) {
		return getData(url, null);
	}

	protected ResponseEntity<String> getData(String url, Map<String, String> allRequestParams) {
		try {
			if (allRequestParams != null)
				url += buildQueryString(allRequestParams);

			l.debug("url: " + url);
			return restTemplate.getForEntity(url, String.class);

		} catch (ResourceAccessException | HttpServerErrorException e) {
			throw new ServerErrorException(e);
		}
	}

	protected ResponseEntity<String> postData(String url, HttpMethod httpMethod, Object data) {
		return postData(url, httpMethod, data, null);
	}

	protected ResponseEntity<String> postData(String url, HttpMethod httpMethod, Object data,
			Map<String, String> allRequestParams) {
		try {
			if (allRequestParams != null)
				url += buildQueryString(allRequestParams);

			l.debug("url: " + url);
			return restTemplate.exchange(url, httpMethod, getHttpEntity(data), String.class);

		} catch (HttpServerErrorException e) {
			throw new ServerErrorException(e);
		} catch (UnprocessableEntity ex) {
			throw new ValidationErrorException(ex);
		}
	}

	protected <E> E getResult(ResponseEntity<String> res, Class<E> cls) {
		if (res.getStatusCode().equals(HttpStatus.OK)) {
			JSONObject jo = new JSONObject(res.getBody());
			if (jo.has("data") && !jo.isNull("data")) {
				Object response = null;
				if (cls.isArray()) {
					response = jo.getJSONArray("data");
				} else {
					response = jo.getJSONObject("data");
				}
				return GsonProvider.getGson().fromJson(response.toString(), cls);
			}
		}

		return null;
	}

	public static String buildQueryString(Map<String, String> params) {
		String str = params.isEmpty() ? "" : "?";

		Iterator<String> it = params.keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();
			String value = params.get(key);

			str += key + "=" + value;

			if (it.hasNext())
				str += "&";
		}

		return str;
	}
}