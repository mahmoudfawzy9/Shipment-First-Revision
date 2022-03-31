package com.company.star.datamanager;

import com.company.star.model.Language;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LanguageDataManager extends DataManager {
	@Value("${urls.language-service}")
	private String languageServiceUrl;

	public Language getLanguageById(int languageId) {
		String url = languageServiceUrl + "/" + languageId;

		ResponseEntity<String> res = getData(url);
		return getResult(res, Language.class);
	}

}
