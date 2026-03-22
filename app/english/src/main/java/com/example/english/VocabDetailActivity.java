package com.example.english;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class VocabDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_detail);


        Intent intent = getIntent();
        Vocab vocab = (Vocab) intent.getSerializableExtra("vocab");


        VocabFragment vocabFragment = new VocabFragment(vocab);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, vocabFragment);
        fragmentTransaction.commit();
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(v -> {
            Intent intent1 = new Intent(VocabDetailActivity.this, MainActivity.class);
            startActivity(intent1);
        });

    }
}

