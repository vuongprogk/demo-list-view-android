package vn.edu.stu.demolistview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnGenerate;
    ListView lv500Bros;
    ArrayList<String> data;
    ArrayAdapter<String> adapters;

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
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generate500Bros();
            }
        });
        lv500Bros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0 && i < 500) {
                    Toast.makeText(MainActivity.this, "This is " + data.get(i), Toast.LENGTH_SHORT).show();
                }
            }
        });
        lv500Bros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>=0 && i < 500){
                    data.remove(i);
                    adapters.notifyDataSetChanged();
                }
                return false;
            }

        });
    }

    private void generate500Bros() {
        for (int i = 0; i < 500; i++) {
            data.add("Brother " + i);
        }
        adapters.notifyDataSetChanged();
    }

    private void addControls() {
        btnGenerate = findViewById(R.id.btnGenerate);
        lv500Bros = findViewById(R.id.lv500Bros);
        data = new ArrayList<String>();
        adapters = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                data
        );
        lv500Bros.setAdapter(adapters);

    }
}