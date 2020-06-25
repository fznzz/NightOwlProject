package ugm.fznzz.nightowlproject.guide;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ugm.fznzz.nightowlproject.R;

public class guide_adapter extends RecyclerView.Adapter<guide_adapter.MyViewHolder> {

    Context context;
    String s1[],s2[];
    String selected;
    int selectedPosition=-1;
    private OnNoteListener mOnNoteListener;

    public guide_adapter(Context context, String[] ss1, String[] ss2, OnNoteListener onNoteListener) {
        this.context = context;
        s1 = ss1;
        s2 = ss2;
        this.mOnNoteListener=onNoteListener;
    }

    @NonNull
    @Override
    public guide_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.guide_row,parent,false);
        return new MyViewHolder(v, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull guide_adapter.MyViewHolder holder, final int position) {

        holder.nama.setText(s1[position]);
        holder.kode.setText(s2[position]);
        if(selectedPosition==position)
            holder.itemView.setBackgroundColor(Color.parseColor("#c3e8fa"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedPosition=position;
//                notifyDataSetChanged();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return s1.length;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nama,kode;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            nama = itemView.findViewById(R.id.tv_nama);
            kode = itemView.findViewById(R.id.tv_kode);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
            selectedPosition=getAdapterPosition();
            notifyDataSetChanged();
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
 }
