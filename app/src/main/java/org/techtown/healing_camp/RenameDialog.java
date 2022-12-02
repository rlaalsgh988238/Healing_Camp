package org.techtown.healing_camp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import java.util.zip.Inflater;

public class RenameDialog {
    EditText editReName;
    Button reNameCancel, reNameAccept;
    String reName;
    Context context;

    public RenameDialog(Context context) {
        this.context = context;
    }
    public void showDialog(){

        final Dialog dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.planner_rename_dialog);
        dialog.show();

        editReName = dialog.findViewById(R.id.editReName);
        reNameCancel = dialog.findViewById(R.id.reNameCancel);
        reNameAccept = dialog.findViewById(R.id.reNameAccept);

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
