package u.com.example.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    public EditText user;
    public EditText pass;
    public Button login;
    public TextView regis;
    public DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        user=(EditText)findViewById(R.id.input_email);
        pass= (EditText)findViewById(R.id.input_password);
        db = new DBHelper(this);
        regis = (TextView)findViewById(R.id.link_signup);
        regis.setPaintFlags(regis.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("in onclick","three");
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivityForResult(i,1);

            }
        });
        login= (Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Onclick","inside");
                String u=user.getText().toString();
                Log.d("username",u);
                String p=pass.getText().toString();
                Log.d("password",p);
                Boolean log = db.login_func(u,p);
                if(log==false)
                    Toast.makeText(getApplicationContext(),"Wrong Credentials",Toast.LENGTH_LONG).show();
                else{
                    SharedPreferences mypref = getSharedPreferences("PREFS",0);
                    SharedPreferences.Editor editor = mypref.edit();
                    editor.putString("username",u);
                    editor.commit();
                    SharedPreferences mypref123 = getSharedPreferences("P1",0);
                    SharedPreferences.Editor editor1 = mypref123.edit();
                    editor1.putString("user",u);
                    editor1.commit();
                    String ko = mypref.getString("username","a");
                    Log.d("here",ko);
                    Intent i = new Intent(getApplicationContext(),Dash.class);
                    startActivityForResult(i,1);
                }

            }
        });
    }
}
