package id.putraprima.skorbola;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String HOMETEAM = "namehome";
    public static final String AWAYTEAM = "nameaway";
    public static final String LOGOHOME = "image2";
    public static final String LOGOAWAY = "image";
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final int GALLERY_REQUEST_CODEX = 2;

    private EditText hname;
    private EditText aname;
    private ImageView hl;
    private ImageView al;
    private Bitmap bitmapa;
    private Bitmap bitmaph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        //Fitur Main Activity
        hname = findViewById(R.id.home_team);
        aname = findViewById(R.id.away_team);
        hl = findViewById(R.id.home_logo);
        al = findViewById(R.id.away_logo);
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    bitmaph = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    hl.setImageBitmap(bitmaph);

                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        } else if (requestCode == GALLERY_REQUEST_CODEX) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    bitmapa = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    al.setImageBitmap(bitmapa);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }


    //5. Next Button Pindah Ke MatchActivity
    public void HandleMatch(View view) {

        //1. Validasi Input Home Team
        String namehome = hname.getText().toString();
        String nameaway = aname.getText().toString();

        if (namehome.isEmpty() && nameaway.isEmpty()) {
            Toast.makeText(this, "harap diisi semua", Toast.LENGTH_LONG).show();
        }

        if (namehome.isEmpty()) {
            hname.setError("Nama team harap diisi");
        } else if (nameaway.isEmpty()) {
            aname.setError("Nama team harap diisi");
        } else if (bitmapa == null) {
            Toast.makeText(this, "Logo away harap di ganti", Toast.LENGTH_LONG).show();
        } else if (bitmaph == null) {
            Toast.makeText(this, "Logo home harap di ganti", Toast.LENGTH_LONG).show();
        } else {

            Intent intent = new Intent(this, MatchActivity.class);

            hl.buildDrawingCache();
            al.buildDrawingCache();
            Bitmap image = hl.getDrawingCache();
            Bitmap image2 = al.getDrawingCache();

            Bundle extras = new Bundle();
            extras.putParcelable(LOGOHOME, image);
            extras.putParcelable(LOGOAWAY, image2);
            intent.putExtras(extras);

            intent.putExtra(HOMETEAM, namehome);
            intent.putExtra(AWAYTEAM, nameaway);
            startActivity(intent);
        }


    }

    public void changeLogoHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void changeLogoAway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODEX);
    }
}
