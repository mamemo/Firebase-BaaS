package tc6.mamendez.firebase_tarea;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    public ArrayList<Book> ArrayItem;
    private book_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), add_book.class);
                startActivity(intent);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),detalle_book.class);
                intent.putExtra("book_id",ArrayItem.get(position).getId());
                startActivity(intent);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int id_book = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Está seguro?")
                        .setMessage("Desea eliminar el libro?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String id = ArrayItem.get(id_book).getId();FirebaseDatabase database = FirebaseDatabase.getInstance();
                                FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database1.getReference().child("Books");
                                myRef.child(id).removeValue();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return false;
            }
        });

        cargarLista(this);
    }

    public void cargarLista(Context context){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Books");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayItem = new ArrayList<>();
                //Log.w("Book",dataSnapshot.getValue().toString());
                try{
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String nom = ds.child("nombre").getValue().toString();
                    String pre = ds.child("precio").getValue().toString();
                    String des = ds.child("descripcion").getValue().toString();
                    Log.w("Detalle",ds.getKey());
                    ArrayItem.add(new Book(ds.getKey(),nom,pre,des));
                    Log.w("Detalle adapter",ArrayItem.toString());
                    adapter = new book_adapter(ArrayItem, getApplicationContext());
                    lista.setAdapter(adapter);
                }}
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Intent intent= new Intent(this,MainActivity.class);
            startActivity(getIntent());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
