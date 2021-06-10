package com.example.k_app.fragmets;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.k_app.MainActivity;
import com.example.k_app.R;
import com.example.k_app.database.QrCodeDatabase;
import com.example.k_app.database.QrCodeModel;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    ListView listView;
    List<QrCodeModel> qrCodes;
    ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_list, container, false);
        listView = contentView.findViewById(R.id.listView);
        qrCodes = QrCodeDatabase.getDatabase(getActivity().getApplicationContext()).qrCodeDao().getAll();
        setListView();

        contentView.findViewById(R.id.btnPay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openCardFragment();
                ((MainActivity)getActivity()).bottomNavigationView.setSelectedItemId(R.id.page_3);
            }
        });

        return contentView;
    }

    private void setListView(){
        List<String> values = new ArrayList<>();
        for (QrCodeModel qrCodeModel : qrCodes) {
            values.add(qrCodeModel.code);
        }

        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, values);
        listView.setAdapter(arrayAdapter);

        //uzun basmaca
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDeleteDialog(i);
                return true;
            }
        });
    }

    void showDeleteDialog(int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure you want to delete this item?");
        builder.setTitle("Warning");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItem(position);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    void deleteItem(int position){
        QrCodeDatabase.getDatabase(getActivity().getApplicationContext()).qrCodeDao().delete(qrCodes.get(position));
        ((MainActivity)getActivity()).openListFragment();
        //ekranı sildikten sonra yeniledik
    }
}