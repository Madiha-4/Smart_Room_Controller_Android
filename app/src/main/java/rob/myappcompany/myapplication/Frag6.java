package rob.myappcompany.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class Frag6 extends Fragment {
    SwitchCompat switchButton;
    ImageView imageViewLight;
    Button button_1;
    Button button_2;
    Button button_3;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag6_layout, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        switchButton = (SwitchCompat) Objects.requireNonNull(getView()).findViewById(R.id.switchButton);
        imageViewLight = (ImageView) Objects.requireNonNull(getView()).findViewById(R.id.imageView);
        button_1 = (Button) Objects.requireNonNull(getView()).findViewById(R.id.button_1);
        button_2 = (Button) Objects.requireNonNull(getView()).findViewById(R.id.button_2);
        button_3 = (Button) Objects.requireNonNull(getView()).findViewById(R.id.button_3);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    imageViewLight.setImageResource(R.drawable.fan_on);

                    button_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(),"Speed is Low",Toast.LENGTH_LONG).show();
                        }
                    });
                    button_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(),"Speed is Medium",Toast.LENGTH_LONG).show();
                        }
                    });
                    button_3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(),"Speed is High",Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    imageViewLight.setImageResource(R.drawable.fan_off);
                }
            }
        });

    }
}
