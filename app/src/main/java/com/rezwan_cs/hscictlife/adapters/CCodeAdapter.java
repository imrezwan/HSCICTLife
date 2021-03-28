package com.rezwan_cs.hscictlife.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.LanguageChangeHelper;
import com.rezwan_cs.hscictlife.dialogs.SeeCodeDialog;
import com.rezwan_cs.hscictlife.modelclasses.CCode;
import com.rezwan_cs.hscictlife.modelclasses.LastStudyResult;

import java.util.ArrayList;

public class CCodeAdapter extends RecyclerView.Adapter<CCodeAdapter.ViewHolder> {



    Context context;
    ArrayList<CCode> arrayList = new ArrayList<>();
    LayoutInflater inflater;

    public CCodeAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_ccode, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    public void setResults(ArrayList<CCode> newAra){
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
        TextView mTitle, mCodes, mInputs, mOutputs;
        Button mSeeCodes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_code_serial);
            mCodes = itemView.findViewById(R.id.tv_code_snippet);
            mSeeCodes = itemView.findViewById(R.id.btn_code_see);
        }

        public void bind(int position) {
            CCode cCode = arrayList.get(position);

            mTitle.setText("কোড "+ LanguageChangeHelper.englishToBanglaNumber(cCode.getId()+"")+": ");
            if(cCode.getCodes().length()> 65){
                mCodes.setText(cCode.getCodes().substring(0,64));
            }
            else{
                mCodes.setText(cCode.getCodes());
            }

            mSeeCodes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("CCODEN", cCode.getInputs());

                    SeeCodeDialog seeCodeDialog = new SeeCodeDialog(context, cCode);
                    seeCodeDialog.setCancelable(true);
                    seeCodeDialog.show();
                }
            });

        }
    }
}
