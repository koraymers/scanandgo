package com.example.k_app.fragmets;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.k_app.R;
import com.example.k_app.qrgenerator.QRGContents;
import com.example.k_app.qrgenerator.QRGEncoder;

import java.util.Random;

public class GenerateFragment extends Fragment  {
    Random rand = new Random();
    int rand_int1 = rand.nextInt(10);

    ImageView qrCodeIV ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_generate, container, false);
        qrCodeIV=contentView.findViewById(R.id.idIVQrcode);
        QRGEncoder qrgEncoder = new QRGEncoder(rand_int1+"", null, QRGContents.Type.TEXT, 300);
        try {
            // getting our qrcode in the form of bitmap.
            Bitmap bitmap = qrgEncoder.getBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            qrCodeIV.setImageBitmap(bitmap);
        } catch (Exception e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());}
        return contentView;
    }


}
