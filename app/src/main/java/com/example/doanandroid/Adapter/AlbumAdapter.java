package com.example.doanandroid.Adapter;

        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.doanandroid.Activity.DanhsachbaihatActivity;
        import com.example.doanandroid.Model.Album;
        import com.example.doanandroid.R;
        import com.squareup.picasso.Picasso;

        import java.util.ArrayList;

public class AlbumAdapter extends  RecyclerView.Adapter<AlbumAdapter.Viewholder>{
    Context context;
    ArrayList<Album>magalbum;

    public AlbumAdapter(Context context, ArrayList<Album> magalbum) {
        this.context = context;
        this.magalbum = magalbum;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_album,parent,false);


        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        Album album=magalbum.get(position);
        holder.txttenalbum.setText(album.getTenalbum());
        holder.tencasialbum.setText(album.getTencasialbum());
        Picasso.get().load(album.getHinhanhalbum()).into(holder.imghinhalbum);

    }

    @Override
    public int getItemCount() {
        return magalbum.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView imghinhalbum;
        TextView txttenalbum,tencasialbum;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imghinhalbum=itemView.findViewById(R.id.imageviewalbum);
            txttenalbum=itemView.findViewById(R.id.textviewtenalbum);
            tencasialbum=itemView.findViewById(R.id.textviewcasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idalbum", magalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });


        }
    }


}