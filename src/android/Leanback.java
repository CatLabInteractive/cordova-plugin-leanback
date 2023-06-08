package eu.catlab.cordova.plugins;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONException;
import org.json.JSONArray;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;

public class Leanback extends CordovaPlugin {

	private static Boolean leanbackCapable;
	private static Boolean leanbackOnly;

	private static final String ACTION_HAS_LEANBACK = "hasLeanback";
	private static final String ACTION_HAS_LEANBACK_ONLY = "hasLeanbackOnly";

	@Override
	public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {

		try {
			if (action.equals(ACTION_HAS_LEANBACK)) {
				callbackContext.success(hasLeanback() ? 1 : 0);
				return true;
			} else if (action.equals(ACTION_HAS_LEANBACK_ONLY)) {
				callbackContext.success(isLeanbackOnly() ? 1 : 0);
				return true;
			} else {
				callbackContext.error("Invalid action");
				return false;
			}
		} catch (Exception e) {
			callbackContext.error(e.getMessage());
			return false;
		}
	}

	private boolean hasLeanback() {
      final PackageManager pm = this.cordova.getActivity().getPackageManager();
      return pm.hasSystemFeature("android.software.leanback");
	}

	private boolean isLeanbackOnly() {
      final PackageManager pm = this.cordova.getActivity().getPackageManager();
      return pm.hasSystemFeature("android.software.leanback_only");
	}

}
