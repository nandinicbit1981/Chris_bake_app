package christopher.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import christopher.bakingapp.R;
import christopher.bakingapp.interfaces.ItemClickInterface;
import christopher.bakingapp.retrofit.StepModel;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private Context mContext;


    private ArrayList<StepModel> stepList = new ArrayList<>();
    ItemClickInterface itemClickInterface;
    public StepAdapter(Context mContext, ArrayList<StepModel> stepList, ItemClickInterface itemClickInterface) {

        this.mContext = mContext;
        this.stepList = stepList;
        this.itemClickInterface = itemClickInterface;

    }

    @Override
    public StepAdapter.StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_steps;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        final StepViewHolder viewHolder = new StepViewHolder(view);




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StepAdapter.StepViewHolder holder, final int position) {

        StepModel selectedRecipe = stepList.get(position);
        holder.step.setText(selectedRecipe.getShortDesc());
        holder.step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickInterface.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder {

        TextView step;


        public StepViewHolder(View itemView) {
            super(itemView);

            step = (TextView) itemView.findViewById(R.id.tv_steps);


        }

    }
}
