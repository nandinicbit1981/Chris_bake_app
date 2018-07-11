package christopher.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.IngredientModel;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private Context mContext;

    String ingredientName;
    String quant;
    String measure;
    String totalIngredients;


    private ArrayList<IngredientModel> ingredientList = new ArrayList<>();

    public IngredientAdapter(Context mContext, ArrayList<IngredientModel> ingredientList) {

        this.mContext = mContext;
        this.ingredientList = ingredientList;

    }

    @Override
    public IngredientAdapter.IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_ingredients;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        IngredientViewHolder viewHolder = new IngredientViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.IngredientViewHolder holder, int position) {
        IngredientModel selectedRecipe = ingredientList.get(position);

        ingredientName = selectedRecipe.getIngredients();
        quant = String.valueOf(selectedRecipe.getAmount());
        measure = selectedRecipe.getMeasure();

        totalIngredients = ingredientName + "\n" + quant + " " + measure;
        holder.ingredientName.setText(totalIngredients);

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {


        TextView ingredientName;
        TextView ingredientQuantity;
        TextView ingredientMeasure;


        public IngredientViewHolder(View itemView) {
            super(itemView);

            ingredientName = (TextView) itemView.findViewById(R.id.tv_ingredient);
            ingredientQuantity = (TextView) itemView.findViewById(R.id.tv_quantity);
            ingredientMeasure = (TextView) itemView.findViewById(R.id.tv_measure);



        }

    }

}
