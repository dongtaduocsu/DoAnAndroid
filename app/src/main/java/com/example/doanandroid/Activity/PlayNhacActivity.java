package com.example.doanandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.Adapter.PlaynhacAdapter;
import com.example.doanandroid.Adapter.ViewPagerPlaylistnhac;
import com.example.doanandroid.Fragment.Fragment_Dia_nhac;
import com.example.doanandroid.Fragment.Fragment_Lyric;
import com.example.doanandroid.Fragment.Fragment_Play_Danh_Sach_Bai_Hat;
import com.example.doanandroid.Model.Baihat;
import com.example.doanandroid.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity implements PlayNhacActivityCallBack{

    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageView imgplay, imgpre, imgnext, imgrepeat, imgrandom;
    ViewPager viewPagerplaynhac;
    public static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistnhac adapternhac;
    Fragment_Dia_nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Bai_Hat fragment_play_danh_sach_bai_hat;
    Fragment_Lyric fragment_lyric;

    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();

    }

    private void eventClick() {



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1)!=null){
                    if (mangbaihat.size() > 0){
                        fragment_dia_nhac.Playnhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);

                    }else {
                        handler.postDelayed(this, 300);
                    }

                }
            }
        }, 500);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(2)!=null){
                    if (mangbaihat.size() > 0){
                        fragment_lyric.ThemLyric(mangbaihat.get(0).getTenbaihat(), mangbaihat.get(0).getCasi(), mangbaihat.get(0).getLoibaihat()+" ");
                        handler2.removeCallbacks(this);

                    }else {
                        handler2.postDelayed(this, 300);
                    }

                }
            }
        }, 500);

        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                    if (fragment_dia_nhac.objectAnimator!=null){
                        fragment_dia_nhac.objectAnimator.pause();
                    }
                }else{
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                    if (fragment_dia_nhac.objectAnimator!=null){
                        fragment_dia_nhac.objectAnimator.resume();
                    }
                }

            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false){
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else {
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false){
                    if (repeat == true) {
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }else {
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;

                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > mangbaihat.size() - 1) {
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        fragment_lyric.ThemLyric(mangbaihat.get(position).getTenbaihat(), mangbaihat.get(position).getCasi(), mangbaihat.get(position).getLoibaihat()+" ");
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0) {
                            position = mangbaihat.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;

                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        fragment_lyric.ThemLyric(mangbaihat.get(position).getTenbaihat(), mangbaihat.get(position).getCasi(), mangbaihat.get(position).getLoibaihat()+" ");
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);

            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatArrayList;
            }
        }
    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgpre = findViewById(R.id.imagebuttonpre);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPagerplaynhac.setOffscreenPageLimit(3);

        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_nhac();
        fragment_play_danh_sach_bai_hat = new Fragment_Play_Danh_Sach_Bai_Hat();
        fragment_lyric = new Fragment_Lyric();
        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_play_danh_sach_bai_hat);
        adapternhac.AddFragment(fragment_dia_nhac);
        adapternhac.AddFragment(fragment_lyric);

        viewPagerplaynhac.setAdapter(adapternhac);
        fragment_play_danh_sach_bai_hat = (Fragment_Play_Danh_Sach_Bai_Hat) adapternhac.getItem(0);
        fragment_dia_nhac = (Fragment_Dia_nhac) adapternhac.getItem(1);
        fragment_lyric = (Fragment_Lyric) adapternhac.getItem(2);





        if (mangbaihat.size() > 0) {
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause);

        }
    }

    @Override
    public void onMsgFromFragAToMain(final Baihat baihat) {
        position = mangbaihat.indexOf(baihat);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }



                    new PlayMp3().execute(baihat.getLinkbaihat());
                    fragment_dia_nhac.Playnhac(baihat.getHinhbaihat());
                    fragment_lyric.ThemLyric(baihat.getTenbaihat(), baihat.getCasi(), baihat.getLoibaihat()+" ");
                    getSupportActionBar().setTitle(baihat.getTenbaihat());

                }

                next = false;
                handler1.removeCallbacks(this);

            }
        }, 1000);
    }


    class PlayMp3 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {

            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }) ;
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (mangbaihat.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (position < (mangbaihat.size())){
                            imgplay.setImageResource(R.drawable.iconpause);
                            position++;
                            if (repeat == true) {
                                if (position == 0) {
                                    position = mangbaihat.size();
                                }
                                position -= 1;

                            }
                            if (checkrandom == true) {
                                Random random = new Random();
                                int index = random.nextInt(mangbaihat.size());
                                if (index == position) {
                                    position = index - 1;
                                }
                                position = index;
                            }
                            if (position > mangbaihat.size() - 1) {
                                position = 0;
                            }
                            new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                            fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                            fragment_lyric.ThemLyric(mangbaihat.get(position).getTenbaihat(), mangbaihat.get(position).getCasi(), mangbaihat.get(position).getLoibaihat()+" ");
                            getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        }
                    }
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
