package com.example.signuplogin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CommentPanel extends AppCompatActivity {

    DatabaseReference commentRef,userRef;

    private List<ModelClass> modelClassList;
    private RecyclerView recyclerView;

    private EditText edtComment;
    private Button submitComment;
    String postkey;
    SharedPreferences mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_panel);
        Toast.makeText(this, "Mandatory to upload profile image before post,comment", Toast.LENGTH_SHORT).show();
        edtComment = findViewById(R.id.commentsssssId);
        submitComment = findViewById(R.id.commentBtnId);
        recyclerView = findViewById(R.id.recycleVwComments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postkey = getIntent().getStringExtra("postkey");

        commentRef = FirebaseDatabase.getInstance().getReference().child("upload").child(postkey).child("Comments");
//      userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        userRef = FirebaseDatabase.getInstance().getReference().child("users");



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mSharedPref = getSharedPreferences("SharedPref", MODE_PRIVATE);
        final String userId = mSharedPref.getString("userName", "null");;

        submitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
//                            String userimage = snapshot.child("dp").getValue().toString();
                            String username = snapshot.child("username").getValue().toString();
                            commenstDetails(username);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }

    private void commenstDetails(String username){
        String mComment = edtComment.getText().toString();


        if (mComment.isEmpty()){
            edtComment.setError("Fill it up");
            edtComment.requestFocus();
            return;

        }
        else  {
            Calendar dateValue = Calendar.getInstance();
            /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yy");
            String cdate = dateFormat.format(dateValue.getTime());*/

            String cdate = new SimpleDateFormat("dd-MM-yy", Locale.getDefault()).format(new Date());

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String ctime = timeFormat.format(dateValue.getTime());


            ModelClass modelClass = new ModelClass(username, mComment, cdate, ctime);

            String key = commentRef.push().getKey();
            commentRef.child(key).setValue(modelClass);
            edtComment.setText("");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ModelClass> options =
                new FirebaseRecyclerOptions.Builder<ModelClass>()
                        .setQuery(commentRef, ModelClass.class)
                        .build();

        FirebaseRecyclerAdapter<ModelClass,CommentsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelClass, CommentsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CommentsViewHolder holder, int position, @NonNull ModelClass model) {
                holder.comment.setText(model.getComment());
                holder.user.setText(model.getUsername());
                holder.date.setText(model.getDate());
                holder.time.setText(model.getTime());

            }

            @NonNull
            @Override
            public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.samplelist_layout,parent,false);
                return new CommentsViewHolder(view);
            }
        };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
