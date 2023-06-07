package com.example.bimmer.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.bimmer.R;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DiscountDialog extends AppCompatDialogFragment {
    private DialogListener listener;
    public void setDialogListener(DialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.discount_dialog_layout, null);
        builder.setView(rootView);
        TextView discountName;
        TextView discountPhone;
        discountPhone = rootView.findViewById(R.id.discountPhone);
        discountName = rootView.findViewById(R.id.discountName);
        Button recordButton = rootView.findViewById(R.id.recordRepairButton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(discountName.getText().toString())){
                    Toast.makeText(getContext(), "Введите свое имя", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(discountPhone.getText().toString())){
                    Toast.makeText(getContext(), "Введите номер телефона", Toast.LENGTH_SHORT).show();
                } else {
                    String name = discountName.getText().toString();
                    String phone = discountPhone.getText().toString();
                    listener.onDialogSubmit(name, phone);
                    dismiss();
                }
            }
        });

        Button cancelButton = rootView.findViewById(R.id.cancelDiscountButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }

    public interface DialogListener {
        void onDialogSubmit(String name, String phone);
    }
}
