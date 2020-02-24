package id.putraprima.skorbola;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView hasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        hasil = findViewById(R.id.textView3);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String terserah = getIntent().getExtras().getString("pemenang");
            hasil.setText(terserah);
        }
    }
}
