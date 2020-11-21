package com.rezwan_cs.hscictlife.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.LanguageChangeHelper;
import com.rezwan_cs.hscictlife.modelclasses.ExamMcqModel;
import com.rezwan_cs.hscictlife.interfaces.IMcqModel;
import com.rezwan_cs.hscictlife.modelclasses.McqModel;
import com.rezwan_cs.hscictlife.modelclasses.PracticeMcqModel;

import java.util.ArrayList;

public class McqNavigationAdapter extends
        RecyclerView.Adapter<McqNavigationAdapter.myViewHolder> {

    Context context;
    ArrayList<IMcqModel> mData;

    public McqNavigationAdapter(Context context, ArrayList<IMcqModel> data) {
        this.context = context;
        this.mData = data;
    }

    @Override
    public McqNavigationAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.navigation_recylerview_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(McqNavigationAdapter.myViewHolder holder, int position)
    {
        McqModel mcqModel = new McqModel();
        if ( mData.get(position) instanceof ExamMcqModel){
            mcqModel = (ExamMcqModel) mData.get(position);
        }
        else if ( mData.get(position) instanceof PracticeMcqModel){
            mcqModel = (PracticeMcqModel) mData.get(position);
        }

        String question = LanguageChangeHelper
                .englishToBanglaNumber(position+1+"")
               +". " +mcqModel.getQuestion();
        holder.mMcqQuestion.setText(question);

        String options = "ক) "+mcqModel.getOption1()+"<br>"+
                "খ) "+mcqModel.getOption2()+"<br>"+
                "গ) "+mcqModel.getOption3()+"<br>"+
                "ঘ) "+mcqModel.getOption4()+"";

        holder.mMcqOptions.setText(Html.fromHtml(options));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView mMcqQuestion, mMcqOptions;

        public myViewHolder(View itemView) {
            super(itemView);
            mMcqQuestion = itemView.findViewById(R.id.nav_mcq_guide_question);
            mMcqOptions = itemView.findViewById(R.id.nav_mcq_guide_options);
        }
    }

}