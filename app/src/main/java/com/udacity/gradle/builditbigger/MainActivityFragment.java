package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public InterstitialAd interstitialAd;
    OnInterResult mCallResult;
    OnJokeChosenListener mJokeChosen;
    String[] mJokeArray;

    public interface OnInterResult {
        public void onResult(boolean result, InterstitialAd iA);
    }

    public interface OnJokeChosenListener {
        public void onJokeChosen(int jokeNo);
    }

    public MainActivityFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallResult = (OnInterResult) getActivity();
            mJokeChosen = (OnJokeChosenListener) getActivity();
        } catch (ClassCastException e) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        //Kill it for paid straight away


        if (BuildConfig.VERSION.equals("FREE")) {
            AdView mAdView = (AdView) root.findViewById(R.id.adView);
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);

            interstitialAd = new InterstitialAd(getContext());
            interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdOpened() {
                    super.onAdOpened();
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mCallResult.onResult(true, interstitialAd);
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    super.onAdFailedToLoad(errorCode);
                    mCallResult.onResult(false, interstitialAd);
                }
            });

            AdRequest adStatialRequest = new AdRequest.Builder().build();

            interstitialAd.loadAd(adStatialRequest);
        }

        mJokeArray = getResources().getStringArray(R.array.me_jokes_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.list_joke, mJokeArray);
        ListView listView = (ListView) root.findViewById(R.id.jokeList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mJokeChosen.onJokeChosen(position);
            }
        });

        return root;

    }

}
