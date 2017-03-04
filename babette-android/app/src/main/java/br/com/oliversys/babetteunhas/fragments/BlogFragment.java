package br.com.oliversys.babetteunhas.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.oliversys.babetteunhas.Constants;
import br.com.oliversys.babetteunhas.OnFragmentInteractionListener;
import pedepizza.oliversoft.com.br.babetteunhas.R;

public class BlogFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    public static BlogFragment newInstance(int sectionNumber) {
        BlogFragment fragment = new BlogFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public BlogFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

// SE FOR CRIAR SUB-MENUS/SUB-FRAGMENTS PARA BLOG

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.customer_list_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        switch (id){
//            case R.id.action_add_customer:
//                MainActivity myActivity = (MainActivity)getActivity();
//                myActivity.ReplaceFragment(Enums.FragmentEnums.CustomerDetailsFragment, 4);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
