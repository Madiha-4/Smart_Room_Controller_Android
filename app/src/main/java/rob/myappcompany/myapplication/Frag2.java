package rob.myappcompany.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class Frag2 extends Fragment {
    SwitchCompat switchButton;
    ImageView imageViewLight;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag2_layout, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        switchButton = (SwitchCompat) Objects.requireNonNull(getView()).findViewById(R.id.switchButton);
        imageViewLight = (ImageView) Objects.requireNonNull(getView()).findViewById(R.id.imageView);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    imageViewLight.setImageResource(R.drawable.light_on);
                } else {
                    imageViewLight.setImageResource(R.drawable.light_off);
                }
            }
        });
    }
}
