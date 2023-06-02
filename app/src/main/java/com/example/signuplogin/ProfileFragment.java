package com.example.signuplogin;

import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ProfileFragment extends Fragment {

    TextView textDate;
    EditText editDate;
    DatePickerDialog.OnDateSetListener setListener;
    ImageView profilePic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view_ = inflater.inflate(R.layout.fragment_profile, container, false);

        //profile pic
        profilePic = view_.findViewById(R.id.profilePicId);
        final ActivityResultLauncher<Intent> launcher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode()== RESULT_OK && result.getData()!=null){
                        Uri photoUri = result.getData().getData();
                        profilePic.setImageURI(photoUri);
                    }
                }

        );
        profilePic.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(intent);
        });


        //date picker
        try {

            editDate = view_.findViewById(R.id.doBId);
            Calendar calendar = Calendar.getInstance();
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH);
            final int day = calendar.get(Calendar.DAY_OF_MONTH);

            editDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.show();
                }
            });

            setListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    i1 += 1;
                    String s = "" + day + "/" + i1 + "/" + i + "" ;
                    editDate.setText(s);
                }
            };


        } catch (Exception e) {
            Toast.makeText(getContext(), "Stopped", Toast.LENGTH_SHORT).show();
        }

        return view_ ;
    }
}