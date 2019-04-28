package com.example.administrator.a20161003441.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.a20161003441.BR;
import com.example.administrator.a20161003441.R;
import com.example.administrator.a20161003441.databinding.DatabindingTestBinding;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnHomePagerInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomePagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePagerFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POS = "position";

    private int currentPos;
    private DatabindingTestBinding mBinding;

    private OnHomePagerInteractionListener mListener;

    public HomePagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pos Parameter 1.
     * @return A new instance of fragment HomePagerFragment.
     */
    public static HomePagerFragment newInstance(int pos) {
        HomePagerFragment fragment = new HomePagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POS, pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentPos = getArguments().getInt(ARG_POS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View root = inflater.inflate(R.layout.fragment_home_pager, container, false);
//        Button btn = root.findViewById(R.id.btn_home_pager);
//        btn.setText("欢迎来到第 "+ currentPos +" 主页");

        //Using databinding:
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.databinding_test, container, false);

        //设置databinding中的data
        mBinding.setText("First DataBinding.");
        mBinding.setVariable(BR.text,"set text again!");
        mBinding.setVariable(BR.action, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mListener.onHomePagerInteraction(v);
            }
        });

        //使得赋值立即效效。
        mBinding.executePendingBindings();
        return mBinding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomePagerInteractionListener) {
            mListener = (OnHomePagerInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHomeInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnHomePagerInteractionListener {
        // TODO: Update argument type and name
        void onHomePagerInteraction(View v);
    }
}
