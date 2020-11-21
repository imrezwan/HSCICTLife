package com.rezwan_cs.hscictlife.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    int chapterMcqSetTotal ;
    IOnMcqSetClicked onMcqSetClicked;

    public interface IOnMcqSetClicked{
        void onMcqSetClicked(int mcqSetNumber);
    }

    int[] iconList = {
            R.drawable.ic_one_star,
            R.drawable.ic_two_star,
            R.drawable.ic_three_star,
            R.drawable.ic_four_star,
            R.drawable.ic_five_star,
            R.drawable.ic_six_star,
            R.drawable.ic_seven_star,
            R.drawable.ic_eight_star
    };

    public McqSetAdapter(Context context, ArrayList<McqSetModel> mcqSetAdapterArrayList, int chapterMcqSetTotal,
                         IOnMcqSetClicked onMcqSetClicked) {
        this.context = context;
        this.mcqSetAdapterArrayList = mcqSetAdapterArrayList;
        this.chapterMcqSetTotal = chapterMcqSetTotal;
        this.onMcqSetClicked = onMcqSetClicked;
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
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return chapterMcqSetTotal;
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

        public void bind(final int position) {
            McqSetModel item = position<mcqSetAdapterArrayList.size()?mcqSetAdapterArrayList.get(position):
                    new McqSetModel(20, 0, 0);
            mEachMcqSetNameTxt.setText(LanguageChangeHelper.getMcqSetName(position+1));

            long doneMcq = item != null?item.getDoneMcq():0;
            long totalMcq =  item != null? item.getTotalMcq():20;
            long completed = item != null? item.getHowManyTimesRead():0;

            mEachSetCompletedCountTxt.setText(Html.fromHtml(
                    ProgressHelper.getHowManyTimesMcqSetRead(completed)));
            mEachMcqSetProgressTxt.setText(
                    ProgressHelper.getMcqSetProgressText(
                            doneMcq,totalMcq));

            mEachMcqSetProgressBar.setProgress(
                    ProgressHelper.getPercentagesOfProgress(doneMcq,
                            totalMcq));

            mEachMcqSetIcon.setImageResource(iconList[position]);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMcqSetClicked.onMcqSetClicked(position+1);
                }
            });
        }



    }

}
