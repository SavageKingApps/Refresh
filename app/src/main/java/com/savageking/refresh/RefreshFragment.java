package com.savageking.refresh;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


public class RefreshFragment extends Fragment {

    private final static String TAG = "RefreshFragment";

    private View.OnClickListener toolBarClick;
    private OnRefreshListener refreshListener;
    private CompoundButton.OnCheckedChangeListener switchListener;

    public static RefreshFragment getInstance()
    {
        return new RefreshFragment();
    }
    public static String getInstanceTag()
    {
        return TAG;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolBarClick = new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        };

        refreshListener = new OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                final View view = getView();
                final Switch refreshSwitch = view.findViewById( R.id.main_switch );
                refreshSwitch.setChecked(true);
            }
        };

        switchListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                final View view = getView();
                final SwipeRefreshLayout swipe = view.findViewById(R.id.refresh);

                if( isChecked && !swipe.isRefreshing())
                {
                    Toast.makeText( view.getContext() , R.string.toast_text, Toast.LENGTH_LONG ).show();
                    buttonView.setChecked(false);
                }

                else if( !isChecked )
                {
                    swipe.setRefreshing(false);
                }
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_refresh, container, false);

        //get the toolbar
        final Toolbar toolBar = view.findViewById(R.id.toolbar);
        toolBar.setTitle(R.string.toolbar_title);
        toolBar.setSubtitle(R.string.toolbar_subtitle);
        toolBar.setNavigationIcon( R.drawable.ic_clear_mtrl_alpha );
        toolBar.setNavigationOnClickListener( toolBarClick );

        final SwipeRefreshLayout swipe = view.findViewById(R.id.refresh);
        swipe.setColorSchemeColors( Color.BLUE, Color.RED, Color.CYAN, Color.GREEN );
        swipe.setOnRefreshListener( refreshListener );

        final Switch refreshSwitch = view.findViewById( R.id.main_switch );
        refreshSwitch.setOnCheckedChangeListener( switchListener );

        return view;
    }
}
