package tc6.mamendez.firebase_tarea;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class book_adapter extends BaseAdapter {
    private ArrayList<Book> arrayItems;
    private Context context;
    private LayoutInflater layoutInflater;

    public book_adapter(ArrayList<Book> arrayItems, Context context) {
        this.arrayItems = arrayItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayItems.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vistaItem = layoutInflater.inflate(R.layout.lista_book, parent, false);
        ImageView iv_imagen = (ImageView) vistaItem.findViewById(R.id.ls_imagen);
        TextView nombre = (TextView) vistaItem.findViewById(R.id.tx_nombre);
        TextView precio = (TextView) vistaItem.findViewById(R.id.tx_precio);
        TextView descp = (TextView) vistaItem.findViewById(R.id.tx_descp);
        ImageDownloadTask downloadTask = new ImageDownloadTask();
        try {
            Bitmap result = downloadTask.execute("https://firebasestorage.googleapis.com/v0/b/fir-tarea.appspot.com/o/"+arrayItems.get(i).getNombre()+
                    "?alt=media&token=49d0fda1-dbb4-4189-9ac3-84f40c21d047").get();
            iv_imagen.setImageBitmap(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        nombre.setText("Name: "+arrayItems.get(i).getNombre());
        precio.setText("Price: "+arrayItems.get(i).getPrecio());
        descp.setText("Description: "+arrayItems.get(i).getDescripcion());
        return vistaItem;
    }
}
