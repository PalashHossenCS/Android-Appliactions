package com.example.signuplogin;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DetectFragment extends Fragment {

    ActivityResultLauncher<Intent> activityResultLauncher;
    ImageView pickImage;
    Button galleryButton, cameraButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view_ = inflater.inflate(R.layout.fragment_detect, container, false);

        pickImage = view_.findViewById(R.id.pickImageId);
        cameraButton = view_.findViewById(R.id.cameraButtonId);
        galleryButton = view_.findViewById(R.id.galleryButtonId);

        //take photos with CAMERA
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData()!=null)
                {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap =(Bitmap) bundle.get("data");
                    pickImage.setImageBitmap(bitmap);
                }
            }
        });
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getActivity().getPackageManager())!=null)
                {
                    activityResultLauncher.launch(intent);
                }
                else {
                    Toast.makeText(getContext(),"There is no app that support this action",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //choose from GALLERY
        final ActivityResultLauncher<Intent> launcher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode()== RESULT_OK && result.getData()!=null){
                        Uri photoUri = result.getData().getData();
                        pickImage.setImageURI(photoUri);
                    }
                }
        );
        galleryButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(intent);
        });

        return view_;
    }
}