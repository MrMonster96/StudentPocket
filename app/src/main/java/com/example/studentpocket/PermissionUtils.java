package com.example.studentpocket;

import android.content.Context;
import android.content.SharedPreferences;

public class PermissionUtils {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public PermissionUtils(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.permissiion_preferences),Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void updatePermissionPreference(String permission)
    {
        switch (permission)
        {
            case "camera":
                editor.putBoolean(context.getString(R.string.permissiion_camera),true);
                editor.commit();
                break;
            case "storage":
                editor.putBoolean(context.getString(R.string.permissiion_storage),true);
                editor.commit();
                break;
            case "contacts":
                editor.putBoolean(context.getString(R.string.permissiion_contacts),true);
                editor.commit();
                break;
        }


    }

    public boolean checkPermissionPreferences(String permission)
    {
       boolean isShown = false;
       switch (permission)
       {
           case "camera":
               isShown = sharedPreferences.getBoolean(context.getString(
                       R.string.permissiion_camera
               ),false);
               break;
           case "storage":
               isShown = sharedPreferences.getBoolean(context.getString(R.string.permissiion_storage),false);
                break;
           case "contacts":
               isShown = sharedPreferences.getBoolean(context.getString(R.string.permissiion_contacts),false);
               break;

       }

       return isShown;
    }



}

