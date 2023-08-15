package com.example.signuplogin;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class Post extends AppCompatActivity {


    EditText imgname;
    ImageView imageView;
    Button pickImage, upload;
    DatabaseReference databaseReference, userRef;
    StorageReference storageReference;
    Uri imageUri;
    StorageTask uploadTask;

    SharedPreferences mSharedPref;


    private int PICK_IMAGE_REQUEST = 1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_review);

        Toast.makeText(this, "Mandatory to upload profile image before post,comment", Toast.LENGTH_SHORT).show();

        imgname = (EditText) findViewById(R.id.title);

        upload = (Button) findViewById(R.id.uploadId);

        imageView = (ImageView) findViewById(R.id.imageId);


        mSharedPref = getSharedPreferences("SharedPref", MODE_PRIVATE);
        String username = mSharedPref.getString("userName", "null");

        databaseReference = FirebaseDatabase.getInstance().getReference("post");
        storageReference = FirebaseStorage.getInstance().getReference("post_image");



        //In order to show username
        userRef = FirebaseDatabase.getInstance().getReference().child("users");


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Post.this, "clicked", Toast.LENGTH_SHORT).show();
                saveData(username);
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == this.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Picasso.get().load(imageUri).into(imageView);

        }
    }

    //Image Extension
    public String getFileExtension(Uri imageUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }

    private void saveData(String username) {
        String imageName = imgname.getText().toString().trim();

        if (imageName.isEmpty()) {
            imgname.setError("Enter a Title");
        } else {
            StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Post.this, "Uploaded", Toast.LENGTH_SHORT).show();

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Upload upload = new Upload(imageName, uri.toString(), username);
                                    String uploadId = databaseReference.push().getKey();
                                    databaseReference.child(uploadId).setValue(upload);
                                    nextActivity();
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Post.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void nextActivity() {
        imgname.setText("");
        Intent intent = new Intent(Post.this, Social.class);
        startActivity(intent);
    }
}
