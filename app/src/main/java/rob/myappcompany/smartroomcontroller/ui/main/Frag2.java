package rob.myappcompany.smartroomcontroller.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import rob.myappcompany.smartroomcontroller.HttpsHelper;
import rob.myappcompany.smartroomcontroller.R;

import static android.app.Activity.RESULT_OK;

public class Frag2 extends Fragment {
    SwitchCompat switchButton;
    ImageView imageViewLight;
    ImageButton voiceInput;
    HttpsHelper req = new HttpsHelper();

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag2_layout, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        switchButton = (SwitchCompat) requireView().findViewById(R.id.switchButton);
        imageViewLight = (ImageView) requireView().findViewById(R.id.imageView);

        voiceInput = (ImageButton) view.findViewById(R.id.frag2_mic);

        voiceInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now.");

                startActivityForResult(speechIntent, 100);
            }
        }
        );


        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    imageViewLight.setImageResource(R.drawable.light_on);
                    req.sendReq("https://ifttt.sinric.pro/v1/actions",
                            "969c1c60-bcc1-44a4-9055-ccd5b2e1476c",
                            "62b36a710aec232058ee2de4",
                            "setPowerState",
                            "On",
                            view
                    );
                } else {
                    imageViewLight.setImageResource(R.drawable.light_off);
                    req.sendReq("https://ifttt.sinric.pro/v1/actions",
                            "969c1c60-bcc1-44a4-9055-ccd5b2e1476c",
                            "62b36a710aec232058ee2de4",
                            "setPowerState",
                            "Off",
                            view
                    );

                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK)
        {
            ArrayList<String> voiceInput = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String voiceLine = voiceInput.get(0).toString();

            String turnOn = "Turn on the light";
            String turnOff = "Turn off the light";

            if(voiceLine.equalsIgnoreCase(turnOn))
            {
                switchButton.setChecked(true);
            }
            else if (voiceLine.equalsIgnoreCase(turnOff))
            {
                switchButton.setChecked(false);
            }
            else
            {
                Toast.makeText(getContext(), "Incorrect input provided. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
