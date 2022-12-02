package org.techtown.healing_camp;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class RenameDialog extends Dialog {
    Dialog dialog;
    EditText editReName;
    Button reNameCancel, reNameAccept;
    String reName;

    public RenameDialog(@NonNull Context context) {
        super(context);
    }
    public void showDialog(){
        dialog.show();

        editReName = findViewById(R.id.editReName);
        reNameCancel = findViewById(R.id.reNameCancel);
        reNameAccept = findViewById(R.id.reNameAccept);

        reNameCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        reNameAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reName = editReName.getText().toString();
            }
        });

    }
}
