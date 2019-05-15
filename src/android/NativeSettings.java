/*
 * PhoneGap is available under *either* the terms of the modified BSD license *or* the
 * MIT License (2008). See http://opensource.org/licenses/alphabetical for full text.
 *
 * Copyright (c) 2005-2010, Nitobi Software Inc.
 * Copyright (c) 2011, IBM Corporation
 */

package com.phonegap.plugins.nativesettings;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;

import android.provider.Settings;

import android.os.Build;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

public class NativeSettings extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		Context context=this.cordova.getActivity().getApplicationContext();
        PluginResult.Status status = PluginResult.Status.OK;
        Uri packageUri = Uri.parse("package:" + this.cordova.getActivity().getPackageName());
        String result = "";

        //Information on settings can be found here:
        //http://developer.android.com/reference/android/provider/Settings.html
		
		String actionToOpen = args.getString(0);
		Intent intent = null;
		
		if (action.equals("openCustom")) {
			intent = new Intent(actionToOpen);
		} else {

			if (actionToOpen.equals("accessibility")) {
				intent = new Intent(android.provider.Settings.actionToOpen_ACCESSIBILITY_SETTINGS);
			} else if (actionToOpen.equals("account")) {
				intent = new Intent(android.provider.Settings.actionToOpen_ADD_ACCOUNT);
			} else if (actionToOpen.equals("airplane_mode")) {
				intent = new Intent(android.provider.Settings.actionToOpen_AIRPLANE_MODE_SETTINGS);
			} else if (actionToOpen.equals("apn")) {
				intent = new Intent(android.provider.Settings.actionToOpen_APN_SETTINGS);
			} else if (actionToOpen.equals("application_details")) {
				intent = new Intent(android.provider.Settings.actionToOpen_APPLICATION_DETAILS_SETTINGS, packageUri);
			} else if (actionToOpen.equals("application_development")) {
				intent = new Intent(android.provider.Settings.actionToOpen_APPLICATION_DEVELOPMENT_SETTINGS);
			} else if (actionToOpen.equals("application")) {
				intent = new Intent(android.provider.Settings.actionToOpen_APPLICATION_SETTINGS);
			}
			//else if (actionToOpen.equals("battery_saver")) {
			//    intent = new Intent(android.provider.Settings.actionToOpen_BATTERY_SAVER_SETTINGS);
			//}
			else if (actionToOpen.equals("battery_optimization")) {
				intent = new Intent(android.provider.Settings.actionToOpen_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
			} else if (actionToOpen.equals("bluetooth")) {
				intent = new Intent(android.provider.Settings.actionToOpen_BLUETOOTH_SETTINGS);
			} else if (actionToOpen.equals("captioning")) {
				intent = new Intent(android.provider.Settings.actionToOpen_CAPTIONING_SETTINGS);
			} else if (actionToOpen.equals("cast")) {
				intent = new Intent(android.provider.Settings.actionToOpen_CAST_SETTINGS);
			} else if (actionToOpen.equals("data_roaming")) {
				intent = new Intent(android.provider.Settings.actionToOpen_DATA_ROAMING_SETTINGS);
			} else if (actionToOpen.equals("date")) {
				intent = new Intent(android.provider.Settings.actionToOpen_DATE_SETTINGS);
			} else if (actionToOpen.equals("about")) {
				intent = new Intent(android.provider.Settings.actionToOpen_DEVICE_INFO_SETTINGS);
			} else if (actionToOpen.equals("display")) {
				intent = new Intent(android.provider.Settings.actionToOpen_DISPLAY_SETTINGS);
			} else if (actionToOpen.equals("dream")) {
				intent = new Intent(android.provider.Settings.actionToOpen_DREAM_SETTINGS);
			} else if (actionToOpen.equals("home")) {
				intent = new Intent(android.provider.Settings.actionToOpen_HOME_SETTINGS);
			} else if (actionToOpen.equals("keyboard")) {
				intent = new Intent(android.provider.Settings.actionToOpen_INPUT_METHOD_SETTINGS);
			} else if (actionToOpen.equals("keyboard_subtype")) {
				intent = new Intent(android.provider.Settings.actionToOpen_INPUT_METHOD_SUBTYPE_SETTINGS);
			} else if (actionToOpen.equals("storage")) {
				intent = new Intent(android.provider.Settings.actionToOpen_INTERNAL_STORAGE_SETTINGS);
			} else if (actionToOpen.equals("locale")) {
				intent = new Intent(android.provider.Settings.actionToOpen_LOCALE_SETTINGS);
			} else if (actionToOpen.equals("location")) {
				intent = new Intent(android.provider.Settings.actionToOpen_LOCATION_SOURCE_SETTINGS);
			} else if (actionToOpen.equals("manage_all_applications")) {
				intent = new Intent(android.provider.Settings.actionToOpen_MANAGE_ALL_APPLICATIONS_SETTINGS);
			} else if (actionToOpen.equals("manage_applications")) {
				intent = new Intent(android.provider.Settings.actionToOpen_MANAGE_APPLICATIONS_SETTINGS);
			} else if (actionToOpen.equals("memory_card")) {
				intent = new Intent(android.provider.Settings.actionToOpen_MEMORY_CARD_SETTINGS);
			} else if (actionToOpen.equals("network")) {
				intent = new Intent(android.provider.Settings.actionToOpen_NETWORK_OPERATOR_SETTINGS);
			} else if (actionToOpen.equals("nfcsharing")) {
				intent = new Intent(android.provider.Settings.actionToOpen_NFCSHARING_SETTINGS);
			} else if (actionToOpen.equals("nfc_payment")) {
				intent = new Intent(android.provider.Settings.actionToOpen_NFC_PAYMENT_SETTINGS);
			} else if (actionToOpen.equals("nfc_settings")) {
				intent = new Intent(android.provider.Settings.actionToOpen_NFC_SETTINGS);
			} else if (actionToOpen.equals("notification_id")) {
				// from: https://stackoverflow.com/questions/32366649/any-way-to-link-to-the-android-notification-settings-for-my-app
				intent = new Intent();
				if(android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1){
					intent.setactionToOpen("android.settings.APP_NOTIFICATION_SETTINGS");
					intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
				}else if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
					intent.setactionToOpen("android.settings.APP_NOTIFICATION_SETTINGS");
					intent.putExtra("app_package", context.getPackageName());
					intent.putExtra("app_uid", context.getApplicationInfo().uid);
				}else {
					intent.setactionToOpen(Settings.actionToOpen_APPLICATION_DETAILS_SETTINGS);
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					intent.setData(Uri.parse("package:" + context.getPackageName()));
				}
			}
			//else if (actionToOpen.equals("notification_listner")) {
			//    intent = new Intent(android.provider.Settings.actionToOpen_NOTIFICATION_LISTENER_SETTINGS);
			//}
			else if (actionToOpen.equals("print")) {
				intent = new Intent(android.provider.Settings.actionToOpen_PRINT_SETTINGS);
			} else if (actionToOpen.equals("privacy")) {
				intent = new Intent(android.provider.Settings.actionToOpen_PRIVACY_SETTINGS);
			} else if (actionToOpen.equals("quick_launch")) {
				intent = new Intent(android.provider.Settings.actionToOpen_QUICK_LAUNCH_SETTINGS);
			} else if (actionToOpen.equals("search")) {
				intent = new Intent(android.provider.Settings.actionToOpen_SEARCH_SETTINGS);
			} else if (actionToOpen.equals("security")) {
				intent = new Intent(android.provider.Settings.actionToOpen_SECURITY_SETTINGS);
			} else if (actionToOpen.equals("settings")) {
				intent = new Intent(android.provider.Settings.actionToOpen_SETTINGS);
			} else if (actionToOpen.equals("show_regulatory_info")) {
				intent = new Intent(android.provider.Settings.actionToOpen_SHOW_REGULATORY_INFO);
			} else if (actionToOpen.equals("sound")) {
				intent = new Intent(android.provider.Settings.actionToOpen_SOUND_SETTINGS);
			} else if (actionToOpen.equals("store")) {
				intent = new Intent(Intent.actionToOpen_VIEW,
						Uri.parse("market://details?id=" + this.cordova.getActivity().getPackageName()));
			} else if (actionToOpen.equals("sync")) {
				intent = new Intent(android.provider.Settings.actionToOpen_SYNC_SETTINGS);
			} else if (actionToOpen.equals("usage")) {
				intent = new Intent(android.provider.Settings.actionToOpen_USAGE_ACCESS_SETTINGS);
			} else if (actionToOpen.equals("user_dictionary")) {
				intent = new Intent(android.provider.Settings.actionToOpen_USER_DICTIONARY_SETTINGS);
			} else if (actionToOpen.equals("voice_input")) {
				intent = new Intent(android.provider.Settings.actionToOpen_VOICE_INPUT_SETTINGS);
			} else if (actionToOpen.equals("wifi_ip")) {
				intent = new Intent(android.provider.Settings.actionToOpen_WIFI_IP_SETTINGS);
			} else if (actionToOpen.equals("wifi")) {
				intent = new Intent(android.provider.Settings.actionToOpen_WIFI_SETTINGS);
			} else if (actionToOpen.equals("wireless")) {
				intent = new Intent(android.provider.Settings.actionToOpen_WIRELESS_SETTINGS);
			} else {
				status = PluginResult.Status.INVALID_actionToOpen;
				callbackContext.sendPluginResult(new PluginResult(status, result));
				return false;
			}
		}
        
        if(args.length() > 1 && args.getBoolean(1)) {
        	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        this.cordova.getActivity().startActivity(intent);
        
        callbackContext.sendPluginResult(new PluginResult(status, result));
        return true;
    }
}

