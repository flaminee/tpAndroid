package com.example.tp1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.tp1.databinding.FragmentFirstBinding;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private ClientViewModel myData;
    private OnFragment1InteractionListener mListener;

    private static final String ARG_PARAM1 = "noData";
    private static final String ARG_PARAM2 = "noData";

    private String name;
    private String place;

    // my Shared data between the fragments


    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance(String name, String place) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putString(ARG_PARAM2, place);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            place = getArguments().getString(ARG_PARAM2);
        }
    }





    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        ClientViewModel clientViewModel = new ClientViewModel();
        binding.setViewModel(clientViewModel);

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_first, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cleanForm) {
            binding.phoneInput.setText("");
            binding.dateInput.setText("");
            binding.placeInput.setText("");
            binding.firstNameInput.setText("");
            binding.lastNameInput.setText("");
        }
        if(id == R.id.addPhone){

            EditText phoneInput = binding.phoneInput;

            if( phoneInput.getVisibility() == View.GONE){
                phoneInput.setVisibility(View.VISIBLE);
                item.setTitle("Enlever son numéro de téléphone");
            } else {
                phoneInput.setVisibility(View.GONE);
                item.setTitle("Ajouter son numéro de téléphone");
            }
            return true;
        }
        if(id == R.id.wiki){
            String place = binding.placeInput.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://fr.wikipedia.org/wiki/" + place));
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity().getApplicationContext(),
                        //binding.firstNameInput.getText().toString() + "\n" +
                        binding.getViewModel().getName() + "\n" +
                        binding.lastNameInput.getText().toString() + "\n" +
                        binding.dateInput.getText().toString() + "\n" +
                        binding.placeInput.getText().toString() + "\n"
                        , Toast.LENGTH_SHORT ).show();
                /*
                 NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                 */
            }
        });
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {

            mListener.onFragment1Interaction(uri);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment1InteractionListener) {
            mListener = (OnFragment1InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragment1InteractionListener {
        // TODO: Update argument type and name
        void onFragment1Interaction(Uri uri);
    }

}