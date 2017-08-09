package mjandroiddev.recyclerviewselectitem.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mjandroiddev.recyclerviewselectitem.Callback;
import mjandroiddev.recyclerviewselectitem.R;
import mjandroiddev.recyclerviewselectitem.models.Action;

/**
 * Adapter that need list of @Link{{@link Action}}
 * Created by manoj.rawal on 8/8/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.AVH> {
    private final Context context;
    private final Callback callback;
    private final List<String> actions;
    private int selectedItem = -1;

    /**
     * Parametrised Constructor
     *
     * @param context     context of the activity
     * @param listActions array list of actions
     * @param callback    instance of {@link Callback}
     */
    public Adapter(Context context, List<String> listActions, Callback callback) {
        this.callback = callback;
        this.context = context;
        this.actions = listActions;
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public AVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_list_item_1, parent, false);
        return new AVH(view);
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(AVH holder, final int position) {
        final String action = actions.get(position);
        holder.setText(action);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedItem(position);  //setting new selected position
                callback.onCallback(action); //callback for click
            }
        });

        if (position == getSelectedItem()) {
            holder.itemView.setActivated(true); // setting activated true
        } else {
            holder.itemView.setActivated(false);// setting activated false
        }
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return actions.size();
    }

    /**
     * Set new selected position for the adapter
     *
     * @param selectedItem Set selected position
     */
    public void setSelectedItem(int selectedItem) {
        if (this.selectedItem != -1) {
            notifyItemChanged(this.selectedItem);
        }
        this.selectedItem = selectedItem;
        notifyItemChanged(selectedItem);

    }


    /**
     * Get current selected position.
     *
     * @return selected position.
     */
    public int getSelectedItem() {
        return selectedItem;
    }


    /*View holder for the row*/
    class AVH extends RecyclerView.ViewHolder {
        private TextView textView;

        public AVH(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text1);
        }

        public void setText(String text) {
            textView.setText(text);
        }
    }
}
