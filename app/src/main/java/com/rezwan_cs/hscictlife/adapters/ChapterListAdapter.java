package com.rezwan_cs.hscictlife.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.interfaces.ChapterClicked;
import com.rezwan_cs.hscictlife.modelclasses.ChapterModel;

import java.util.ArrayList;

public class ChapterListAdapter extends
        RecyclerView.Adapter<ChapterListAdapter.MyChapterViewHolder> {
        Context context;
        ArrayList<ChapterModel> chapterModelsArrayList ;
        ChapterClicked chapterClickedListener;


    public ChapterListAdapter(Context context, ArrayList<ChapterModel> chapterModelsArrayList, ChapterClicked chapterClickedListener) {
        this.context = context;
        this.chapterModelsArrayList = chapterModelsArrayList;
        this.chapterClickedListener = chapterClickedListener;
    }

    @NonNull
    @Override
    public MyChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.each_chapter_layout,parent,false);

        return new MyChapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChapterViewHolder holder, int position) {
        holder.bind(chapterModelsArrayList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return chapterModelsArrayList.size();
    }

    public class MyChapterViewHolder extends RecyclerView.ViewHolder{
        TextView mEachChapterCountTxt, mEachChapterNameTxt;
        ImageView mEachChapterIcon;
        public MyChapterViewHolder(@NonNull View itemView) {
            super(itemView);

            mEachChapterCountTxt= itemView.findViewById(R.id.tv_chapter_count_num);
            mEachChapterNameTxt = itemView.findViewById(R.id.tv_chapter_name);
            mEachChapterIcon = itemView.findViewById(R.id.iv_chapter_icon);

        }

        public void bind(final ChapterModel item, final int position) {
            mEachChapterIcon.setImageResource(item.getChapterIcon());
            mEachChapterNameTxt.setText(item.getChapterName());
            mEachChapterCountTxt.setText(item.getChapterCountName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getChapterCountName(), Toast.LENGTH_SHORT).show();
                    chapterClickedListener.onChapterClicked(position+1);
                }
            });
        }



    }
}