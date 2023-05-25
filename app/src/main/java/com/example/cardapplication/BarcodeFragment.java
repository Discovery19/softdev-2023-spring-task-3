package com.example.cardapplication;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.IOException;

public class BarcodeFragment extends Fragment {
    String code;
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    ImageView imageView;
    ImageButton imageButton;
    Button button;

    public BarcodeFragment(String string) {
        this.code = string;
    }

    public BarcodeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barcode, container, false);
        imageView = view.findViewById(R.id.imageView);
        imageButton = view.findViewById(R.id.barcode_close_button);
        try {
            barcode(code);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(BarcodeFragment.this).commit();
            }
        });
        return view;
    }

    private void barcode(String code) throws WriterException {
        BitMatrix bitMatrix = multiFormatWriter.encode(code, BarcodeFormat.CODE_128, 800, 600);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        imageView.setImageBitmap(bitmap);

    }
}