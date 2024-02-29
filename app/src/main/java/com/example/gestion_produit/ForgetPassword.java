package com.example.gestion_produit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.Executor;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ForgetPassword extends AppCompatActivity {
    UserService userService;
    String userEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.145:5000")
                .build();

        userService = retrofit.create(UserService.class);

        Button submitButton = findViewById(R.id.submitBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Appeler l'authentification par empreinte digitale
                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Please verify")
                        .setDescription("User authentication is required to proceed")
                        .setNegativeButtonText("Cancel")
                        .build();

                getPrompt().authenticate(promptInfo);
            }
        });
    }

    private BiometricPrompt getPrompt() {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyUser(errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                notifyUser("Authentication Succeeded!!");
                // L'authentification par empreinte digitale a réussi, générez le code et affichez la boîte de dialogue
                String verificationCode = generateVerificationCode();
                userEmail = getEmailFromApi(); // Remplacez par la méthode pour obtenir l'email associé à l'utilisateur

                // Envoi du code par email
                displayCodeInConsole(verificationCode);
                // Affichage de la boîte de dialogue pour saisir le code
                showVerificationCodePrompt(verificationCode);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                notifyUser("Authentication Failed!!");
            }
        };

        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, callback);
        return biometricPrompt;
    }

    private String getEmailFromApi() {
        // Appeler votre API ou utiliser vos méthodes pour obtenir l'email associé à l'utilisateur
        // Remplacez cette méthode par la manière dont vous récupérez l'email de l'utilisateur
        return "user@example.com";
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 10000 + random.nextInt(90000);
        return String.valueOf(code);
    }

    private void displayCodeInConsole(String code) {
        System.out.println("Received code in ForgetPassword activity: " + code);

        Call<ResponseBody> call = userService.sendCode(userEmail, code);

        // Enqueue the call
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Handle success
                    Toast.makeText(ForgetPassword.this, "Email sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failure
                    System.out.println(response.toString());
                    Toast.makeText(ForgetPassword.this, "Email Failed to send", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle failure
                Toast.makeText(ForgetPassword.this, "Email Failed to send: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showVerificationCodePrompt(String code) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Verification Code");
        builder.setMessage("Please enter the verification code sent to your email:");

        final EditText verificationCodeInput = new EditText(this);
        builder.setView(verificationCodeInput);

        builder.setPositiveButton("Submit", (dialog, which) -> {
            String enteredCode = verificationCodeInput.getText().toString().trim();
            if (enteredCode.equals(code)) {
                showNewPasswordPrompt();
            } else {
                Toast.makeText(ForgetPassword.this, "Incorrect verification code", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void showNewPasswordPrompt() {
        AlertDialog.Builder newPasswordBuilder = new AlertDialog.Builder(this);
        newPasswordBuilder.setTitle("New Password");
        newPasswordBuilder.setMessage("Please enter the verification code and your new password:");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText newPasswordInput = new EditText(this);
        newPasswordInput.setHint("New Password");
        layout.addView(newPasswordInput);

        newPasswordBuilder.setView(layout);

        newPasswordBuilder.setPositiveButton("Submit", (dialog, which) -> {
            String newPassword = newPasswordInput.getText().toString().trim();
            updatePassword(userEmail, newPassword);
        });

        newPasswordBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        newPasswordBuilder.show();
    }

    private void updatePassword(String email, String newPassword) {
        // Call the API to update the password
        Call<ResponseBody> call = userService.updatePassword(email, newPassword);

        // Enqueue the call
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Handle success
                    Toast.makeText(ForgetPassword.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println(response.body());
                    // Handle failure
                    Toast.makeText(ForgetPassword.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle failure
                Toast.makeText(ForgetPassword.this, "Failed to update password: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void notifyUser(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
