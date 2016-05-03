package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    OnJokeChosenListener mJokeChosen;
    String[] mJokeArray;



    public interface OnJokeChosenListener {
        public void onJokeChosen(int jokeNo);
    }

    public MainActivityFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            mJokeChosen = (OnJokeChosenListener) getActivity();
        } catch (ClassCastException e) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);



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
