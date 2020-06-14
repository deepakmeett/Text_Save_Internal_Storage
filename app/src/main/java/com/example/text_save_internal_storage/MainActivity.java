package com.example.text_save_internal_storage;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button, button1;
    
    private static final String File_Name = "example.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        editText = findViewById( R.id.edit );
        button = findViewById(R.id.save );
        button1 = findViewById(R.id.show );

        button.setOnClickListener( view->{
            String text = editText.getText().toString();
            FileOutputStream fos = null;
            
            try {
                fos = openFileOutput( File_Name, MODE_PRIVATE );
                fos.write( text.getBytes() );
                
                editText.getText().clear();
                Toast.makeText( this, "Save to " + getFilesDir() + "/" + File_Name, Toast.LENGTH_SHORT ).show();
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (fos!=null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } );
        
        button1.setOnClickListener( view->{
            FileInputStream fis = null;
            try {
                fis = openFileInput( File_Name );
                InputStreamReader isr = new InputStreamReader( fis );
                BufferedReader br = new BufferedReader( isr );
                StringBuffer sb = new StringBuffer(  );
                String text;
                while ((text= br.readLine())!=null){
                    sb.append( text ).append( "\n" );
                }
                
                editText.setText( sb.toString() );
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } );
    }
}
