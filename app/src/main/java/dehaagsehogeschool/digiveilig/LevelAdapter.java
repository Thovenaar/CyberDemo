package dehaagsehogeschool.digiveilig;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dehaagsehogeschool.digiveilig.models.Level;

/**
 * Created by Thomas on 27-Mar-18.
 */

class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {

    ArrayList<Level> levels = new ArrayList<>();

    public void addLevels(List<Level> levels) {
        this.levels.addAll(levels);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stars, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.stars.setText(getItemCount() + " Sterren");
    }

    @Override
    public int getItemCount() {
        int i = 0;

        for (Level level : levels) {
            i += level.stars;
        }

        return i;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView stars;

        public ViewHolder(View itemView) {
            super(itemView);

            stars = (TextView) itemView.findViewById(R.id.stars);
        }
    }
}
