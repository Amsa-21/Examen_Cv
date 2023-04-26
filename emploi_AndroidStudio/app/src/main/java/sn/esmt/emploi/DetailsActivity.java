package sn.esmt.emploi;

import static sn.esmt.emploi.MainActivity.baseURL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sn.esmt.emploi.httpConfig.Api;
import sn.esmt.emploi.httpConfig.CVsResponse;

public class DetailsActivity extends AppCompatActivity {

    private TextView prenomtxt, nomtxt, agetxt, numerotxt, emailtxt, adressetxt, niveautxt, specialitetxt, experiencetxt;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        prenomtxt = (TextView) findViewById(R.id.prenom);
        nomtxt = (TextView) findViewById(R.id.nom);
        agetxt = (TextView) findViewById(R.id.age);
        numerotxt = (TextView) findViewById(R.id.numero);
        emailtxt = (TextView) findViewById(R.id.email);
        adressetxt = (TextView) findViewById(R.id.adresse);
        niveautxt = (TextView) findViewById(R.id.niveau);
        specialitetxt = (TextView) findViewById(R.id.specialite);
        experiencetxt = (TextView) findViewById(R.id.experience);

        Button back = (Button) findViewById(R.id.rtr);
        Button delete = (Button) findViewById(R.id.del);
        Button modify = (Button) findViewById(R.id.mod);

        chargement();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retour();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suppression();
            }
        });
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this, ModifyActivity.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
    }

    private void retour() {
        Intent i = new Intent(DetailsActivity.this, WelcomeActivity.class);
        startActivity(i);
        finish();
    }
    private void suppression() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Confirmer");
        builder.setMessage("Voulez-vous vraiment supprimer ce CV ?");
        builder.setPositiveButton("OUI",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle extra = getIntent().getExtras();
                        if(extra != null) {
                            id = extra.getInt("id");
                        }

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(baseURL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        Api api = retrofit.create(Api.class);

                        Call<Void> call = api.delete(id);

                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    Log.d("Response :", "Success");
                                    annonce();
                                } else {
                                    Log.d("Error message exception", response.toString());
                                    Toast.makeText(getApplicationContext(),"Suppression impossible !",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.d("Error : ", t.getMessage());
                                Toast.makeText(getApplicationContext(),"Connexion au serveur impossible !",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
        builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
    private void annonce() {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Notification");
        alert.setMessage("CV supprimé avec succès !");
        alert.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(DetailsActivity.this, WelcomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        alert.show();
    }
    private void chargement() {
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
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<CVsResponse> call, Response<CVsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("Response ID", response.body().getEmail());

                    prenomtxt.setText(response.body().getPrenom());
                    nomtxt.setText(response.body().getNom());
                    agetxt.setText(response.body().getAge() + " ans");
                    numerotxt.setText(response.body().getTelephone());
                    emailtxt.setText(response.body().getEmail());
                    adressetxt.setText(response.body().getAdresse());
                    niveautxt.setText(response.body().getNiveauEtude());
                    specialitetxt.setText(response.body().getSpecialite());
                    experiencetxt.setText(response.body().getExperienceProfessionnelle());

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
}