package com.rezwan_cs.hscictlife.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.rezwan_cs.hscictlife.commons.LanguageChangeHelper;
import com.rezwan_cs.hscictlife.commons.ProgressHelper;
import com.rezwan_cs.hscictlife.modelclasses.ChapterModel;
import com.rezwan_cs.hscictlife.modelclasses.McqSetModel;

import java.util.ArrayList;

public class McqSetAdapter extends
        RecyclerView.Adapter<McqSetAdapter.MyMcqSetViewHolder> {
    Context context;
    ArrayList<McqSetModel> mcqSetAdapterArrayList ;

    int[] iconList = {
            R.drawable.ic_one_star,
            R.drawable.ic_two_star,
            R.drawable.ic_three_star,
            R.drawable.ic_four_star,
            R.drawable.ic_five_star,
            R.drawable.ic_six_star,
            R.drawable.ic_seven_star,
            R.drawable.ic_eight_star,
            R.drawable.ic_nine_star,
            R.drawable.ic_ten_star,
    };

    public McqSetAdapter(Context context, ArrayList<McqSetModel> mcqSetAdapterArrayList) {
        this.context = context;
        this.mcqSetAdapterArrayList = mcqSetAdapterArrayList;
    }

    @NonNull
    @Override
    public MyMcqSetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.each_question_set_layout,parent,false);

        return new MyMcqSetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMcqSetViewHolder holder, int position) {
        holder.bind(mcqSetAdapterArrayList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return mcqSetAdapterArrayList.size();
    }

    public class MyMcqSetViewHolder extends RecyclerView.ViewHolder{
        TextView mEachMcqSetNameTxt, mEachMcqSetProgressTxt, mEachSetCompletedCountTxt;
        ProgressBar mEachMcqSetProgressBar;
        ImageView mEachMcqSetIcon;
        public MyMcqSetViewHolder(@NonNull View itemView) {
            super(itemView);

            mEachMcqSetNameTxt= itemView.findViewById(R.id.tv_question_set_name);
            mEachSetCompletedCountTxt= itemView.findViewById(R.id.tv_question_set_completed_count);
            mEachMcqSetProgressTxt = itemView.findViewById(R.id.tv_question_progress);
            mEachMcqSetProgressBar = itemView.findViewById(R.id.pb_question_set_progress);
            mEachMcqSetIcon = itemView.findViewById(R.id.iv_question_set_count);

        }

        public void bind(final McqSetModel item, final int position) {

            mEachMcqSetNameTxt.setText(LanguageChangeHelper.getMcqSetName(position+1));

            mEachSetCompletedCountTxt.setText(Html.fromHtml(
                    ProgressHelper.getHowManyTimesMcqSetRead(item.getHowManyTimesRead())));
            mEachMcqSetProgressTxt.setText(
                    ProgressHelper.getMcqSetProgressText(
                            item.getDoneMcq(),item.getTotalMcq()));

            mEachMcqSetProgressBar.setProgress(
                    ProgressHelper.getPercentagesOfProgress(item.getDoneMcq(),
                            item.getTotalMcq()));

            mEachMcqSetIcon.setImageResource(iconList[position]);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, position+1+"", Toast.LENGTH_SHORT).show();
                }
            });
        }



    }

}
