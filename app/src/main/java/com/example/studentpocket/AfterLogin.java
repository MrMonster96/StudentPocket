package com.example.studentpocket;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AfterLogin extends AppCompatActivity {
    TextView nametext;
    GoogleSignInClient mGoogleSignClient;
    CircleImageView circularImageView;
    Button Signout;
    private static final int REQUEST_CAMERA = 125 ;
    private static final int REQUESR_STORAGE = 225 ;
    private static final int REQUEST_CONTACTS = 325 ;

    private static final int TEXT_CAMERA = 1 ;
    private static final int TEXT_STORAGE = 2 ;
    private static final int TEXT_CONTACTS = 3 ;
    private static final int REQUEST_GROUP_PERMISSION = 425;
    private PermissionUtils permissionUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
    nametext = findViewById(R.id.name);
    Signout = findViewById(R.id.logout);

    permissionUtils = new PermissionUtils(this);

    circularImageView=findViewById(R.id.circular_imag);


        GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignClient = GoogleSignIn.getClient(this,googleSignInOptions);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(AfterLogin.this);
        if (account != null)
        {
            String PersonName = account.getDisplayName();
            Uri personPhoto = account.getPhotoUrl();
            nametext.setText(PersonName);
           Glide.with(AfterLogin.this).load(personPhoto).into(circularImageView);


        }
        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });









    }
    private void signOut() {
        mGoogleSignClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AfterLogin.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AfterLogin.this, LoginActivity.class));
                        finish();
                    }
                });
    }

    @Override
    public void onBackPressed() {
       AlertDia();

    }
    public void AlertDia()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirm Exit .... !!!");
        alertDialogBuilder.setMessage("Are you sure, You want to Logout");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                signOut();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    protected void onStart() {
        Toast.makeText(AfterLogin.this, "Permission", Toast.LENGTH_SHORT).show();
        requestAllPermission();

        super.onStart();
    }
      private int checkPermission(int permission)
    {
        int status = PackageManager.PERMISSION_DENIED;

        switch (permission)
        {
            case TEXT_CAMERA:
                status = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                break;
            case TEXT_STORAGE:
                status= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case TEXT_CONTACTS:
                status = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS);
                break;

        }


        return status;
    }
    private void requestPermission(int permission)
    {
        switch (permission)
        {
            case TEXT_CAMERA:
                ActivityCompat.requestPermissions(AfterLogin.this,new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
                break;
            case TEXT_STORAGE:
                ActivityCompat.requestPermissions(AfterLogin.this,new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },REQUESR_STORAGE);
                break;
            case TEXT_CONTACTS:

                ActivityCompat.requestPermissions(AfterLogin.this
                ,new String[]{Manifest.permission.READ_CONTACTS},REQUEST_CONTACTS);
                break;
        }

    }
    private void showPermissionExplanation(final int permission)
    {
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       if (permission == TEXT_CAMERA)
       {
           builder.setTitle("Camera Permission Needed..");
           builder.setMessage("This app need to access your device Camera .. Please allow");

       }
       else if (permission == TEXT_CONTACTS)
       {
           builder.setTitle("Contact Permission Needed...");
           builder.setMessage("This App need to access your contacts ... Please allow");


       }
       else if (permission == TEXT_STORAGE)
       {
           builder.setTitle("Storage Permission Needed...");
           builder.setMessage("This App need to access your Storage ... Please allow");
       }

       builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               if (permission == TEXT_CAMERA)
                   requestPermission(TEXT_CAMERA);
               else if (permission == TEXT_CONTACTS)
                   requestPermission(TEXT_CONTACTS);
               else if (permission == TEXT_STORAGE)
                   requestPermission(TEXT_STORAGE);
           }
       });

       builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
           }
       });
       AlertDialog alertDialog =builder.create();
       alertDialog.show();

    }

    private void requestGroupPermission(ArrayList<String> permission)
    {
        String[] permissionList = new String[permission.size()];
        permission.toArray(permissionList);

        ActivityCompat.requestPermissions(AfterLogin.this,permissionList,REQUEST_GROUP_PERMISSION);


    }

    public void requestAllPermission()
    {
        ArrayList<String> permissionsNeeded = new ArrayList<>();
        ArrayList<String> permissionsAvailable = new ArrayList<>();
        permissionsAvailable.add(Manifest.permission.READ_CONTACTS);
        permissionsAvailable.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionsAvailable.add(Manifest.permission.CAMERA);
        for (String permission : permissionsAvailable)
        {
            if (ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED)
                permissionsNeeded.add(permission);

        }
        requestGroupPermission(permissionsNeeded);

    }

    public void onRequestPermissionResult()
    {}


}
