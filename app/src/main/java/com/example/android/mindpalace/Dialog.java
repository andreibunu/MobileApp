package com.example.android.mindpalace;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AlertDialogLayout;

public class Dialog extends AppCompatDialogFragment {

    private EditText editTextWord;
    private EditText editTextMean;
    private DialogListener listener;


    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view).setTitle("add word").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String w=editTextWord.getText().toString();
                String m=editTextMean.getText().toString();
                listener.applyTexts(w,m);
            }
        });

        editTextMean=(EditText)view.findViewById(R.id.editText2);
        editTextWord=(EditText)view.findViewById(R.id.editText);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            listener=(DialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+" must implement dialoglistener");
        }
    }
    public interface DialogListener{
        void applyTexts(String word, String mean);

    }

}
