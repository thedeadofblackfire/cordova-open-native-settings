var NativeSettings = function() {
};

NativeSettings.open = function(setting, onsucess, onfail) {
	var settings = (typeof setting === 'string' || setting instanceof String) ? [setting] : setting;
	cordova.exec(onsucess, onfail, "NativeSettings", "open", settings);
};

NativeSettings.openCustom = function(setting, onsucess, onfail) {
	var settings = (typeof setting === 'string' || setting instanceof String) ? [setting] : setting;
	cordova.exec(onsucess, onfail, "NativeSettings", "openCustom", settings);
};

NativeSettings.openCustomComponent = function(setting, onsucess, onfail) {
	var settings = (typeof setting === 'string' || setting instanceof String) ? [setting] : setting;
	cordova.exec(onsucess, onfail, "NativeSettings", "openCustomComponent", settings);
};

module.exports = NativeSettings;