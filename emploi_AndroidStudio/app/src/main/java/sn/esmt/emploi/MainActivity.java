package sn.esmt.emploi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public final static String baseURL = "http://172.20.2.221:8008";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                versListe();
            }
        });
    }

    private void versListe() {
        Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(i);
    }
}