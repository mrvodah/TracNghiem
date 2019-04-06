package develop.com.tracnghiem;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PrefUtil {

    private static final String TAG = "PrefUtil";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private static SharedPreferences getInsSharePref(Context context) {
        if (preferences == null)
            preferences = context.getSharedPreferences("app_preference", Context.MODE_PRIVATE);

        return preferences;
    }

    private static SharedPreferences.Editor getInsPrefEdit(Context context) {
        if (editor == null)
            editor = getInsSharePref(context).edit();

        return editor;
    }

    public static void saveString(Context context, String key, String value) {
        getInsPrefEdit(context).putString(key, value);
        getInsPrefEdit(context).apply();
    }

    public static String getString(Context context, String key, String defaultValue) {
        return getInsSharePref(context).getString(key, defaultValue);
    }

    public static void saveInt(Context context, String key, int value) {
        getInsPrefEdit(context).putInt(key, value);
        getInsPrefEdit(context).apply();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getInsSharePref(context).getInt(key, defaultValue);
    }

}
