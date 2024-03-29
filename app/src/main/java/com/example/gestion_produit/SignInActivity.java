package com.example.gestion_produit;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestion_produit.model.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonSignIn;
    private Button buttonSignUp;
    private TextView textForgetPassword;
    private UserService userService;
    private TextView textViewShowPassword;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_SECURE, android.view.WindowManager.LayoutParams.FLAG_SECURE);

        textForgetPassword = findViewById(R.id.textForgetPassword);
        textViewShowPassword = findViewById(R.id.textViewShowPassword);

        textForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.145:5000")
                .build();
        userService = retrofit.create(UserService.class);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                signIn(username, password);

            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signIn(String username, String password) {
        Call<ResponseBody> call = userService.signIn(username, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONObject userObject = jsonObject.getJSONObject("user");
                        int id = userObject.getInt("id");
                        String username = userObject.getString("username");
                        String birthdate = userObject.getString("birthdate");
                        String email = userObject.getString("email");
                        double height = userObject.getDouble("height");
                        double weight = userObject.getDouble("weight");
                        JSONArray profilePictureDataArray = userObject.getJSONObject("profile_picture").getJSONArray("data");
                        byte[] profilePictureData = new byte[profilePictureDataArray.length()];
                        for (int i = 0; i < profilePictureDataArray.length(); i++) {
                            profilePictureData[i] = (byte) profilePictureDataArray.getInt(i);
                        }
                        User user = new User(id, username, birthdate, email, height, weight, profilePictureData);
                        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                        intent.putExtra("user", user);
                        Log.e("USER", user.toString());
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.e("TAG", "Error parsing response: ", e);
                        showToast("An error occurred");
                    }
                } else {
                    // Handle error
                    showToast("Invalid username or password");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle failure
                showToast("Sign-in failed: " + t.getMessage());
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
