package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerView;
    List<Artikal> slideImages2 = new ArrayList<>();

    Artikal a1 = new Artikal("Srce torta", "t1.jpg", "ova divna torta od jagoda je napravljena mešanjem svežih jagoda u sočno testo,\n stvarajući nalet voćne dobrote sa svakim zalogajem.", 250, "jaja, secer, voda, brasno,i naravno, sočne jagode.", true);
    Artikal a2 = new Artikal("Cheesecake", "cheesecake.jpg", "Prepustite se kremastoj eleganciji našeg kolača od sira, gde se baršunasta mešavina krem sira, jaja \n i šećera delikatno peče do savršenstva, stvarajući nebeski desert koji se topi u ustima.", 480, "jaja, secer, voda, brasno, bogata dobrota krem sira,maline.", true);
    Artikal a3 = new Artikal("Vocna torta", "t2.jpg", "Doživite žarki ples ukusa uz našu tortu od limete i kivija. Oštre note limete i tropske slatkoće\n  kivija spajaju se u harmoničnu fuziju, čineći svaku krišku osvežavajućim užitkom.", 195, "jaja, secer, voda, brasno,limeta,kivi", true);
    Artikal a4 = new Artikal("Borovnica torta", "t3.jpg", "Uronite u slatku simfoniju borovnica u svakom sloju ove torte. Prasak dobrote borovnice isprepleten je sa\n  vlažnom i mekanom teksturom za odličnu poslasticu.", 370, "jaja, secer, voda, brasno,borovnice", true);
    Artikal a5 = new Artikal("Cokolada torta", "n.jpg", "Prepustite se dekadenciji naše čokoladne torte. Bogat kakao u kombinaciji sa baršunastim testom\n  stvara vlažan i ugodan doživljaj, čineći ga istinskim slavljem za ljubitelje čokolade.", 400, "jaja, secer, voda, brasno,cokolada", true);
    Artikal a6 = new Artikal("Bela torta", "t4.jpg", "Prihvatite jednostavnost i eleganciju naše bele torte. Delikatna mešavina sastojaka daje laganu i\n  mekanu teksturu, pružajući platno za vaš izbor glazura ili preliva.", 325, "jaja, secer, voda, brasno", true);
    private Integer[] mImageResources = {R.drawable.t1, R.drawable.cheesecake, R.drawable.t2,
            R.drawable.t3, R.drawable.n, R.drawable.t4};


    public TFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TFragment newInstance(String param1, String param2) {
        TFragment fragment = new TFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t, container, false);
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