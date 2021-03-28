package com.rezwan_cs.hscictlife.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.LanguageChangeHelper;
import com.rezwan_cs.hscictlife.modelclasses.LastStudyResult;

import java.util.ArrayList;

public class LastExamResultAdapter extends RecyclerView.Adapter<LastExamResultAdapter.ViewHolder> {
    Context context;
    ArrayList<LastStudyResult> arrayList = new ArrayList<>();
    LayoutInflater inflater;

    public LastExamResultAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_exam_result, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    public void setResults(ArrayList<LastStudyResult> newAra){
        Log.d("HOMEA", newAra.toString());

        arrayList.addAll(newAra);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(arrayList != null){
            return arrayList.size();
        }
        else{
            return  0;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mExamSerialTime, mChapterList, mCorrectAns, mWrongAns, mTotalAns;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mExamSerialTime = itemView.findViewById(R.id.tv_exam_serial_time);
            mChapterList = itemView.findViewById(R.id.tv_chapter_list);
            mCorrectAns = itemView.findViewById(R.id.tv_correct_ans);
            mWrongAns = itemView.findViewById(R.id.tv_wrong_ans);
            mTotalAns = itemView.findViewById(R.id.tv_total);
        }

        public void bind(int position) {
            LastStudyResult studyResult = arrayList.get(position);

            mExamSerialTime.setText("পরীক্ষা "+
                    LanguageChangeHelper.englishToBanglaNumber(studyResult.getId()+"")+" : "
            + studyResult.getTime() + " মিনিট");

            mChapterList.setText(studyResult.getChapterList());

            mCorrectAns.setText(studyResult.getCorrectAnswer()+"");
            mWrongAns.setText(studyResult.getWrongAnswer()+"");
            mTotalAns.setText(studyResult.getTotalQuestions()+"");
        }
    }
}
