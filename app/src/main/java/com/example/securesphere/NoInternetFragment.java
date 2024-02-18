package com.example.securesphere;

/* App By Kiruthik Suriyah M
   last updated: 18-Feb-2024 */

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.net.ConnectivityManager;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.sql.Connection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoInternetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoInternetFragment extends Fragment {

    ImageButton retry;

    LottieAnimationView load_ani, no_net_ani;

    TextView txt;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NoInternetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoInternetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoInternetFragment newInstance(String param1, String param2) {
        NoInternetFragment fragment = new NoInternetFragment();
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

        View root = inflater.inflate(R.layout.fragment_no_internet, container, false);
        retry = root.findViewById(R.id.retry_bt);
        txt = root.findViewById(R.id.no_net_txt);
        no_net_ani = root.findViewById(R.id.no_net_ani);
        load_ani = root.findViewById(R.id.loadani2);
        load_ani.setVisibility(View.GONE);
        boolean Connection = haveNetworkConnection();

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setVisibility(View.GONE);
                no_net_ani.setVisibility(View.GONE);
                retry.setVisibility(View.GONE);
                load_ani.setVisibility(View.VISIBLE);
                boolean Connection = haveNetworkConnection();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!Connection){
                            txt.setVisibility(View.VISIBLE);
                            no_net_ani.setVisibility(View.VISIBLE);
                            retry.setVisibility(View.VISIBLE);
                            load_ani.setVisibility(View.GONE);
                        }else {
                            //replaceFragment(new FeedFragment());
                            getActivity().getSupportFragmentManager().popBackStack();
                        }

                    }
                }, 3000);
            }
        });



        return root;
    }

    private void replaceFragment(Fragment fragment) {

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .setCustomAnimations(
                        R.anim.fadein,
                        R.anim.fadeout
                )
                .replace(R.id.Frg_1, fragment)
                .commit();
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void onBackPressed() {
        int fragments = getActivity().getSupportFragmentManager().getBackStackEntryCount();

    }
}