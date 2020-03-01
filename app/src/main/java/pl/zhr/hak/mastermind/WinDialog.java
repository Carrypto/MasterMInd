package pl.zhr.hak.mastermind;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

public class WinDialog extends AppCompatDialogFragment {

    EditText putName;
    Button okButton;
    ScoreViewModel scoreViewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);

        AppCompatDialog appCompatDialog = new AppCompatDialog(getContext());
        appCompatDialog.setContentView(R.layout.win_dialog_layout);
        appCompatDialog.setCancelable(false);
        appCompatDialog.setCanceledOnTouchOutside(false);

        putName = appCompatDialog.findViewById(R.id.putName);
        okButton = appCompatDialog.findViewById(R.id.okButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (putName.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity(), getString(R.string.put_name_warning), Toast.LENGTH_SHORT).show();
                } else {
                    assert getArguments() != null;
                    int attempts = getArguments().getInt("attempts");
                    String difficulty = getArguments().getString("difficulty");
                    String name = putName.getText().toString();
                    int points = 0;
                    if(difficulty.equals(getString(R.string.easy))){
                        points = (10-attempts) * 5 + 30;
                    }
                    if(difficulty.equals(getString(R.string.medium))){
                        points = (8-attempts) * 10 + 40;
                    }
                    if(difficulty.equals(getString(R.string.hard))){
                        points = (8-attempts) * 15 + 50;
                    }

                    scoreViewModel.insert(new Score(attempts, points, name, difficulty));
                    appCompatDialog.dismiss();
                }
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
