package sg.edu.rp.c346.id19047433.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etEvent;
    Button btnAdd, btnClear, btnDelete;
    ListView lvEvent;
    Spinner spnTask;
    ArrayList<String> alEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEvent = findViewById(R.id.editTextColour);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lvEvent = findViewById(R.id.listViewEvent);
        spnTask = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.buttonDelete);

        alEvent = new ArrayList<>();

        final ArrayAdapter aaEvent = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alEvent);
        lvEvent.setAdapter(aaEvent);

        spnTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        btnClear.setEnabled(true);
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        btnClear.setEnabled(true);
                        etEvent.setHint("Type in index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Events = etEvent.getText().toString();
                alEvent.add(Events);
                etEvent.setText("");
                aaEvent.notifyDataSetChanged();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alEvent.size() == 0){
                    Toast.makeText(MainActivity.this, "You dont have any task to remove", Toast.LENGTH_SHORT).show();
                }
                else if(alEvent.size() <= Integer.parseInt(etEvent.getText().toString())){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }
                else{
                    int index = Integer.parseInt(etEvent.getText().toString());
                    alEvent.remove(index);
                    etEvent.setText("");
                    aaEvent.notifyDataSetChanged();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alEvent.clear();
                aaEvent.notifyDataSetChanged();
            }
        });
    }
}
