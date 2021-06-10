package com.example.k_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.k_app.database.QrCodeDatabase;
import com.example.k_app.database.QrCodeModel;
import com.example.k_app.fragmets.CardFragment;
import com.example.k_app.fragmets.GenerateFragment;
import com.example.k_app.fragmets.ListFragment;
import com.example.k_app.fragmets.ScanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {
      public BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.page_1){
                    openScanFragment();
                }else if (item.getItemId() == R.id.page_2){
                    openListFragment();
                }else if (item.getItemId() == R.id.page_3){
                    openCardFragment();
                }
                return true;


            }
        });

        openScanFragment();
    }

    private void openScanFragment(){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, ScanFragment.class, null)
                .commit();
    }

    public void openListFragment(){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, ListFragment.class, null)
                .commit();
    }

    public void openCardFragment(){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, CardFragment.class, null)
                .commit();
    }

    public void openGenerateFragment(){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, GenerateFragment.class, null)
                .commit();
    }

    public void scanCode(){
        IntentIntegrator intentIntegrator=new IntentIntegrator(this);
        intentIntegrator.setCaptureActivity(CaptureAct.class);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scanning");
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result =IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents()!=null){

                //Save to database
                saveQrCodeToDatabase(result.getContents());


                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("See Cart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bottomNavigationView.setSelectedItemId(R.id.page_2);
                        openListFragment();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();


            }
            else{
                Toast.makeText(this,"No Results",Toast.LENGTH_LONG).show();

            }
        } else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }


    private void saveQrCodeToDatabase(String code){
        QrCodeModel qrCodeModel = new QrCodeModel();
        qrCodeModel.code = code;
        QrCodeDatabase.getDatabase(getApplicationContext()).qrCodeDao().insert(qrCodeModel); //qr inserted here
    }
}