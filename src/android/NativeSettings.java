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
import android.content.ComponentName;

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
		//https://stackoverflow.com/questions/31638986/protected-apps-setting-on-huawei-phones-and-how-to-handle-it
		
		String actionToOpen = args.getString(0);
		Intent intent = null;
		
		if (action.equals("openCustom")) {
			intent = new Intent(actionToOpen);
		} else if (action.equals("openCustomComponent")) {
			String customPkg = actionToOpen;
			String customCls = args.getString(1);
		
			intent = new Intent();
			intent.setComponent(new ComponentName(customPkg, customCls));
			
			if (args.length() > 2 && args.getBoolean(2)) {
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			}                               
		} else {
			if (actionToOpen.equals("accessibility")) {
				intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
			} else if (actionToOpen.equals("account")) {
				intent = new Intent(android.provider.Settings.ACTION_ADD_ACCOUNT);
			} else if (actionToOpen.equals("airplane_mode")) {
				intent = new Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS);
			} else if (actionToOpen.equals("apn")) {
				intent = new Intent(android.provider.Settings.ACTION_APN_SETTINGS);
			} else if (actionToOpen.equals("application_details")) {
				intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageUri);
			} else if (actionToOpen.equals("application_usage")) {
				// new, only for android Q
				//intent = new Intent(android.provider.Settings.ACTION_APP_USAGE_SETTINGS, packageUri);
			} else if (actionToOpen.equals("application_development")) {
				intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
			} else if (actionToOpen.equals("application")) {
				intent = new Intent(android.provider.Settings.ACTION_APPLICATION_SETTINGS);
			}
			//else if (actionToOpen.equals("battery_saver")) {
			//    intent = new Intent(android.provider.Settings.ACTION_BATTERY_SAVER_SETTINGS);
			//}
			else if (actionToOpen.equals("battery_optimization")) {
				intent = new Intent(android.provider.Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
			} else if (actionToOpen.equals("bluetooth")) {
				intent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
			} else if (actionToOpen.equals("captioning")) {
				intent = new Intent(android.provider.Settings.ACTION_CAPTIONING_SETTINGS);
			} else if (actionToOpen.equals("cast")) {
				intent = new Intent(android.provider.Settings.ACTION_CAST_SETTINGS);
			} else if (actionToOpen.equals("data_roaming")) {
				intent = new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
			} else if (actionToOpen.equals("date")) {
				intent = new Intent(android.provider.Settings.ACTION_DATE_SETTINGS);
			} else if (actionToOpen.equals("about")) {
				intent = new Intent(android.provider.Settings.ACTION_DEVICE_INFO_SETTINGS);
			} else if (actionToOpen.equals("display")) {
				intent = new Intent(android.provider.Settings.ACTION_DISPLAY_SETTINGS);
			} else if (actionToOpen.equals("dream")) {
				intent = new Intent(android.provider.Settings.ACTION_DREAM_SETTINGS);
			} else if (actionToOpen.equals("home")) {
				intent = new Intent(android.provider.Settings.ACTION_HOME_SETTINGS);
			} else if (actionToOpen.equals("keyboard")) {
				intent = new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS);
			} else if (actionToOpen.equals("keyboard_subtype")) {
				intent = new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS);
			} else if (actionToOpen.equals("storage")) {
				intent = new Intent(android.provider.Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
			} else if (actionToOpen.equals("locale")) {
				intent = new Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS);
			} else if (actionToOpen.equals("location")) {
				intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			} else if (actionToOpen.equals("manage_all_applications")) {
				intent = new Intent(android.provider.Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
			} else if (actionToOpen.equals("manage_applications")) {
				intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
			} else if (actionToOpen.equals("memory_card")) {
				intent = new Intent(android.provider.Settings.ACTION_MEMORY_CARD_SETTINGS);
			} else if (actionToOpen.equals("network")) {
				intent = new Intent(android.provider.Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
			} else if (actionToOpen.equals("nfcsharing")) {
				intent = new Intent(android.provider.Settings.ACTION_NFCSHARING_SETTINGS);
			} else if (actionToOpen.equals("nfc_payment")) {
				intent = new Intent(android.provider.Settings.ACTION_NFC_PAYMENT_SETTINGS);
			} else if (actionToOpen.equals("nfc_settings")) {
				intent = new Intent(android.provider.Settings.ACTION_NFC_SETTINGS);
			} else if (actionToOpen.equals("notification_id")) {
				// from: https://stackoverflow.com/questions/32366649/any-way-to-link-to-the-android-notification-settings-for-my-app
				intent = new Intent();
				if(android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1){
					intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
					intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
				}else if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
					intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
					intent.putExtra("app_package", context.getPackageName());
					intent.putExtra("app_uid", context.getApplicationInfo().uid);
				}else {
					intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					intent.setData(Uri.parse("package:" + context.getPackageName()));
				}
			}
			//else if (actionToOpen.equals("notification_listner")) {
			//    intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
			//}
			else if (actionToOpen.equals("print")) {
				intent = new Intent(android.provider.Settings.ACTION_PRINT_SETTINGS);
			} else if (actionToOpen.equals("privacy")) {
				intent = new Intent(android.provider.Settings.ACTION_PRIVACY_SETTINGS);
			} else if (actionToOpen.equals("quick_launch")) {
				intent = new Intent(android.provider.Settings.ACTION_QUICK_LAUNCH_SETTINGS);
			} else if (actionToOpen.equals("search")) {
				intent = new Intent(android.provider.Settings.ACTION_SEARCH_SETTINGS);
			} else if (actionToOpen.equals("security")) {
				intent = new Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS);
			} else if (actionToOpen.equals("settings")) {
				intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
			} else if (actionToOpen.equals("show_regulatory_info")) {
				intent = new Intent(android.provider.Settings.ACTION_SHOW_REGULATORY_INFO);
			} else if (actionToOpen.equals("sound")) {
				intent = new Intent(android.provider.Settings.ACTION_SOUND_SETTINGS);
			} else if (actionToOpen.equals("store")) {
				intent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("market://details?id=" + this.cordova.getActivity().getPackageName()));
			} else if (actionToOpen.equals("sync")) {
				intent = new Intent(android.provider.Settings.ACTION_SYNC_SETTINGS);
			} else if (actionToOpen.equals("usage")) {
				intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
			} else if (actionToOpen.equals("user_dictionary")) {
				intent = new Intent(android.provider.Settings.ACTION_USER_DICTIONARY_SETTINGS);
			} else if (actionToOpen.equals("voice_input")) {
				intent = new Intent(android.provider.Settings.ACTION_VOICE_INPUT_SETTINGS);
			} else if (actionToOpen.equals("wifi_ip")) {
				intent = new Intent(android.provider.Settings.ACTION_WIFI_IP_SETTINGS);
			} else if (actionToOpen.equals("wifi")) {
				intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
			} else if (actionToOpen.equals("wireless")) {
				intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
			} else {
				status = PluginResult.Status.INVALID_ACTION;
				callbackContext.sendPluginResult(new PluginResult(status, result));
				return false;
			}
			
			if (args.length() > 1 && args.getBoolean(1)) {
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			}
		}
               
        this.cordova.getActivity().startActivity(intent);
        
        callbackContext.sendPluginResult(new PluginResult(status, result));
        return true;
    }
}

