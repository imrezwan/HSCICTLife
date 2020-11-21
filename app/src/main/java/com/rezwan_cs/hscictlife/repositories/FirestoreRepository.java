package com.rezwan_cs.hscictlife.repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rezwan_cs.hscictlife.interfaces.IMcqModel;
import com.rezwan_cs.hscictlife.modelclasses.ExamMcqModel;
import com.rezwan_cs.hscictlife.modelclasses.McqModel;
import com.rezwan_cs.hscictlife.modelclasses.PracticeMcqModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class FirestoreRepository {

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference quizListRef ;
    private OnExamFirestoreRepository examFirestoreRepository;
    private OnPracticeFirestoreRepository practiceFirestoreRepository;

    public FirestoreRepository(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);

        quizListRef = firebaseFirestore.collection("mcq");
    }

    public void setUpExamRepository(OnExamFirestoreRepository examFirestoreRepository){
        this.examFirestoreRepository = examFirestoreRepository;
    }

    public void setUpPracticeRepository(OnPracticeFirestoreRepository practiceRepository){
        this.practiceFirestoreRepository = practiceRepository;
    }

    public void getPracticeQuizdata(int chapterNumber, int mcqSetNumber){
        quizListRef.whereEqualTo("chapter", chapterNumber)
                .whereEqualTo("questionset", mcqSetNumber)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){
                    Log.d("TAG_FirebaseRepo: ", task.getResult().toObjects(PracticeMcqModel.class).toString());
                    practiceFirestoreRepository.quizListPracticeDataAdded(task.getResult().toObjects(PracticeMcqModel.class));
                }
                else{
                    practiceFirestoreRepository.onError(task.getException());
                    Log.d("TAG_FirebaseRepo: ", task.getException().getMessage());
                }
            }
        });
    }


    public void getExamQuizdata(final int mcqTotal, ArrayList<Integer> chapterlist){


        quizListRef.whereIn("chapter", chapterlist).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                if(task.isSuccessful()){
                    //Log.d("TAG_FirebaseRepo: ", task.getResult().toObjects(ExamMcqModel.class).toString());
                    List<ExamMcqModel> examMcqModelArrayList = new ArrayList<>();
                    List<ExamMcqModel> examMcqFirstList = task.getResult().toObjects(ExamMcqModel.class);

                    QuerySnapshot documentSnapshot = task.getResult();

                    int documentSize = documentSnapshot.getDocuments().size();

                    for(int i=0;i<(mcqTotal*2) && i<documentSize;i++){

                        ExamMcqModel examMcqModel = new ExamMcqModel(
                                Objects.requireNonNull(documentSnapshot.getDocuments().get(i).getData()));
                        examMcqFirstList.add(examMcqModel);
                    }
                    Collections.shuffle(examMcqFirstList);

                    examFirestoreRepository.quizListExamDataAdded(examMcqFirstList.subList(0,mcqTotal));
                }
                else{
                    examFirestoreRepository.onError(task.getException());
                    Log.d("TAG_FirebaseRepo: ", task.getException().getMessage());
                }
            }
        });
    }


    public interface OnExamFirestoreRepository{
        void quizListExamDataAdded(List<ExamMcqModel> quizListModelList);
        void onError(Exception e);
    }

    public interface OnPracticeFirestoreRepository{
        void quizListPracticeDataAdded(List<PracticeMcqModel> quizListModelList);
        void onError(Exception e);
    }
}
