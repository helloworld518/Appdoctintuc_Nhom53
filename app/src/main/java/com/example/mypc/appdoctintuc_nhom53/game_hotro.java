package com.example.mypc.appdoctintuc_nhom53;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class game_hotro extends AppCompatActivity {
    int soDiem=100;
    TextView txtDiem;
    ImageButton ibtnPlay, ibtnRestart;
    CheckBox cb1, cb2, cb3;
    SeekBar skOne, skTwo, skThree;
    LinearLayout lnQuayveHotro_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_hotro);

        Toast.makeText(this, "Bạn hãy đặt cược trước khi chơi", Toast.LENGTH_SHORT).show();
        AnhXa();
        txtDiem.setText(soDiem + "");

        //khong cho tuong tac voi seekbar
        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skThree.setEnabled(false);

        ibtnRestart.setVisibility(View.INVISIBLE);
        // quay về màn hình hỗ trợ
        lnQuayveHotro_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(game_hotro.this,HotroActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_enter1, R.anim.animation_exit1);
            }
        });

        final CountDownTimer countDownTimer=new CountDownTimer(30000, 450) {
            @Override
            public void onTick(long millisUntilFinished) {
                Random random=new Random();
                int number=35;
                int one=random.nextInt(number);
                int two=random.nextInt(number);
                int three=random.nextInt(number);
                skOne.setProgress(skOne.getProgress()+one);
                skTwo.setProgress(skOne.getProgress()+two);
                skThree.setProgress(skOne.getProgress()+three);

                //kiem tra SeeBar win
                if (skOne.getProgress()>=skOne.getMax()){
                    this.cancel();
                    Toast.makeText(game_hotro.this, "hô hô! con thứ nhất win rồi", Toast.LENGTH_SHORT).show();
                    ibtnRestart.setVisibility(View.VISIBLE);

                    //kiem tra dat cuoc
                    if (cb1.isChecked()){
                        soDiem +=10;
                        Toast.makeText(game_hotro.this, "bạn đoán rất chính xác, 10 điểm cho bạn", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -=5;
                        Toast.makeText(game_hotro.this, "Tiếc quá, bạn mất 5 điểm rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");

                    EnableCheckBox();
                }
                if (skTwo.getProgress()>=skTwo.getMax()){
                    this.cancel();
                    Toast.makeText(game_hotro.this, "say oh Yeah! con thứ 2 win nha", Toast.LENGTH_SHORT).show();
                    ibtnRestart.setVisibility(View.VISIBLE);

                    //kiem tra dat cuoc
                    if (cb2.isChecked()){
                        soDiem +=10;
                        Toast.makeText(game_hotro.this, "Bạn đoán chỉ có chuẩn! 10 điểm cho bạn", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -=5;
                        Toast.makeText(game_hotro.this, "sai béc, mất 5 điểm", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }
                if (skThree.getProgress()>=skThree.getMax()){
                    this.cancel();
                    Toast.makeText(game_hotro.this, "con thứ ba win nhé ^.^", Toast.LENGTH_SHORT).show();
                    ibtnRestart.setVisibility(View.VISIBLE);

                    //kiem tra dat cuoc
                    if (cb3.isChecked()){
                        soDiem +=10;
                        Toast.makeText(game_hotro.this, "Chính xác! bạn được 10 điểm", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -=5;
                        Toast.makeText(game_hotro.this, "Rất tiếc! mất 5 điểm rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckBox();
                }
            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiem tra xem da click vao checkbox chua?
                if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked()) {
                    skOne.setProgress(0);
                    skTwo.setProgress(0);
                    skThree.setProgress(0);
                    ibtnPlay.setVisibility(View.INVISIBLE);
                    ibtnRestart.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                    DisableCheckBox();
                }else {
                    Toast.makeText(game_hotro.this, "bạn phải cược trước khi chơi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ibtnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiem tra xem da click vao checkbox chua?
                if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked()) {
                    skOne.setProgress(0);
                    skTwo.setProgress(0);
                    skThree.setProgress(0);
                    ibtnPlay.setVisibility(View.INVISIBLE);
                    ibtnRestart.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                    DisableCheckBox();
                }else {
                    Toast.makeText(game_hotro.this, "cược trước khi chơi nha !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //check CheckBox
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                }
            }
        });
    }

    private void EnableCheckBox(){
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }

    private void DisableCheckBox(){
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }

    private void EnableImageButton(){
        ibtnPlay.setEnabled(true);
        ibtnRestart.setEnabled(true);
    }
    private void DisableImageButton(){
        ibtnPlay.setEnabled(false);
        ibtnRestart.setEnabled(false);
    }

    private void AnhXa(){
        txtDiem = findViewById(R.id.txtDiem);
        ibtnPlay= findViewById(R.id.buttonPlay);
        ibtnRestart = findViewById(R.id.buttonRestart);
        cb1     = findViewById(R.id.checkboxOne);
        cb2     = findViewById(R.id.checkboxTwo);
        cb3     = findViewById(R.id.checkboxThree);
        skOne   = findViewById(R.id.seekbarOne);
        skTwo   = findViewById(R.id.seekbarTwo);
        skThree = findViewById(R.id.seekbarThree);
        lnQuayveHotro_game = findViewById(R.id.lnBacktoHotro_game);

    }


}
