package vn.com.ivnd.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import vn.com.ivnd.student.model.Cosmetics;

public class CosmeticsAdapter extends RecyclerView.Adapter<CosmeticsAdapter.CosmeticsHolder> {

    private List<Cosmetics> cosmetics;
    private Context context;
    private onClickItem listener;

    public CosmeticsAdapter(List<Cosmetics> cosmetics, Context context, onClickItem listener) {
        this.cosmetics = cosmetics;
        this.context = context;
        this.listener = listener;
    }

    public void setCosmetics(List<Cosmetics> cosmetics) {
        this.cosmetics = cosmetics;
        notifyDataSetChanged();
    }

    public List<Cosmetics> searchStudent(String textQuery, List<Cosmetics> students) {
        List<Cosmetics> listAlbumSearch = new ArrayList<>();
        for (Cosmetics movies : students) {
            if (movies.getTen().toLowerCase().contains(textQuery.toLowerCase())) {
                listAlbumSearch.add(movies);
            }
        }
        return listAlbumSearch;
    }

    @NonNull
    @Override
    public CosmeticsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new CosmeticsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CosmeticsHolder holder, int position) {
        final Cosmetics cosmetic = cosmetics.get(position);
        holder.binDataCosmetics(cosmetic);
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickEdit(cosmetic);
                }
            }
        });

        holder.imgDelete.setOnClickListener(view -> {
            if (listener != null) {
                cosmetics.remove(cosmetic);
                listener.onClickDelete(cosmetic.getIdCosmetics());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cosmetics == null ? 0 : cosmetics.size();
    }

    public static class CosmeticsHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvName, amount;
        AppCompatImageView imgEdit, imgDelete;

        public CosmeticsHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            amount = itemView.findViewById(R.id.amount);
            imgEdit = itemView.findViewById(R.id.tv_edit);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }

        public void binDataCosmetics(Cosmetics cosmetic) {
            tvName.setText(cosmetic.getTen());
            amount.setText(cosmetic.getGiatien() + " đ");
        }
    }

    public interface onClickItem {
        void onClickEdit(Cosmetics student);

        void onClickDelete(int id);
    }
}
