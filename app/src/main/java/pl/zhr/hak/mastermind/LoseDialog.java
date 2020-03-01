package pl.zhr.hak.mastermind;

import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

public class LoseDialog extends AppCompatDialogFragment {

    TextView codeWas;
    Button okButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AppCompatDialog appCompatDialog = new AppCompatDialog(getContext());
        appCompatDialog.setContentView(R.layout.lose_dialog_layout);
        appCompatDialog.setCancelable(false);
        appCompatDialog.setCanceledOnTouchOutside(false);

        codeWas = appCompatDialog.findViewById(R.id.codeWas);
        okButton = appCompatDialog.findViewById(R.id.okButton);

        assert getArguments() != null;
        int first = getArguments().getInt("first");
        int second = getArguments().getInt("second");
        int third = getArguments().getInt("third");
        int fourth = getArguments().getInt("fourth");

        String code = (" " + first + " " + second + " " + " " + third + " " + " " + fourth);
        codeWas.setText(getString(R.string.what_was_that,code));

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatDialog.dismiss();
            }
        });

        return appCompatDialog;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Objects.requireNonNull(getActivity()).finish();
    }
}