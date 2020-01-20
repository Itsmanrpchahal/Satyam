package com.mandy.satyam.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mandy.satyam.MainActivity;
import com.mandy.satyam.login.LoginActivity;
import com.mandy.satyam.R;

public class UtilDialog {

    public static Dialog dialog(final Context context, String titel, String button) {
        final Dialog dialog = new Dialog(context);

        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(false);

        TextView textView;
        final Button btnCancel, btnLogout;

        textView = dialog.findViewById(R.id.txtTitle);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnLogout = dialog.findViewById(R.id.btnLogout);

        //set the title
        textView.setText(titel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        btnLogout.setText(button);

        if (btnLogout.getText().equals("Logout")) {
            btnLogout.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        } else {
            btnLogout.setBackgroundColor(context.getResources().getColor(R.color.red));
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnLogout.getText().equals("Logout")) {
                    dialog.dismiss();
                    SharedToken sharedToken = new SharedToken(context);
                    sharedToken.clearShaerd();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                } else {
                    Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        return dialog;
    }

}
