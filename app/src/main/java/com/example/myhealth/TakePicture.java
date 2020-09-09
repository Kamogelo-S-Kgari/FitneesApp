package com.example.myhealth;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class TakePicture extends AppCompatActivity {

    //Declared variables:
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;

    Uri image_uri;

    private ImageView imageView;
    private Button buttonCapture, buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        imageView = findViewById(R.id.camera_view);
        buttonCapture = findViewById(R.id.upload_image);
        buttonHome = findViewById(R.id.back);

        buttonCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //If system OS is >= Oreo, request runtime permission:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    if(checkSelfPermission(Manifest.permission.CAMERA)==
                            PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                                    PackageManager.PERMISSION_DENIED){

                        //Permission not enabled, request it:
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

                        //Show popup to request permissions:
                        requestPermissions(permission, PERMISSION_CODE);
                    }else{

                        //Permission already granted:
                        openCamera();
                    }
                }else{

                    //System os < Oreo
                    openCamera();
                }
            }

            });

        // Returns user back to the HomePage activity:
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TakePicture.this, HomePage.class));
                finish();
            }
        });
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        //Camera intent:
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){

                    //Permission from popup was granted:
                    openCamera();

                }else{
                    //Permission from popup was denied:
                    Toast.makeText(TakePicture.this, "Permission denied... Try again", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            //Set the image captured to our ImageView
            imageView.setImageURI(image_uri);
        }
    }
}

