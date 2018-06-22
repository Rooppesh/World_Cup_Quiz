package u.com.example.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class LeaderWC extends AppCompatActivity {
    public int first,second,third;
    Button rank;
    private SharedPreferences mypref123;
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(getApplicationContext(),Dash.class);
        startActivity(setIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//
        setContentView(R.layout.activity_score);

        Button button = (Button)findViewById(R.id.play_buttoncric);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        button.setText(score+" Pts");

        rank = (Button) findViewById(R.id.rank);

        SharedPreferences mypref123 = getSharedPreferences("P1",0);
        SharedPreferences.Editor editor1 = mypref123.edit();
        editor1.putInt("yourscoreckt",score);
        int first = mypref123.getInt("fc",0);
        int second = mypref123.getInt("sc",0);
        int third = mypref123.getInt("tc",0);


        String cur_user = mypref123.getString("user","a");

        String nfirst = mypref123.getString("nfc","no_player");
        String nsecond = mypref123.getString("nsc","no_player");
        String nthird = mypref123.getString("ntc","no_player");

        if(score > third){
            third = score;
            editor1.putInt("tc",third);
            editor1.putString("ntc",cur_user);
            editor1.apply();
        }
        if(score > second){
            int temp = second;
            second = score;
            third = temp;
            editor1.putInt("tc",third);
            editor1.putString("ntc",nsecond);
            editor1.putInt("sc",second);
            editor1.putString("nsc",cur_user);
            editor1.apply();
        }
        if(score > first){
            int temp = first;
            first = score;
            second = temp;
            editor1.putInt("sc",second);
            editor1.putString("nsc",nfirst);
            editor1.putString("nfc",cur_user);
            editor1.putInt("fc",first);
            editor1.apply();
        }
        nfirst = mypref123.getString("nfc","no_player");
        nsecond = mypref123.getString("nsc","no_player");
        nthird = mypref123.getString("ntc","no_player");


        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Dash.class);
                startActivityForResult(i,1);
            }
        });

    }
}
