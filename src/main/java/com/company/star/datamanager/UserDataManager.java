package com.company.star.datamanager;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDataManager extends DataManager {
	@Value("${urls.user-service}")
	private String userServiceUrl;

	public int getUserLanguage(int userId) {
		String url = userServiceUrl + "/" + userId + "/language";

		ResponseEntity<String> res = getData(url);
		if (res.getStatusCode().equals(HttpStatus.OK)) {
			JSONObject jo = new JSONObject(res.getBody());
			if (jo.has("data") && !jo.isNull("data")) {
				return jo.optInt("data", 2);
			}
		}
		return 2;
	}

}
