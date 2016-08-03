package catdogapp.alan.com.playsoundtrack;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.game_field);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                int duration = mp.getDuration()/1000;

                Toast.makeText(getApplicationContext(), "Duration: " + duration + " seconds", Toast.LENGTH_LONG).show();
            }
        });

        playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaPlayer.isPlaying()){
                    pauseMusic();
                } else {
                    startMusic();
                }
            }
        });

    }

    public void pauseMusic(){

        if(mediaPlayer != null){
            mediaPlayer.pause();
            playButton.setText(R.string.play);
        }
    }

    public void startMusic(){

        if(mediaPlayer != null){
            mediaPlayer.start();
            playButton.setText(R.string.pause);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
