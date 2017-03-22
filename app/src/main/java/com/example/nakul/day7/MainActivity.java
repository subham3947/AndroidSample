package com.example.nakul.day7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button con,update,dall,dp,del;
    EditText name,ph,id;
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con=(Button)findViewById(R.id.con);
        update=(Button)findViewById(R.id.update);
        dall=(Button)findViewById(R.id.dall);
        dp=(Button)findViewById(R.id.dp);
        del=(Button)findViewById(R.id.del);
        name=(EditText)findViewById(R.id.name);
        ph=(EditText)findViewById(R.id.ph);
        id=(EditText)findViewById(R.id.id);
        display=(TextView)findViewById(R.id.display);
        final SQLDatabaseHandler db=new SQLDatabaseHandler(this);
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addContact(new SQLcontact(
                        name.getText().toString(),
                        ph.getText().toString()));
                name.setText("");
                ph.setText("");

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateContact(new SQLcontact(Integer.parseInt(id.getText().toString()),
                        name.getText().toString(),
                        ph.getText().toString()));
                name.setText("");
                ph.setText("");
                id.setText("");


            }
        });
        dall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SQLcontact> contacts=db.getAllContacts();
                for (SQLcontact cn: contacts){
                    String log="ID: "+cn.getId()
                            +" Name: "
                            +cn.getName()
                            +" Phone: "
                            + cn.getPhone_number();
                    display.append(log+"\n");
                    Log.v("name",log);
                }



            }
        });
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLcontact cn=db.getContact(Integer.parseInt(id.getText().toString()));
                String log="ID: "+cn.getId()+" ,Name: " +cn.getName()+" ,Phone: "+cn.getPhone_number();
                display.setText(log);
                Log.v("name",log);

            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteContact(new SQLcontact((Integer.parseInt(id.getText().toString()))));
                id.setText("");
            }
        });

    }
}
