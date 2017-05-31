package com.example.rxbro.contactapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener {
    Button camera;
    Button save;
    Button ListActivity;
    EditText FName;
    EditText LName;
    DataManager dm;
    private static final int CAMERA_REQUEST = 123;
    private ImageView imageView;
    String mCurrentPhotoPath;
    private Uri imageUri = Uri.EMPTY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DataManager(this);
        camera = (Button)findViewById(R.id.camera);
        save = (Button)findViewById(R.id.Save);
        ListActivity = (Button)findViewById(R.id.ListActivity);
        FName = (EditText)findViewById(R.id.Fname);
        LName = (EditText)findViewById(R.id.Lname);
        camera.setOnClickListener(this);
        save.setOnClickListener(this);
        ListActivity.setOnClickListener(this);
    }
    public void showData(Cursor c) {
        while (c.moveToNext()) {
            Log.i(c.getString(1), c.getString(2));
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                break;
            case R.id.Save:
                FName.getText().toString();
                LName.getText().toString();
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File photoFile = null;
                try {
                    photoFile = createImageFile();

                } catch (IOException e) {
                    Log.e("error", "Error creating the file.");
                }
                // If file was created successfully
                if (photoFile != null) {
                    imageUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }

                break;
            case R.id.ListActivity:
                Intent intent = new Intent(this, MyListActivity.class);
                startActivity(intent);
                break;
            default:
                return;
        }
    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
//
//            Bitmap bitmap;
//            try {
//                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream());
//
//            } catch (Exception e) {
//
//            }
//            imageView.setImageBitmap(photo);
//        }
//    }
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
    public void onDestroy() {
        super.onDestroy();
        BitmapDrawable bd = (BitmapDrawable)imageView.getDrawable();
        bd.getBitmap().recycle();
        imageView.setImageBitmap(null);
    }

   }