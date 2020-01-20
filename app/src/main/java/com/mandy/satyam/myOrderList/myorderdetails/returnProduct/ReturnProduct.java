package com.mandy.satyam.myOrderList.myorderdetails.returnProduct;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mandy.satyam.R;
import com.mandy.satyam.utils.Util;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickClick;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;

public class ReturnProduct extends AppCompatActivity implements IPickResult {

    @BindView(R.id.back_on_returnScreen)
    ImageButton backOnReturnScreen;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.refund_reason_tv)
    TextView refundReasonTv;
    @BindView(R.id.reason_for_return)
    EditText reasonForReturn;
    @BindView(R.id.product_image)
    ImageView productImage;
    @BindView(R.id.upload_image_bt)
    Button uploadImageBt;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.summary_tv)
    TextView summaryTv;
    @BindView(R.id.tv_OrderName)
    TextView tvOrderName;
    @BindView(R.id.tv_SubPrice)
    TextView tvSubPrice;
    @BindView(R.id.tv_tax)
    TextView tvTax;
    @BindView(R.id.tv_tax1)
    TextView tvTax1;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_totalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.layout_main)
    RelativeLayout layoutMain;
    @BindView(R.id.return_bt)
    Button returnBt;
    public static int RESULT_LOAD_IMAGE = 101;
    public static int REQUEST_CAMERA = 102;
    public static int MY_PERMISSIONS_REQUEST_CAMERA = 1242;
    Bitmap bitmap;
    Uri uri;
    File filesDir;
    MultipartBody.Part part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_product);
        ButterKnife.bind(this);
        filesDir = getFilesDir();
        listerners();
    }

    private void listerners() {
        backOnReturnScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        uploadImageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();
            }
        });
    }

    private void uploadImage() {
        PickImageDialog.build(new PickSetup().setButtonOrientation(LinearLayout.HORIZONTAL)).show(ReturnProduct.this).setOnClick(new IPickClick() {
            @Override
            public void onGalleryClick() {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);

            }

            @Override
            public void onCameraClick() {
                if (ContextCompat.checkSelfPermission(ReturnProduct.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(ReturnProduct.this, Manifest.permission.CAMERA)) {

                        ActivityCompat.requestPermissions(ReturnProduct.this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                    } else {
                        ActivityCompat.requestPermissions(ReturnProduct.this,
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }

                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }
            }

        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            Log.d("++++++++++", "++++ data log +++" + imageBitmap);

        }

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {


            uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
//                setStringVal(Constants.BitmapImage, String.valueOf(bitmap));
                productImage.setImageBitmap(bitmap);
            try {
                part = Util.sendImageFileToserver(bitmap, "user_image", ReturnProduct.this);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            ActivityCompat.requestPermissions(ReturnProduct.this, new String[]{Manifest.permission.CAMERA}, requestCode);
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");

            productImage.setImageBitmap(bitmap);
            Util.encodeTobase64(bitmap);

            try {
                part = Util.sendImageFileToserver(bitmap, "user_image", ReturnProduct.this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            try {
                part = Util.sendImageFileToserver(bitmap, "user_image", ReturnProduct.this);


            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //Handle possible errors
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
