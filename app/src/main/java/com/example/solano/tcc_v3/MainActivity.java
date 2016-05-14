package com.example.solano.tcc_v3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class MainActivity extends Activity {


    private EditText cap_host;
    private EditText cap_user;
    private EditText cap_pass;
    private Button btn_ok;
    private Button btn_clr;
    private Spinner spinner_sgbds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Define as variáveis e associa os inputs
        cap_host = (EditText) findViewById(R.id.editText);
        cap_user = (EditText) findViewById(R.id.editText2);
        cap_pass = (EditText) findViewById(R.id.editText3);
        btn_ok = (Button) findViewById(R.id.button);
        btn_clr = (Button) findViewById(R.id.button3);
        spinner_sgbds = (Spinner) findViewById(R.id.spinner);

//Carrega o driver
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        //DriverManager.registerDriver(new org.gjt.mm.mysql.Driver ());
        Log.i("APP", "Driver loaded");
    }
    catch (Exception e) {
        Log.e("APP", "Cannot create connection");
    }

//Tenta criar a conexão com o banco em background
 //   AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
 //       @Override
 //       protected Void doInBackground(Void... params) {

        //Tratamento do Botão Ok
        btn_ok.setOnClickListener (new OnClickListener(){
                public void onClick(View v){
                    //Tenta conectar ao banco de dados
            try {
                //Variáveis para definir host, usuário e senha (hardcoded com ambiente no Azure)
                //String host = "jdbc:mysql://eu-cdbr-azure-north-e.cloudapp.net:3306/db_tcc_mysql";
                //String user = "b80ee6868b7d65";
                //String pass = "b485f4ca";

                //Converte as variáveis EditText para String
                  String host = cap_host.getText().toString();
                  String user = cap_user.getText().toString();
                  String pass = cap_pass.getText().toString();

                //Método para conexão:
                  Connection connection = DriverManager.getConnection(host,user,pass);
                    //Loga status no logcat
                    Log.i("APP", "Connected");

                        //Envia a Query
                        Statement statement = connection.createStatement();
                        //Query
                        String query = "select dept_no, dept_name from departments;";
                        //Joga o resultado em uma variável
                        ResultSet result = statement.executeQuery(query);

                        //Printa o resultado no logcat
                        while (result.next()) {
                            String dept_no = result.getString("dept_no");
                            String dept_name = result.getString("dept_name");

                            Log.d("APP", dept_no + " - " + dept_name);

                        }

            }
            //Printa o erro
            catch (Exception e) {
                Log.e("APP", "Error: " + e.getMessage());
                e.printStackTrace();
                }

                }
            });
        //Tratamento do Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sgbds_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

//            return null;
//        }
//Encerra o Asynctask
  //  };


//    task.execute();
    }
}
