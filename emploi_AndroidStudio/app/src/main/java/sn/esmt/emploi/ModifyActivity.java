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

public class ModifyActivity extends AppCompatActivity {

    private EditText nomtxt, prenomtxt, agetxt, adressetxt, emailtxt, telephonetxt, specialitetxt, niveauEtudetxt, experienceProfessionnelletxt;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        Button save = (Button) findViewById(R.id.modifyButton);
        Button back = (Button) findViewById(R.id.backButtonM);

        nomtxt = (EditText) findViewById(R.id.editTextNomM);
        prenomtxt = (EditText) findViewById(R.id.editTextPrenomM);
        agetxt = (EditText) findViewById(R.id.editTextAgeM);
        adressetxt = (EditText) findViewById(R.id.editTextAdresseM);
        emailtxt = (EditText) findViewById(R.id.editTextEmailM);
        telephonetxt = (EditText) findViewById(R.id.editTextTelephoneM);
        specialitetxt = (EditText) findViewById(R.id.editTextSpecialiteM);
        niveauEtudetxt = (EditText) findViewById(R.id.editTextNiveauM);
        experienceProfessionnelletxt = (EditText) findViewById(R.id.editTextExperienceM);

        charger();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifier();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retour();
            }
        });
    }

    private void retour() {
        Intent i = new Intent(ModifyActivity.this, DetailsActivity.class);
        i.putExtra("id", id);
        startActivity(i);
        finish();
    }
    private void charger() {
        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            id = extra.getInt("id");
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<CVsResponse> call = api.findById(id);

        call.enqueue(new Callback<CVsResponse>() {
            @Override
            public void onResponse(Call<CVsResponse> call, Response<CVsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("Response ID", response.body().getEmail());

                    prenomtxt.setText(response.body().getPrenom());
                    nomtxt.setText(response.body().getNom());
                    agetxt.setText(String.valueOf(response.body().getAge()));
                    telephonetxt.setText(response.body().getTelephone());
                    emailtxt.setText(response.body().getEmail());
                    adressetxt.setText(response.body().getAdresse());
                    niveauEtudetxt.setText(response.body().getNiveauEtude());
                    specialitetxt.setText(response.body().getSpecialite());
                    experienceProfessionnelletxt.setText(response.body().getExperienceProfessionnelle());

                } else {
                    Log.d("Error message exception", response.toString());
                }
            }

            @Override
            public void onFailure(Call<CVsResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
    private void modifier() {
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

        CVsResponse cv = new CVsResponse(id, nom, prenom, age, adresse, email, telephone, specialite, niveauEtude, experienceProfessionnelle);
        Call<CVsResponse> call = api.update(cv);

        call.enqueue(new Callback<CVsResponse>() {
            @Override
            public void onResponse(Call<CVsResponse> call, Response<CVsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("Response", response.body().getEmail());
                    annonce();
                } else {
                    Log.d("Error message exception", response.toString());
                    Toast.makeText(getApplicationContext(),"Echec de la modifiation !",Toast.LENGTH_LONG).show();
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
        AlertDialog dialog = new AlertDialog.Builder(ModifyActivity.this).create();
        dialog.setTitle("Notification");
        dialog.setMessage("Modification réussie !");
        dialog.setButton(Dialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(ModifyActivity.this, DetailsActivity.class);
                i.putExtra("id", id);
                startActivity(i);
                finish();
            }
        });
        dialog.show();
    }
}