package com.example.jon.echoes;

        import android.content.Intent;
        import android.media.AudioManager;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.os.Handler;
        import android.app.Activity;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.View.OnTouchListener;
        import android.widget.Button;
        import android.widget.SeekBar;

public class MusicPlayerActivity extends Activity implements OnClickListener {
    SeekBar ss;
    SeekBar sb;
    Button b;
    MediaPlayer mp;
    AudioManager am;
    Handler h1=new Handler();
    int i=0;
    Button echo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.all_time_low_dear_maria_count_me_in);
        b=(Button)findViewById(R.id.bb);
        ss =(SeekBar)findViewById(R.id.ss);
        ss.setMax(mp.getDuration());
        if (savedInstanceState != null) {
            //TODO start from previous point
        }
        b.setOnClickListener(this);
        ss.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar s = (SeekBar) v;
                mp.seekTo(s.getProgress());
                return false;
            }
        });
        echo = (Button) findViewById(R.id.echo);
        View.OnClickListener handler = new View.OnClickListener() {

            public void onClick(View v) {

                if (v == echo) {
                    Intent intentMain = new Intent(MusicPlayerActivity.this,
                            MapsActivity.class);
                    MusicPlayerActivity.this.startActivity(intentMain);
                    mp.pause();
                }
            }
        };
        echo.setOnClickListener(handler);
    }

    @Override
    public void onClick(View v) {
        if (i==0)
        {
            mp.start();
            add();
            b.setText("pause");
            i++;
        }
        else if(i==1)
        {
            mp.pause();
            b.setText("Resume");
            i++;
        }
        else if(i==2)
        {
            mp.start();
            add();
            b.setText("Pause");
            i=1;
        }
    }
    public void add()
    {
        ss.setProgress(mp.getCurrentPosition());
        if (ss.getMax() == mp.getCurrentPosition())
        {
            b.setText("Play");
            mp.seekTo(0);
            ss.setProgress(mp.getCurrentPosition());
            i = 0;
        }
        Runnable r1=new Runnable() {
            @Override
            public void run() {
                add();
            }
        };
        h1.postDelayed(r1, 4000);


    }



}