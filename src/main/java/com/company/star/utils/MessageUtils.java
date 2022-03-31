package com.company.star.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {
	@Autowired
	private MessageSource messageSource;

	public static final String PARENT_NOTIFICATION_TITLE_STORY_REQUEST = "parent_notification_title_story_request";
	public static final String PARENT_NOTIFICATION_BODY_STORY_REQUEST = "parent_notification_body_story_request";
	public static final int FEDEX_REQUEST = 1;
	public static final int UPS_REQUEST = 2;
	public static final String PARENT_NOTIFICATION_BODY_COIN_REQUEST = "parent_notification_body_coin_request";
	public static final String PARENT_NOTIFICATION_TITLE_KID_ACHIVEMENT = "parent_notification_title_kid_achivement";
	public static final String PARENT_NOTIFICATION_BODY_KID_ACHIVEMENT = "parent_notification_body_kid_achivement";
	public static final String PARENT_NOTIFICATION_TITLE_CHAT = "parent_notification_title_chat";
	public static final String PARENT_NOTIFICATION_BODY_CHAT = "parent_notification_body_chat";
	public static final String PARENT_NOTIFICATION_TITLE_NEW_BOOK = "parent_notification_title_new_book";
	public static final String PARENT_NOTIFICATION_BODY_NEW_BOOK = "parent_notification_body_new_book";

	public String getMessageLocalized(int carrierId, String tag, String shipment_id, String price) {
		try {
			return messageSource.getMessage(tag, null, LocaleContextHolder.getLocale());
		} catch (Exception e) {
			return tag;
		}
	}

	public String getMessageLocalized(String tag, Object... args) {
		try {
			return messageSource.getMessage(tag, args, LocaleContextHolder.getLocale());
		} catch (Exception e) {
			return tag;
		}
	}

	public String getMessageLocalized(String tag, String lang) {
		try {
			LocaleContextHolder.setLocale(new Locale(lang));
		} catch (Exception e) {
			LocaleContextHolder.setLocale(new Locale("en"));
		}
		try {
			return messageSource.getMessage(tag, null, LocaleContextHolder.getLocale());
		} catch (Exception e) {
			return tag;
		}
	}

	public String getMessageLocalized(String tag, String lang, Object... args) {
		try {
			LocaleContextHolder.setLocale(new Locale(lang));
		} catch (Exception e) {
			LocaleContextHolder.setLocale(new Locale("en"));
		}
		try {
			return messageSource.getMessage(tag, args, LocaleContextHolder.getLocale());
		} catch (Exception e) {
			return tag;
		}
	}

}
