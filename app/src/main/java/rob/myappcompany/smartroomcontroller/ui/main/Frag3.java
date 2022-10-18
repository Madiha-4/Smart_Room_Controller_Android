package rob.myappcompany.smartroomcontroller.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class Frag3 extends Fragment {
    SwitchCompat switchButton;
    ImageView imageViewLight;
    Button button_1;
    ImageButton voiceInput;
    HttpsHelper req = new HttpsHelper();

    int checkCount = 0;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag3_layout, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        switchButton = (SwitchCompat) requireView().findViewById(R.id.switchButton);
        imageViewLight = (ImageView) requireView().findViewById(R.id.imageView);
        button_1 = (Button) requireView().findViewById(R.id.button_1);

        voiceInput = (ImageButton) view.findViewById(R.id.frag3_mic);

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

        String[] fanModes = {"Off", "Low", "High"};



        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    imageViewLight.setImageResource(R.drawable.fan_on);
                    req.sendReq("https://ifttt.sinric.pro/v1/actions",
                            "969c1c60-bcc1-44a4-9055-ccd5b2e1476c",
                            "62b36acdad95bfbcdf2e8d71",
                            "setPowerState",
                            "On",
                            view
                    );
                    checkCount++;
                    button_1.setText(fanModes[1]);
                }
                else {
                    imageViewLight.setImageResource(R.drawable.fan_off);
                    req.sendReq("https://ifttt.sinric.pro/v1/actions",
                            "969c1c60-bcc1-44a4-9055-ccd5b2e1476c",
                            "62b36acdad95bfbcdf2e8d71",
                            "setPowerState",
                            "Off",
                            view
                    );
                    button_1.setText(fanModes[0]);
                    checkCount = 0;
                }

            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchButton.isChecked())
                {
                    if(checkCount == 0)
                    {
                        checkCount++;
                        req.sendReq("https://ifttt.sinric.pro/v1/actions",
                                "969c1c60-bcc1-44a4-9055-ccd5b2e1476c",
                                "62b36acdad95bfbcdf2e8d71",
                                "setPowerState",
                                "On",
                                view
                        );
                        button_1.setText(fanModes[1]);
                    }
                    else if(checkCount == 1)
                    {
                        req.sendReq("https://ifttt.sinric.pro/v1/actions",
                                "969c1c60-bcc1-44a4-9055-ccd5b2e1476c",
                                "62b36acdad95bfbcdf2e8d71",
                                "setPowerState",
                                "On",
                                view
                        );
                        checkCount++;
                        button_1.setText(fanModes[2]);

                    }
//                    else if(checkCount == 2)
//                    {
//                        req.sendReq("https://ifttt.sinric.pro/v1/actions",
//                                "969c1c60-bcc1-44a4-9055-ccd5b2e1476c",
//                                "62b36acdad95bfbcdf2e8d71",
//                                "setPowerState",
//                                "On",
//                                view
//                        );
//                        checkCount++;
//                        button_1.setText(fanModes[3]);
//                    }
                    else if(checkCount == 2)
                    {
                        req.sendReq("https://ifttt.sinric.pro/v1/actions",
                                "969c1c60-bcc1-44a4-9055-ccd5b2e1476c",
                                "62b36acdad95bfbcdf2e8d71",
                                "setPowerState",
                                "Off",
                                view
                        );
                        checkCount = 0;
                        switchButton.setChecked(false);
                        button_1.setText(fanModes[0]);
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Unknown counter value", Toast.LENGTH_SHORT).show();
                    }
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

            String turnOn = "Turn on the fan";
            String turnOff = "Turn off the fan";
            String speed  = "Increase the speed";

            if(voiceLine.equalsIgnoreCase(turnOn))
            {
                switchButton.setChecked(true);
            }
            else  if (voiceLine.equalsIgnoreCase(speed))
            {
                if (checkCount < 2)
                {
                    switchButton.isChecked();
                    button_1.callOnClick();
                }
                else
                {
                    Toast.makeText(getContext(), "The fan is at max speed already.", Toast.LENGTH_LONG).show();
                }
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
