package com.example.cardapplication;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//
//import android.os.Bundle;
//import android.view.View;
//
//import android.widget.FrameLayout;
//import android.widget.Toast;
//
//import com.budiyev.android.codescanner.CodeScanner;
//import com.budiyev.android.codescanner.CodeScannerView;
//import com.budiyev.android.codescanner.DecodeCallback;
//import com.google.zxing.Result;
//
//public class Scanner extends AppCompatActivity {
//    FrameLayout frameLayout;
//    CardSave cardSave;
//    private CodeScanner mCodeScanner;
//    String res="nothing";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        setContentView(R.layout.activity_scanner);
//        CodeScannerView scannerView = findViewById(R.id.scanner);
//        mCodeScanner = new CodeScanner(this, scannerView);
//        frameLayout = findViewById(R.id.frame);
//        mCodeScanner.getScanMode();
//        mCodeScanner.setDecodeCallback(new DecodeCallback() {
//            @Override
//            public void onDecoded(@NonNull final Result result) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        setResult(result.getText());
//                        Toast.makeText(Scanner.this, getResult(), Toast.LENGTH_SHORT).show();
//                        cardSave=new CardSave(getResult());
//                        setFragment(cardSave);
//                        mCodeScanner.stopPreview();
//
//                    }
//                });
//
//            }
//        });
//        scannerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mCodeScanner.startPreview();
//            }
//        });
//    }
//    String getResult(){
//        return this.res;
//    }
//    public void setFragment(Fragment fragment) {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.frame, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
//    void setResult(String res){
//        this.res=res;
//    }
//}

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.Result;

public class Scanner extends AppCompatActivity {
    private static final String TAG = Scanner.class.getSimpleName();
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    private CodeScanner mCodeScanner;
    private CodeScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mScannerView = findViewById(R.id.scanner);
        mCodeScanner = new CodeScanner(this, mScannerView);

        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                System.out.println("нихуя декодировалось");
                System.out.println(result.getBarcodeFormat());
                // Handle the decoded result
                Log.d(TAG, "Scanned: " + result.getText());
                //Toast.makeText(Scanner.this, result.toString(), Toast.LENGTH_SHORT).show();
                        CardSave cardSave=new CardSave(result+"//codeType:"+result.getBarcodeFormat());
                        setFragment(cardSave);
            }
        });
        mCodeScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull Throwable thrown) {
                Log.e(TAG, "Error: " + thrown.getMessage());
            }
        });

        checkCameraPermission(getApplicationContext());
    }
        public void setFragment(Fragment fragment) {
            System.out.println("ставится фрагмент");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

//    public void createScannerView(ViewGroup container, Context context) {
//        View view = LayoutInflater.from(context).inflate(R.layout.activity_scanner, container, false);
//        mScannerView = view.findViewById(R.id.scanner);
//        mCodeScanner = new CodeScanner(context, mScannerView);
//
//        mCodeScanner.setDecodeCallback(new DecodeCallback() {
//            @Override
//            public void onDecoded(@NonNull final Result result) {
//                // Handle the decoded result
//                Log.d(TAG, "Scanned: " + result.getText());
//            }
//        });
//
//        mCodeScanner.setErrorCallback(new ErrorCallback() {
//            @Override
//            public void onError(@NonNull Throwable thrown) {
//                Log.e(TAG, "Error: " + thrown.getMessage());
//            }
//        });
//
//        checkCameraPermission(context);
//    }

    private void checkCameraPermission(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startScanning();
        } else {
            requestCameraPermission(context);
        }
    }

    private void requestCameraPermission(final Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
            Snackbar.make(mScannerView, "Нужно ваше разрешение на использование камеры", Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{Manifest.permission.CAMERA},
                                    CAMERA_PERMISSION_REQUEST_CODE);
                        }
                    }).show();
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScanning();
            }
        }
        else super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void startScanning() {
        mCodeScanner.startPreview();
    }
}