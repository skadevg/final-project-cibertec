package com.project.cibertec.finalproject.pedido.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.cibertec.finalproject.R;
import com.project.cibertec.finalproject.entities.Pedido;

import java.util.ArrayList;


public class RVAdapterListaPedido extends RecyclerView.Adapter<RVAdapterListaPedido.RVAdapterListaPedidoViewHolder> {

    private ArrayList<Pedido> mLstPedido;

    public RVAdapterListaPedido() {
        mLstPedido = new ArrayList<>();
    }

    public void add(Pedido pedido) {
        mLstPedido.add(pedido);
        notifyItemInserted(mLstPedido.size() - 1);
    }

    public void addAll(ArrayList<Pedido> pedidos) {
        mLstPedido.addAll(pedidos);
    }

    @Override
    public RVAdapterListaPedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVAdapterListaPedidoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_lista_pedidos_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RVAdapterListaPedidoViewHolder holder, int position) {
        Pedido pedido = mLstPedido.get(position);
        holder.tvPedidoListItemNombreCli.setText(pedido.getNombreCliente());
        holder.tvPedidoListItemCantProductos.setText(String.valueOf(pedido.getCantidadProductos()).concat(" productos"));
        holder.tvPedidoListItemTotal.setText("S/ "+String.valueOf(pedido.getTotalPedido()));
    }

    @Override
    public int getItemCount() {
        return mLstPedido.size();
    }

    static class RVAdapterListaPedidoViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPedidoListItemNombreCli, tvPedidoListItemCantProductos, tvPedidoListItemTotal;

        public RVAdapterListaPedidoViewHolder(View itemView) {
            super(itemView);

            tvPedidoListItemNombreCli = (TextView) itemView.findViewById(R.id.tvPedidoListItemNombreCli);
            tvPedidoListItemCantProductos = (TextView) itemView.findViewById(R.id.tvPedidoListItemCantProductos);
            tvPedidoListItemTotal = (TextView) itemView.findViewById(R.id.tvPedidoListItemTotal);
        }
    }

}
