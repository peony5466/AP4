package com.example.myappliall4sport;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 100;

    private Button loginBtn;
    private EditText usernameInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison des vues avec les éléments de l'interface utilisateur
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        loginBtn = findViewById(R.id.login_btn);

        // Définition du OnClickListener pour le bouton de connexion
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Demander la permission d'accéder à la caméra lors du clic sur le bouton de connexion
                demanderPermissionCamera();
            }
        });
    }

    // Méthode pour demander la permission d'accès à la caméra
    private void demanderPermissionCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        } else {
            // La permission est déjà accordée, lancer l'activité de scan
            lancerActiviteScan();
        }
    }

    // Gérer la réponse de l'utilisateur à la demande de permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission accordée, lancer l'activité de scan
                lancerActiviteScan();
            } else {
                // Permission refusée, afficher un message d'erreur ou prendre une autre action appropriée
            }
        }
    }

    // Méthode pour lancer l'activité de scan
    private void lancerActiviteScan() {
        Intent intent = new Intent(MainActivity.this, Scan.class);
        startActivity(intent);
    }
}
