package com.rezwan_cs.hscictlife.fragments.mainpage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.activities.MainActivity;
import com.rezwan_cs.hscictlife.adapters.CCodeAdapter;
import com.rezwan_cs.hscictlife.adapters.LastExamResultAdapter;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.rezwan_cs.hscictlife.commons.SharedPrefHelper;
import com.rezwan_cs.hscictlife.modelclasses.CCode;
import com.rezwan_cs.hscictlife.modelclasses.LastStudyResult;
import com.rezwan_cs.hscictlife.utilities.CCodeDao;
import com.rezwan_cs.hscictlife.utilities.ICTDatabase;
import com.rezwan_cs.hscictlife.utilities.LastStudyResultDao;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    TextView mName;
    RecyclerView mLastStudyRv;
    LastExamResultAdapter examResultAdapter;
    ArrayList<LastStudyResult> lastStudyResultArrayList= new ArrayList<>();
    LinearLayout mStudyResultNotFoundLayout, mCodeNotFoundLayout;

    RecyclerView mLastCodeRv;
    CCodeAdapter cCodeAdapter;
    ArrayList<CCode> cCodeArrayList = new ArrayList<>();

    Button mGiveExam, mRunCode;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViews(view);
        setName();

        restoreExamDataFromDB();
        setUpLastExamRecyclerView();

        restoreCodeDataFromDB();
        setUpLastCodeRecyclerView();

        mGiveExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = QuizFragment.newInstance();
                loadFragment(fragment);
            }
        });

        mRunCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = ToolsFragment.newInstance();
                loadFragment(fragment);
            }
        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations
                (android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        transaction.replace(R.id.container_area, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void restoreCodeDataFromDB() {
        Log.d("HOMEC", cCodeArrayList.toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                ICTDatabase ictDatabase = ICTDatabase.getInstance(getContext());
                CCodeDao cCodeDao = ictDatabase.cCodeDao();
                cCodeArrayList.addAll(cCodeDao.getAll());

                new Handler(Looper.getMainLooper()).post(new Runnable(){
                    @Override
                    public void run() {
                        cCodeAdapter.setResults(cCodeArrayList);
                        if(cCodeArrayList.size() > 0){
                            mLastCodeRv.setVisibility(View.VISIBLE);
                            mCodeNotFoundLayout.setVisibility(View.GONE);
                        }
                        else{
                            mLastCodeRv.setVisibility(View.GONE);
                            mCodeNotFoundLayout.setVisibility(View.VISIBLE);
                        }
                    }
                });

                Log.d("HOMEC", cCodeArrayList.toString());


            }
        }).start();
    }

    private void setUpLastCodeRecyclerView() {
        cCodeAdapter = new CCodeAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL, false);

        mLastCodeRv.setAdapter(cCodeAdapter);
        mLastCodeRv.setLayoutManager(linearLayoutManager);
    }

    private void setUpLastExamRecyclerView() {
        examResultAdapter = new LastExamResultAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);

        mLastStudyRv.setAdapter(examResultAdapter);
        mLastStudyRv.setLayoutManager(linearLayoutManager);
    }

    private void restoreExamDataFromDB() {
        Log.d("HOME", lastStudyResultArrayList.toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                ICTDatabase ictDatabase = ICTDatabase.getInstance(getContext());
                LastStudyResultDao lastStudyResultDao = ictDatabase.lastStudyResultDao();
                lastStudyResultArrayList.addAll(lastStudyResultDao.getAll());


                new Handler(Looper.getMainLooper()).post(new Runnable(){
                    @Override
                    public void run() {
                        examResultAdapter.setResults(lastStudyResultArrayList);
                        if(lastStudyResultArrayList.size() > 0){
                            mLastStudyRv.setVisibility(View.VISIBLE);
                            mStudyResultNotFoundLayout.setVisibility(View.GONE);
                        }
                        else{
                            mLastStudyRv.setVisibility(View.GONE);
                            mStudyResultNotFoundLayout.setVisibility(View.VISIBLE);
                        }
                    }
                });

                Log.d("HOME", lastStudyResultArrayList.toString());


            }
        }).start();
    }

    private void setName() {
        mName.setText(SharedPrefHelper.getString(getContext(), Constants.NAME_KEY, "ব্যবহারকারী") );
    }

    private void findViews(View view) {
        mName = view.findViewById(R.id.tv_user_name);
        mLastStudyRv = view.findViewById(R.id.rv_exam_result_list);
        mStudyResultNotFoundLayout = view.findViewById(R.id.ll_no_exam);
        mGiveExam = view.findViewById(R.id.btn_give_exam);

        mLastCodeRv = view.findViewById(R.id.rv_code_run_list);
        mCodeNotFoundLayout = view.findViewById(R.id.ll_no_code);

        mRunCode = view.findViewById(R.id.btn_code_run);

    }
}