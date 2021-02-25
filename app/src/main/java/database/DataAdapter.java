package database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import space.korolev.quiz.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolderData> {
    private List<Client> clientListArray;

    public DataAdapter(List<Client> clientListArray) {
        this.clientListArray = clientListArray;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout,parent,false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.setData(clientListArray.get(position));

    }

    @Override
    public int getItemCount() {
        return clientListArray.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvLevel;
        TextView tvTime;
        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvLevel = itemView.findViewById(R.id.tv_level);
            tvTime = itemView.findViewById(R.id.tv_time);
        }

        public void setData(Client client) {
            tvName.setText(client.getName());
            tvLevel.setText(client.getLevel());
            tvTime.setText(client.getTime());
        }
    }
}
