package com.example.smart_drinking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RM_RecyclerViewAdapter extends RecyclerView.Adapter<RM_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<RemindersModel> remindersModels;
    public RM_RecyclerViewAdapter(Context context, ArrayList<RemindersModel> remindersModels){
        this.context = context;
        this.remindersModels = remindersModels;
    }

    @NonNull
    @Override
    public RM_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent,false);
        return new RM_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RM_RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.tv_dia.setText(remindersModels.get(position).getDia());
        holder.tv_hora.setText(remindersModels.get(position).getHora());
        holder.tv_hora.setText(remindersModels.get(position).getHorario());
        holder.iv_recordatorio.setImageResource(remindersModels.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return remindersModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_recordatorio;
        TextView tv_dia, tv_hora, tv_horario;
        Button btn_eliminar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_recordatorio = itemView.findViewById(R.id.iv_recordatorio);
            tv_dia = itemView.findViewById(R.id.tv_dia);
            tv_hora = itemView.findViewById(R.id.tv_hora);
            tv_horario =  itemView.findViewById(R.id.tv_horario);
            btn_eliminar = itemView.findViewById(R.id.btn_eliminar);

        }
    }
}
