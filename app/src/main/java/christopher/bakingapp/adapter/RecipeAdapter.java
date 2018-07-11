package christopher.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.RecipeModel;
import christopher.bakingapp.ui.activities.StepActivity;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context mContext;


    private ArrayList<RecipeModel> recipeList = new ArrayList<>();


    public RecipeAdapter(Context mContext, ArrayList<RecipeModel> recipeList) {

        this.mContext = mContext;
        this.recipeList = recipeList;

    }

    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item_recipes;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeViewHolder holder, int position) {
        RecipeModel selectedRecipe = recipeList.get(position);
        holder.name.setText(selectedRecipe.getRecipeName());

        if (position == 0) {

            Glide.with(mContext)
                    .load(R.drawable.nutellapie)
                    .into(holder.recPic);

        } else if (position == 1) {
            Glide.with(mContext)
                    .load(R.drawable.brownie)
                    .into(holder.recPic);
        } else if (position == 2) {
            Glide.with(mContext)
                    .load(R.drawable.yellowcake)
                    .into(holder.recPic);
        } else {
            Glide.with(mContext)
                    .load(R.drawable.cheesecake)
                    .into(holder.recPic);
        }

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {


        TextView name;
        ImageView recPic;


        public RecipeViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tv_recipe);
            recPic = (ImageView) itemView.findViewById(R.id.recipePicture);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickedPosition = getAdapterPosition();
                    Intent intent = new Intent(mContext, StepActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("steps", (ArrayList<? extends Parcelable>) recipeList.get(clickedPosition).getSteps());
                    bundle.putParcelableArrayList("ingredients", (ArrayList<? extends Parcelable>) recipeList.get(clickedPosition).getIngredients());
                    bundle.putString("recipeNames", recipeList.get(clickedPosition).getRecipeName());
                    intent.putExtra("recipeBundle", bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        }

    }


}
