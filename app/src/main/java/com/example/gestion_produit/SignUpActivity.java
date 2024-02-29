package com.example.gestion_produit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {
    private UserService userService;
    private EditText editTextUsername, editTextPassword, editTextEmail, editTextHeight, editTextWeight;
    private DatePicker datePickerBirthdate;
    private ImageView imageViewProfilePicture;
    private static final int CAMERA_REQUEST_CODE = 1;

    private boolean isPasswordVisible = false;
    private TextView textViewShowPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_SECURE, android.view.WindowManager.LayoutParams.FLAG_SECURE);


        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        datePickerBirthdate = findViewById(R.id.datePickerBirthdate);
        imageViewProfilePicture = findViewById(R.id.imageViewProfilePicture);
        Button signInButton = findViewById(R.id.btnSignIn);
        textViewShowPassword = findViewById(R.id.textViewShowPassword);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.145:5000")
                .build();

        userService = retrofit.create(UserService.class);
    }

    public void pickProfilePicture(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap profileImage = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            imageViewProfilePicture.setImageBitmap(profileImage);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            profileImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
        }
    }

    byte[] byteArray;

    public void signUp(View view) throws IOException {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        float height = Float.parseFloat(editTextHeight.getText().toString().trim());
        float weight = Float.parseFloat(editTextWeight.getText().toString().trim());

        int year = datePickerBirthdate.getYear();
        int month = datePickerBirthdate.getMonth();
        int day = datePickerBirthdate.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String birthdate = sdf.format(calendar.getTime());

        if (byteArray != null) {
            RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), byteArray);
            MultipartBody.Part profilePicturePart = MultipartBody.Part.createFormData("profilePicture", "image.jpg", imageBody);

            Call<ResponseBody> call = userService.signUp(
                    RequestBody.create(MediaType.parse("text/plain"), username),
                    RequestBody.create(MediaType.parse("text/plain"), password),
                    RequestBody.create(MediaType.parse("text/plain"), birthdate),
                    RequestBody.create(MediaType.parse("text/plain"), email),
                    RequestBody.create(MediaType.parse("text/plain"), String.valueOf(height)),
                    RequestBody.create(MediaType.parse("text/plain"), String.valueOf(weight)),
                    profilePicturePart
            );
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Sign-up successful!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Sign-up failed", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("E", t.toString());
                    Toast.makeText(SignUpActivity.this, "Sign-up failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void togglePasswordVisibility(View view) {
        if (isPasswordVisible) {
            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            textViewShowPassword.setText("Show");
        } else {
            editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            textViewShowPassword.setText("Hide");
        }

        isPasswordVisible = !isPasswordVisible;
        editTextPassword.setSelection(editTextPassword.getText().length());
    }
}
