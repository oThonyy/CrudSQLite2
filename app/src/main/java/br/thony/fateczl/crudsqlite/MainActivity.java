package br.thony.fateczl.crudsqlite;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;

/*
 *@author:<Anthony Siqueira de Oliveira>
 *@ra:<1110482313032>
 */

public class MainActivity extends AppCompatActivity {

    private JogadorDAO jogadorDao;
    private TimeDAO timeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        jogadorDao = new JogadorDAO(this);
        timeDao = new TimeDAO(this);

        MaterialToolbar toolbar = findViewById(R.id.appBar);
        toolbar.setOnMenuItemClickListener(item -> {
            Fragment fragment = null;
            if (item.getItemId() == R.id.menu_jogadores) {
                fragment = new JogadoresFragment();
            } else if (item.getItemId() == R.id.menu_times) {
                fragment = new TimesFragment();
            }

            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();

            }
        });
    }
}