package com.robotack.robogamification.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Base64;

import com.google.gson.Gson;
import com.robotack.robogamification.R;
import com.robotack.robogamification.helpers.ApiConstants;
import com.robotack.robogamification.helpers.LanguageHelper;
import com.robotack.robogamification.models.AlertClickListener;

import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.robotack.robogamification.helpers.PrefConstant.custumerID;
import static com.robotack.robogamification.helpers.PrefConstant.identifierValue;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Utils {


    public void showSettingsAlertCustomClick(Context context, String message, AlertClickListener alertClickListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("");
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(Html.fromHtml(context.getResources().getString(R.string.ok__)), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertClickListener.onAlertClick();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
    public void showSettingsAlert(Context context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("");
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(Html.fromHtml(context.getResources().getString(R.string.ok__)), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void setUserID(String userID, Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(custumerID, userID);
        edit.commit();
    }

    public static void putSharedPreferencesObject(Context context, String key, Object shared_object) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.remove(key).commit();
        Gson gson = new Gson();
        String json = gson.toJson(shared_object);
        edit.putString(key, json);
        edit.commit();
    }


    public static Object getSharedPreferencesObject(Context context, String key, Class neededClass) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(key, "");
        Object obj = gson.fromJson(json, neededClass);
        return obj;
    }

    public void setIdentifierValue(String userID, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(identifierValue, userID);
        edit.commit();
    }
    public void updateLangauge(Context context)
    {
        Locale locale = new Locale(LanguageHelper.getCurrentLanguage(context));
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

    }

    public String getUserId(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return encryptWithoutTime(preferences.getString(custumerID, ""),context);
    }
    public  String encryptWithoutTime(String value, Context context) {
        String plainText = value;
        String escapedString;
        try {
            byte[] key = ApiConstants.KEY_AES.getBytes("UTF-8");
            byte[] ivs = ApiConstants.IV_AES.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivs);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, paramSpec);
            escapedString = Base64.encodeToString(cipher.doFinal(plainText.getBytes("UTF-8")), Base64.DEFAULT).trim();
            escapedString = escapedString.replace("+", "__plus__");
            escapedString = escapedString.replace("/", "__slash__");
            escapedString = escapedString.replace("%", "__perc__");
            escapedString = escapedString.replace("=", "__equal__");
            return escapedString;
        } catch (Exception e) {

            return value;
        }
    }
    public String getIdentifierValue(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(identifierValue, "");
    }

    public static Typeface SetTFace(Context context) {
        Typeface font;
        font = Typeface.createFromAsset(context.getAssets(), "capitalbank-medium.ttf");
        return font;
    }

    public String getDate(long time) {
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        } catch (Exception e) {
            return "";
        }
        try {
            sdf.format(new Date(time));
            return sdf.format(time);
        } catch (Exception e) {
            return "";
        }

    }

//    public boolean isGMSAvailable(Context context) {
//        try {
//
//
////            GoogleApiAvailability gms = GoogleApiAvailability.getInstance();
////            int isGMS = gms.isGooglePlayServicesAvailable(context);
////            return isGMS == com.google.android.gms.common.ConnectionResult.SUCCESS;
//        } catch (Exception e) {
//            return true;
//        }
//
//    }
}
