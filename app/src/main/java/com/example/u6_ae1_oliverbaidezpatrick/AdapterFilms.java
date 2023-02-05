package com.example.u6_ae1_oliverbaidezpatrick;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ContextMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

//Clase personalizada para generar un adaptador de peliculas.
// Permite pasarle un ArrayList de pelis y genera una serie de
// Elementos película con un layout que hemos definido en un xml

public class AdapterFilms extends RecyclerView.Adapter<AdapterFilms.ViewHolderFilms>
implements  View.OnClickListener, View.OnCreateContextMenuListener{

    ArrayList<Film> films;
    private LayoutInflater inflater;

    private View.OnClickListener listener;


    public AdapterFilms(Context context, ArrayList<Film> films) {
        this.inflater = LayoutInflater.from(context);
        this.films = films;
    }

    @NonNull
    @Override
    public ViewHolderFilms onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        //Le asigna el layout que hemos creado a la vista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_peli, null, false);
        view.setOnClickListener(this); //Para que escuche el listener
        view.setOnCreateContextMenuListener(this);  //menu contextual;

        return new ViewHolderFilms(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFilms holder, int position) {
        //Settea los elementos del xml recibiendo los parámetros de cada una de las películas
        // contenidas en el ArrayList que le pasamos
        holder.title.setText(films.get(position).getTitle());
        holder.year.setText(films.get(position).getYear()+"");
        holder.image.setImageResource(films.get(position).getImage());

    }



    @Override
    //Cuenta los elementos en el arraylist
    public int getItemCount() {
        return films.size();
    }

    //Genera un listener
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    //Permite usar la función onclick en instancias de este elemento o que hereden de él
    public void onClick(View view) {

        if(listener != null){
            listener.onClick(view);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select Action");
        contextMenu.add(0, view.getId(), 0, "Add/Remove to favorites").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                ImageView iv = (ImageView) view.findViewById(R.id.ivStar);


                if(iv.getVisibility() == View.VISIBLE){
                    iv.setVisibility(View.INVISIBLE);

                }else{
                    iv.setVisibility(View.VISIBLE);
                }


                return false;
            }
        });
        contextMenu.add(0, view.getId(), 0, "Exit");

    }


    //Clase interna que genera un elemento con los atributos setteables del layout(xml)
    public class ViewHolderFilms extends RecyclerView.ViewHolder{

        TextView title, year;
        ImageView image;



        public ViewHolderFilms(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitulo);
            year = itemView.findViewById(R.id.tvYear);
            image = itemView.findViewById(R.id.ivImage);
        }
    }
}
