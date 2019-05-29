package com.neocean.app.uivertifycode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.neocean.app.neoceansoftuivertifycode.VertifyCodeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((VertifyCodeView) findViewById(R.id.vc_line)).setVertifyCodeListener(new VertifyCodeView.VertifyCodeListener() {
            @Override
            public void onInputFinish(String inputContent) {
                Toast.makeText(MainActivity.this, "" + inputContent, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
