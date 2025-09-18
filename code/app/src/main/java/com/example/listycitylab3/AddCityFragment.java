package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.listycitylab3.bean.City;

public class AddCityFragment extends DialogFragment {

    private AddCityDialogListener listener;
    interface AddCityDialogListener {
        void addCity(City city);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + "must implement AddCityDialogListener");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCitName = view.findViewById(R.id.edit_text_city);
        EditText editProvince = view.findViewById(R.id.edit_text_province);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder.setView(view)
                .setTitle("Add a City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ciyName = editCitName.getText().toString();
                        String provinceName = editProvince.getText().toString();
                        listener.addCity(new City(ciyName, provinceName));
                    }
                })
                .create();

    }
}
