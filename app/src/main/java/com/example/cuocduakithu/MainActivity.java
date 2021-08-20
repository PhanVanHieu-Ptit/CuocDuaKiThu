package com.example.cuocduakithu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtDiem;
    CheckBox cbOne,cbTwo,cbThree;
    SeekBar skOne,skTwo,skThree;
    ImageButton ibtnPlay;
    int soDiem=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skThree.setEnabled(false);

        txtDiem.setText(soDiem + "");
        CountDownTimer countDownTimer= new CountDownTimer(10000000,300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int number=10;
                Random random = new Random();
                int one     = random.nextInt(number);
                int two     =random.nextInt(number);
                int three   = random.nextInt(number);

                // kiem tra win One
                if(skOne.getProgress()>=skOne.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "DOG WIN", Toast.LENGTH_SHORT).show();
                    // kiem tra dat cuoc
                    if (cbOne.isChecked()){
                        soDiem+=10;
                        Toast.makeText(MainActivity.this, "Bạn giỏi thật, đoán đúng rồi, cho bạn 10 điểm nè !", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        soDiem-=5;
                        Toast.makeText(MainActivity.this, "Tiếc quá, đoán sai rồi, thua keo này ta bày keo khác!", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }
                // kiem tra win Two
                if(skTwo.getProgress()>=skTwo.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "PIG WIN", Toast.LENGTH_SHORT).show();
                    // kiem tra dat cuoc
                    if (cbTwo.isChecked()){
                        soDiem+=10;
                        Toast.makeText(MainActivity.this, "Ngưỡng mộ bạn quá, đoán đúng rồi, còn chờ gì nữa cho bạn 10  điểm liền!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        soDiem-=5;
                        Toast.makeText(MainActivity.this, "Sai rồi! Thắng làm vua, thua làm lại, mời bạn phục thù !", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");

                    EnableCheckBox();
                }
                // kiem tra win Three
                if(skThree.getProgress()>=skThree.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "WATER SEAL WIN", Toast.LENGTH_SHORT).show();
                    // kiem tra dat cuoc
                    if (cbThree.isChecked()){
                        soDiem+=10;
                        Toast.makeText(MainActivity.this, "Bạn là thiên tài, đoán đúng rồi, tặng 10 điểm cho thiên tài !", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        soDiem-=5;
                        Toast.makeText(MainActivity.this, "Xui quá sai rồi! Còn thở còn gở, mời bạn đoán lại !", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");

                    EnableCheckBox();
                }
                skOne.setProgress(skOne.getProgress() + one);
                skTwo.setProgress(skTwo.getProgress() + two);
                skThree.setProgress(skThree.getProgress() + three);
            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soDiem>0){
                    if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()){
                        skOne.setProgress(0);
                        skTwo.setProgress(0);
                        skThree.setProgress(0);

                        ibtnPlay.setVisibility(View.INVISIBLE);
                        countDownTimer.start();

                        DisableCheckBox();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Vui Long dat cuoc truoc khi choi!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Ban da het diem roi cho ban 10 diem choi tiep ne!", Toast.LENGTH_SHORT).show();
                    soDiem=10;
                    txtDiem.setText(soDiem+"");
                }
            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //bo check 2,3
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // bo check 1,3
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    // bo check 1,2
                    cbOne.setChecked(false);
                    cbTwo.setChecked(false);
                }
            }
        });
    }
    private void EnableCheckBox(){
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }
    private void DisableCheckBox(){
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }
    private void AnhXa(){
        txtDiem             = (TextView) findViewById(R.id.textviewDiemSo);
        ibtnPlay            = (ImageButton) findViewById(R.id.buttonPlay);
        cbOne               = (CheckBox) findViewById(R.id.checkboxOne);
        cbTwo               = (CheckBox) findViewById(R.id.checkboxTwo);
        cbThree             = (CheckBox) findViewById(R.id.checkboxThree);
        skOne               = (SeekBar) findViewById(R.id.seekbarOne);
        skTwo               = (SeekBar) findViewById(R.id.seekbarTwo);
        skThree             =(SeekBar) findViewById(R.id.seekbarThree);

    }
}