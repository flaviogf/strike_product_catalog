package br.com.flaviogf.strikeproductcatalog.extensions;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

public class ActivityExtensions {
    public static boolean hasPermissions(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }

        return true;
    }
}
