package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class KFragment extends Fragment  {

    private RecyclerView mRecyclerView;
    private Integer[] mImageResources = {R.drawable.cc, R.drawable.ck, R.drawable.cd,R.drawable.kolaci, R.drawable.jag, R.drawable.ck};
    List<Artikal> slideImages2 = new ArrayList<>();
    Artikal a1 = new Artikal("Cokoladni mafin", "cc.jpg", "Ovaj čokoladni mafin osvaja svojom bogatom čokoladnom notom i mekanom teksturom.\n Savršen je za ljubitelje čokolade koji žele uživati u ukusnom zalogaju.", 250, "jaja, secer, voda, brasno", false);
    Artikal a2 = new Artikal("Sareni mafin", "ck.jpg", "Sareni mafin donosi radost boja i ukusa. \nSvaki zalogaj je šareno iskustvo, savršeno za sve koji vole veselje i raznolikost", 150, "jaja, secer, voda, brasno", false);
    Artikal a3 = new Artikal("Plavi mafin", "cd.jpg", "Plavi mafin osvaja svojom jednostavnošću i ukusom. \nSavršen je izbor za one koji vole klasične arome i sočnost.", 370, "jaja, secer, voda, brasno", false);
    Artikal a4 = new Artikal("Plavo beli mafin", "kolaci.jpg", "Ovaj mafin kombinuje harmoniju plave i bele boje s ukusom koji oduševljava.\n Jedinstveni spoj vizualnog užitka i fantastičnog ukusa.", 300, "jaja, secer, voda, brasno", false);
    Artikal a5 = new Artikal("Jagoda mafin", "jag.jpg", "Jagoda mafin donosi svežinu jagoda u svaki zalogaj. \nSočnost ovog mafina čini ga savršenim desertom za sve ljubitelje voćnih poslastica.", 195, "jaja, secer, voda, brasno", false);
    Artikal a6 = new Artikal("Roze plavi mafini", "ck.jpg", "Roze plavi mafini spoj su nežnosti roze boje i osvežavajućeg ukusa. \nIdealan izbor za posebne trenutke i sladokusce koji vole neobične kombinacije.", 400, "jaja, secer, voda, brasno", false);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_k, container, false);
        slideImages2.add(a1);
        slideImages2.add(a2);
        slideImages2.add(a3);
        slideImages2.add(a4);
        slideImages2.add(a5);
        slideImages2.add(a6);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ImageAdapter2 adapter = new ImageAdapter2(getContext(), mImageResources);
        adapter.setOnImageClickListener(new ImageAdapter2.OnImageClickListener() {
            @Override
            public void onImageClick(int position) {
                Artikal a = slideImages2.get(position);
                Intent intent = new Intent(getActivity(), Details.class);
                intent.putExtra("clickedImage", a);
                Snackbar.make(view, "kliknuta poz" + position, Snackbar.LENGTH_SHORT).show();

                startActivity(intent);            }
        });
        mRecyclerView.setAdapter(adapter);
        return view;
    }


}
