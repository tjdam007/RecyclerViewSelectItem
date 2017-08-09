package mjandroiddev.recyclerviewselectitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import mjandroiddev.recyclerviewselectitem.adapters.Adapter;

import static mjandroiddev.recyclerviewselectitem.BuildConfig.DEBUG;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(this, getData(), new Callback() {
            @Override
            public void onCallback(Object data) {
                if (DEBUG) Log.d(TAG, "onCallback: " + data.toString());
                Toast.makeText(MainActivity.this, "" + data.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //setting selected item
        adapter.setSelectedItem(0);
        recyclerView.setAdapter(adapter);

    }

    private List<String> getData() {
        return Arrays.asList(getResources().getStringArray(R.array.countries_name));
    }
}
