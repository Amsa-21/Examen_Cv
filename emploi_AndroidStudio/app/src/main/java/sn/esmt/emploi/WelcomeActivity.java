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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sn.esmt.emploi.httpConfig.Api;
import sn.esmt.emploi.httpConfig.CVsResponse;
import sn.esmt.emploi.tools.MyAdapterCV;

public class WelcomeActivity extends AppCompatActivity {

    private ListView list;
    private ArrayList<CVsResponse> cvs = new ArrayList<CVsResponse>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        list = (ListView) findViewById(R.id.listCV);
        Button btnAdd = (Button) findViewById(R.id.btnadd);
        Button btnBack = (Button) findViewById(R.id.btnback);

        chargerListe();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(WelcomeActivity.this, DetailsActivity.class);
                intent.putExtra("id", cvs.get(i).getId());
                startActivity(intent);
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajouter();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retour();
            }
        });
    }

    private void ajouter() {
        Intent i = new Intent(WelcomeActivity.this, AddCVActivity.class);
        startActivity(i);
        finish();
    }
    private void retour() {
        Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
    private void chargerListe() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<ArrayList<CVsResponse>> call = api.all();

        call.enqueue(new Callback<ArrayList<CVsResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<CVsResponse>> call, Response<ArrayList<CVsResponse>> response) {
                if (response.isSuccessful()) {
                    response.body().stream().forEach(cVsResponse -> cvs.add(cVsResponse));
                    if(cvs.isEmpty()) {
                        Log.d("Response", "Liste vide");
                        annonce();
                    } else {
                        Log.d("Response", cvs.get(0).getEmail());
                        MyAdapterCV adpt = new MyAdapterCV(WelcomeActivity.this, cvs);
                        Log.d("Debbugage" , "Chargement de liste réussi !");
                        list.setAdapter(adpt);
                    }
                } else {
                    Log.d("Error message exception", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CVsResponse>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
    private void annonce() {
        AlertDialog dialog = new AlertDialog.Builder(WelcomeActivity.this).create();
        dialog.setTitle("Notification");
        dialog.setMessage("Aucun CV dans la liste !");
        dialog.setButton(Dialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }
}