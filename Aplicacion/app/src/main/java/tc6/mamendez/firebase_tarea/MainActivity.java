package tc6.mamendez.firebase_tarea;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //cargarLista(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

//    public void cargarLista(Context context){
//        String id = String.valueOf(Controlador.getInstance().getId_user());
//        String chofer =  String.valueOf(Controlador.getInstance().get_chofer_id(id));
//        try {
//            JSONObject ch = new JSONObject(chofer);
//            String id_chofer = ch.getString("chofer");
//            String chofer_info = Controlador.getInstance().get_driver(id_chofer);
//            JSONObject json_aux = new JSONObject(chofer_info);
//            //Log.i("==>",json_aux.getJSONArray("rutas").toString());
//            JSONArray json_rutas = json_aux.getJSONArray("rutas");
//            for(int i=0;i<json_rutas.length();i++){
//                JSONObject object = (JSONObject) json_rutas.getJSONObject(i);
//                //Log.i("id ==> ",String.valueOf(object.getInt("id")));
//                ArrayItem.add(new Ruta(object.getInt("id"),
//                        object.getString("nombre"),
//                        object.getString("latitud_final"),
//                        object.getString("longitud_final"),
//                        (float) object.getDouble("costo")));
//            }
//            adapter = new listRutaAdapter(ArrayItem, context);
//            rutas.setAdapter(adapter);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


}
