package sn.esmt.emploi;

import static sn.esmt.emploi.MainActivity.baseURL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sn.esmt.emploi.httpConfig.Api;
import sn.esmt.emploi.httpConfig.CVsResponse;

public class AddCVActivity extends AppCompatActivity {

    private EditText nomtxt, prenomtxt, agetxt, adressetxt, emailtxt, telephonetxt, specialitetxt, niveauEtudetxt, experienceProfessionnelletxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cv);

        nomtxt = (EditText) findViewById(R.id.editTextNom);
        prenomtxt = (EditText) findViewById(R.id.editTextPrenom);
        agetxt = (EditText) findViewById(R.id.editTextAge);
        adressetxt = (EditText) findViewById(R.id.editTextAdresse);
        emailtxt = (EditText) findViewById(R.id.editTextEmail);
        telephonetxt = (EditText) findViewById(R.id.editTextTelephone);
        specialitetxt = (EditText) findViewById(R.id.editTextSpecialite);
        niveauEtudetxt = (EditText) findViewById(R.id.editTextNiveau);
        experienceProfessionnelletxt = (EditText) findViewById(R.id.editTextExperience);

        Button btnSave = (Button) findViewById(R.id.saveButton);
        Button btnBack = (Button) findViewById(R.id.backButton);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enregistrer();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retour();
            }
        });
    }

    private void retour() {
        Intent i = new Intent(AddCVActivity.this, WelcomeActivity.class);
        startActivity(i);
        finish();
    }
    private void enregistrer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        String nom, prenom, adresse, email, telephone, specialite, niveauEtude, experienceProfessionnelle;
        int age;

        nom = nomtxt.getText().toString();
        prenom = prenomtxt.getText().toString();
        age = Integer.parseInt(agetxt.getText().toString());
        adresse = adressetxt.getText().toString();
        email = emailtxt.getText().toString();
        telephone = telephonetxt.getText().toString();
        specialite = specialitetxt.getText().toString();
        niveauEtude = niveauEtudetxt.getText().toString();
        experienceProfessionnelle = experienceProfessionnelletxt.getText().toString();

        CVsResponse cv = new CVsResponse(0, nom, prenom, age, adresse, email, telephone, specialite, niveauEtude, experienceProfessionnelle);
        Call <CVsResponse> call = api.save(cv);

        call.enqueue(new Callback<CVsResponse>() {
            @Override
            public void onResponse(Call<CVsResponse> call, Response<CVsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("Response", response.body().getEmail());
                    annonce();
                } else {
                    Log.d("Error message exception", response.toString());
                    Toast.makeText(getApplicationContext(),"Echec de l'enregistrement !",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CVsResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Toast.makeText(getApplicationContext(),"Connexion au serveur impossible !",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void annonce() {
        AlertDialog dialog = new AlertDialog.Builder(AddCVActivity.this).create();
        dialog.setTitle("Notification");
        dialog.setMessage("Enregistrement réussi !");
        dialog.setButton(Dialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(AddCVActivity.this, WelcomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        dialog.show();
    }
}