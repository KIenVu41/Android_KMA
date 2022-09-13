package com.kma.bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnStop;
    SurfaceView surfaceView;
    MediaRecorder mediaRecorder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        surfaceView = findViewById(R.id.surfaceview);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRecordingVideo();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doStopRecording();
            }
        });
    }

    public void doRecordingVideo() {
        try {
            if(mediaRecorder == null) {
                mediaRecorder = new MediaRecorder();
            }
            String saveto = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myfile.mp4";
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setVideoSize(640, 480);
            mediaRecorder.setVideoFrameRate(30);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            mediaRecorder.setOutputFile(saveto);
            mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
            mediaRecorder.prepare();
            mediaRecorder.start();
        }catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void showMessage(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    public void doStopRecording() {
        if(mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

            Intent intent = new Intent(Intent.ACTION_VIEW);
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myfile.mp4";
            intent.setDataAndType(Uri.parse(path),"video/*");
            startActivity(intent);

        }
    }
}