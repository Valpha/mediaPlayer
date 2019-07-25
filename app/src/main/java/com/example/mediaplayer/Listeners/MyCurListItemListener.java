package com.example.mediaplayer.Listeners;

import android.view.View;
import android.widget.AdapterView;

import com.example.mediaplayer.AudioPlayer;
import com.example.mediaplayer.R;

public class MyCurListItemListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        AudioPlayer audioPlayer = AudioPlayer.getInstance();

        curadapter.changeSelect(i);
        changeView(i);
        songOrder = i;
        audioPlayer.setMediaPlayerReset();
        musicStatusChange(songs.get(i).getSsrc());
         audioPlayer.setMediaPlayerStart();
        mbtPlaying.setImageDrawable(getResources().getDrawable(R.drawable.pause));
        ii = 1;
        testLoadCover(i);
    }


}
