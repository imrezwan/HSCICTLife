package com.rezwan_cs.hscictlife.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.LanguageChangeHelper;
import com.rezwan_cs.hscictlife.modelclasses.ExamMcqModel;
import com.rezwan_cs.hscictlife.interfaces.IMcqModel;

import java.util.ArrayList;

public class McqStateAdapter extends
        RecyclerView.Adapter<McqStateAdapter.MyMcqStateViewHolder> {
    Context context;
    ArrayList<IMcqModel> mcqStateArrayList ;
    OnMcqStateClicked listener;
    int onScreenPos, prevOnScreen;

    public McqStateAdapter(Context context, ArrayList<IMcqModel> mcqStateArrayList, OnMcqStateClicked listener) {
        this.context = context;
        this.mcqStateArrayList = mcqStateArrayList;
        onScreenPos = 0;
        prevOnScreen = 0;
        this.listener = listener;
    }

    public interface OnMcqStateClicked{
        void mcqNumberClicked(int pos);
    }

    public void changeMcqState(int pos, ExamMcqModel.STATE mcqState){
        if(mcqStateArrayList.get(pos) instanceof ExamMcqModel){
            ((ExamMcqModel)mcqStateArrayList.get(pos)).setMcqState(mcqState);
        }
        notifyItemChanged(pos);
    }

    public void setOnScreenPosition(int pos){
        prevOnScreen = onScreenPos;
        onScreenPos = pos;
        notifyItemChanged(pos);
        notifyItemChanged(prevOnScreen);

    }

    @NonNull
    @Override
    public MyMcqStateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.each_question_state_layout,parent,false);

        return new MyMcqStateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMcqStateViewHolder holder, int position) {
        holder.bind((ExamMcqModel) mcqStateArrayList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return mcqStateArrayList.size();
    }

    public class MyMcqStateViewHolder extends RecyclerView.ViewHolder{
        TextView mEachMcqSerialTxt;
        ImageView mEachMcqStateIcon;
        LinearLayout mMcqStateContainer;

        public MyMcqStateViewHolder(@NonNull View itemView) {
            super(itemView);

            mEachMcqSerialTxt= itemView.findViewById(R.id.tv_question_state_number);
            mEachMcqStateIcon= itemView.findViewById(R.id.tv_question_state_icon);
            mMcqStateContainer = itemView.findViewById(R.id.ll_mcq_state_container_layout);
        }

        public void bind(final ExamMcqModel item, final int position) {
            mEachMcqSerialTxt.setText("প্রশ্ন "+ LanguageChangeHelper.englishToBanglaNumber(position+1+""));

            setUpStateIcon(item.getMcqState());

            setUpMcqContainerBackground(position);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.mcqNumberClicked(position);
                    Toast.makeText(context, position+1+"", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void setUpMcqContainerBackground(int position) {
            if(position == onScreenPos){
                mMcqStateContainer.setBackgroundResource(R.drawable.current_mcq_state_bg);
            }
            else{
                mMcqStateContainer.setBackgroundResource(R.drawable.default_mcq_state_bg);
            }
        }

        private void setUpStateIcon(ExamMcqModel.STATE state) {
            if(state == ExamMcqModel.STATE.UNANSWERED){
                mEachMcqStateIcon.setImageResource(R.drawable.ic_no_answer);
            }
            else if(state == ExamMcqModel.STATE.CURRECT){
                mEachMcqStateIcon.setImageResource(R.drawable.ic_correct_answer);
            }
            else if(state == ExamMcqModel.STATE.WRONG){
                mEachMcqStateIcon.setImageResource(R.drawable.ic_incorrect_answer);
            }

        }
    }

}
