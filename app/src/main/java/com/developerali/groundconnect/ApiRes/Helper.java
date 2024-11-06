package com.developerali.groundconnect.ApiRes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import com.developerali.groundconnect.R;

import java.util.List;

public class Helper {

    public static boolean isChromeCustomTabsSupported(@NonNull final Context context) {
        Intent serviceIntent = new Intent("android.support.customtabs.action.CustomTabsService");
        serviceIntent.setPackage("com.android.chrome");
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentServices(serviceIntent, 0);
        return !resolveInfos.isEmpty();
    }
    public static void openChromeTab(String link, Activity activity){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(activity, R.color.mainColor));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(activity, Uri.parse(link));
    }

    public static void openLink(String url, Activity activity) {
        // Create an intent with ACTION_VIEW and the URL
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        // Check if there is an app that can handle this intent
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        } else {
            // Handle case when there is no app to open the URL
            Toast.makeText(activity, "No application can handle this request. Please install a web browser.", Toast.LENGTH_LONG).show();
        }
    }
}
