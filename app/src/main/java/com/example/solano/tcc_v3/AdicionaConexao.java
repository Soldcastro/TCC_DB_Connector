package com.example.solano.tcc_v3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdicionaConexao extends AppCompatActivity  {

    EditText cap_name,cap_host,cap_user,cap_pass;
    Button btn_ok,btn_clr;
    Spinner spinner_sgbds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_conexao);

        addItemOnSpinner();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }

    public void addItemOnSpinner() {
        Spinner spinner_sgbds = (Spinner) findViewById(R.id.spinner_1);
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sgbds_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_sgbds.setAdapter(adapter);

    }

    public void addListenerOnSpinnerItemSelection() {
        Spinner spinner_sgbds = (Spinner) findViewById(R.id.spinner_1);
        spinner_sgbds.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton(){
        btn_ok.setOnClickListener(new View.OnClickListener() {

            EditText cap_name = (EditText) findViewById(R.id.editText4);
            EditText cap_host = (EditText) findViewById(R.id.editText);
            EditText cap_user = (EditText) findViewById(R.id.editText2);
            EditText cap_pass = (EditText) findViewById(R.id.editText3);
            Spinner spinner_sgbds = (Spinner) findViewById(R.id.spinner_1);

            @Override
            public void onClick(View v) {
                ControlaBanco crud = new ControlaBanco(getBaseContext());

                String name = cap_name.getText().toString();
                String host = cap_host.getText().toString();
                String user = cap_user.getText().toString();
                String pass = cap_pass.getText().toString();
                String sgbd = String.valueOf(spinner_sgbds.getSelectedItem());
                String result;

                result = crud.insereDado(name,host,user,pass,sgbd);

                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG);
            }
        });

    }
}
