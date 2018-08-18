package com.leogaming.leogamingtest.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leogaming.leogamingtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Nikita Skubak on 24.07.2018.
 */

public class DialogFragment extends BottomSheetDialogFragment {

    private static final String TITLE_KEY = "TITLE_KEY";
    private static final String DESCRIPTION_KEY = "DESCRIPTION_KEY";
    public static final String TAG = "DialogFragment";

    @BindView(R.id.close_view)
    ImageView closeButton;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.description)
    TextView description;

    Unbinder unbinder;

    public static DialogFragment newInstance(String title, String description) {
        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        args.putString(DESCRIPTION_KEY, description);
        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        closeButton.setOnClickListener(v -> dismiss());
        title.setText(getArguments().getString(TITLE_KEY));
        description.setText(getArguments().getString(DESCRIPTION_KEY));
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
