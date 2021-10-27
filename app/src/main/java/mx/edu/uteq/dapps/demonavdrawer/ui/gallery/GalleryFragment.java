package mx.edu.uteq.dapps.demonavdrawer.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import mx.edu.uteq.dapps.demonavdrawer.R;
import mx.edu.uteq.dapps.demonavdrawer.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {


private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {


    binding = FragmentGalleryBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        textView.setText("Aqui en Gallery");

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}