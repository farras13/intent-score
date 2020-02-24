package id.putraprima.skorbola;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MatchActivity extends AppCompatActivity {
    public static final String PEMENANG = "pemenang";
    private TextView Homename;
    private TextView Awayname;
    private ImageView Homelogo;
    private ImageView Awaylogo;
    private TextView schome;
    private TextView scaway;
    private Integer homesc;
    private Integer awaysc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        Homename = findViewById(R.id.txt_home);
        Awayname = findViewById(R.id.txt_away);
        Homelogo = findViewById(R.id.home_logo);
        Awaylogo = findViewById(R.id.away_logo);
        scaway = findViewById(R.id.score_away);
        schome = findViewById(R.id.score_home);
        homesc = 0;
        awaysc = 0;

        schome.setText("0");
        scaway.setText("0");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String hn = getIntent().getExtras().getString("namehome");
            String an = getIntent().getExtras().getString("nameaway");
            Bundle extra = getIntent().getExtras();
            Bitmap bmp = extra.getParcelable("image");
            Bitmap bmp1 = extra.getParcelable("image2");
            Homelogo.setImageBitmap(bmp);
            Awaylogo.setImageBitmap(bmp1);
            Homename.setText(hn);
            Awayname.setText(an);
        }



        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    public void addAway(View view) {
        awaysc++;
        scaway.setText(String.valueOf(awaysc));

    }

    public void addHome(View view) {
        homesc++;
        schome.setText(String.valueOf(homesc));
    }

    public void CekPemenang(View view) {
        String pemenang;
        if (homesc > awaysc) {
            pemenang = Homename.getText().toString();
        } else if (homesc < awaysc) {
            pemenang = Awayname.getText().toString();
        } else {
            pemenang = "Draw";
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(PEMENANG, pemenang);
        startActivity(intent);
    }
}
