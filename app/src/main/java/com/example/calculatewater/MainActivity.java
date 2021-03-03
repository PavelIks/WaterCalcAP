package com.example.calculatewater;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Switch switchSex;
    TextView txtWeight, txtGrow, txtGender, txtFemale;
    Button btnNext;
    UserClass user;

    private final static String FILE_NAME = "test.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchSex = findViewById(R.id.Switch_Gender_ID);
        txtWeight = findViewById(R.id.txt_weight);
        txtGrow = findViewById(R.id.txt_grow);
        btnNext = findViewById(R.id.Button_Next_ID);
        txtGender = findViewById(R.id.textView3);

        int color_male = getResources().getColor(R.color.male_color, null);
        int color_female = getResources().getColor(R.color.female_color, null);
        int wh_color = getResources().getColor(R.color.white, null);
        txtGender.setTextColor(color_male);

        switchSex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) //Line A
            {
                if (switchSex.isChecked())
                {
                    txtGender.setTextColor(color_female);
                    txtGender.setText("Женский");
                }
                else 
                    {
                        txtGender.setTextColor(color_male);
                        txtGender.setText("Мужской");
                    } 
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void nextMethod(View v) {
        String getsex;

        if (switchSex.isChecked()) {
            getsex = "Женщина";
        } else {
            getsex = "Мужчина";
        }
        if (txtWeight.getText().length() != 0 && txtGrow.getText().length() != 0 && Integer.parseInt(txtGrow.getText().toString()) < 200 && Integer.parseInt(txtWeight.getText().toString()) < 300) {
            {
                user = new UserClass(getsex, Integer.parseInt(txtWeight.getText().toString()), Integer.parseInt(txtGrow.getText().toString()));
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(("Рост:" + txtGrow.getText().toString() + ";\nВес : " + txtWeight.getText().toString() + ";\nПол : " + getsex).toString().getBytes());
                    Toast.makeText(getApplicationContext(), "SAVED!", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }

                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent = new Intent(this, UserActivity.class);
                String user_weight = txtWeight.getText().toString();
                String user_grow = txtGrow.getText().toString();
                String user_sex = getsex;
                intent.putExtra("user_weight", user_weight);
                intent.putExtra("user_grow", user_grow);
                intent.putExtra("user_sex", user_sex);
                startActivity(intent);
            }
        }
        else 
            { 
                Toast.makeText(this, "Введены не коректные данные! ", Toast.LENGTH_SHORT).show();
                txtWeight.setText("");
                txtGrow.setText(""); 
            } 
    }
}