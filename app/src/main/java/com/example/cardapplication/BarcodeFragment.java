package com.example.cardapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BarcodeFragment extends Fragment {
    String name;
    String code;
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    ImageView imageView;
    TextView textView;
    ImageButton imageButton;

    public BarcodeFragment(String code,String name) {
        this.code = code;
        this.name=name;
    }

    public BarcodeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barcode, container, false);
        imageView = view.findViewById(R.id.imageView);
        imageButton = view.findViewById(R.id.barcode_close_button);
        textView=view.findViewById(R.id.barcode_name);
        textView.setText(name);
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
    BarcodeFormat MY_BARCODE_FORMAT;
    String num_code;
    private void barcode(String code) throws WriterException {
        checkFormat(code);
        BitMatrix bitMatrix = multiFormatWriter.encode(num_code, MY_BARCODE_FORMAT, 900, 500);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        imageView.setImageBitmap(bitmap);
    }
    void checkFormat(String code){
        Pattern pattern = Pattern.compile("//codeType:(.*)");
        Matcher matcher = pattern.matcher(code);
        String codeType = "";
        if (matcher.find()) {
            codeType = matcher.group(1);
            System.out.println(codeType); // Выводит "QR code"
        }
        BarcodeFormat format=BarcodeFormat.CODE_128;
        switch (codeType) {
            case "UPC_A":
                format = BarcodeFormat.UPC_A;
                break;
            case "UPC_E":
                format = BarcodeFormat.UPC_E;
                break;
            case "EAN_8":
                format = BarcodeFormat.EAN_8;
                break;
            case "EAN_13":
                format = BarcodeFormat.EAN_13;
                break;
            case "CODE_39":
                format = BarcodeFormat.CODE_39;
                break;
            case "CODE_93":
                format = BarcodeFormat.CODE_93;
                break;
            case "CODE_128":
                format = BarcodeFormat.CODE_128;
                break;
            case "CODABAR":
                format = BarcodeFormat.CODABAR;
                break;
            case "ITF":
                format = BarcodeFormat.ITF;
                break;
            case "QR_CODE":
                format = BarcodeFormat.QR_CODE;
                break;
            case "DATA_MATRIX":
                format = BarcodeFormat.DATA_MATRIX;
                break;
            case "AZTEC":
                format = BarcodeFormat.AZTEC;
                break;
            case "PDF_417":
                format = BarcodeFormat.PDF_417;
                break;
        }
//            case "MAXICODE" : format= BarcodeFormat.MAXICODE;
//            case "RSS_14" : format= BarcodeFormat.RSS_14;
            //case "RSS_EXPANDED" : format= BarcodeFormat.RSS_EXPANDED;
        System.out.println("сука бля "+format);
    this.MY_BARCODE_FORMAT=format;
        pattern = Pattern.compile("(.*)//codeType:");
        matcher = pattern.matcher(code);
        String codeValue="";
        if (matcher.find()) {
            codeValue = matcher.group(1);
            System.out.println(codeValue); // Выводит "12345678qwe"
        }
        this.num_code=codeValue;
    }
}