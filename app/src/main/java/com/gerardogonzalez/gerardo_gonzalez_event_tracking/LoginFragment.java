package com.gerardogonzalez.gerardo_gonzalez_event_tracking;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EventDatabase dbHelper;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new EventDatabase(getContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginButton = view.findViewById(R.id.Submit_Button);
        Button registerButton = view.findViewById(R.id.create_Acct);

        EditText usernameEditText = view.findViewById(R.id.user_Name);
        EditText passwordEditText = view.findViewById(R.id.user_Pass);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.nav_view);

        // Hide the bottom navigation view
        bottomNavigationView.setVisibility(View.GONE);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            boolean validated = validationCheck(username, password);
            if (validated) {
                Navigation.findNavController(view).navigate(R.id.navigation_home);
            } else {
                Toast.makeText(getContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            boolean added = dbHelper.addUser(username, password);
            if (added) {
                Toast.makeText(getContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "User already exists", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private boolean validationCheck(String username, String password) {
        return dbHelper.checkUser(username, password);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Get reference to the bottom navigation view
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.nav_view);

        // Show the bottom navigation view
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}