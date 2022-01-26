package com.robotack.robogamification.ui.Activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.robotack.robogamification.R;
import com.robotack.robogamification.utilities.Utils;


public class MaintancePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Utils().updateLangauge(this);
        setContentView(R.layout.activity_maintance_page);
        TextView wb = (TextView) findViewById(R.id.wb);
        wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.capitalbank.jo/ar"));
                startActivity(browserIntent);
            }
        });
    }
}