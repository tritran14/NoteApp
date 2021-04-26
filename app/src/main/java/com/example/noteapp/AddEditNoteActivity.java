package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID="ID";
    public static final String EXTRA_TITLE="TITLE";
    public static final String EXTRA_DESC="DESCRIPTION";
    public static final String EXTRA_PRIORITY="PRIORITY";
    private EditText edtTittle,edtDesc;
    private NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        edtTittle=findViewById(R.id.edt_tittle);
        edtDesc=findViewById(R.id.edt_description);
        numberPicker=findViewById(R.id.np_priority);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent=getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            edtTittle.setText(intent.getStringExtra(EXTRA_TITLE));
            edtDesc.setText(intent.getStringExtra(EXTRA_DESC));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }
        else{
            setTitle("Add Note");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                SaveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void SaveNote() {
        String title=edtTittle.getText().toString().trim();
        String desc=edtDesc.getText().toString().trim();
        int priority=numberPicker.getValue();
        if(TextUtils.isEmpty(title)||TextUtils.isEmpty(desc)){
            Toast.makeText(this, "Insert both", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent=new Intent();
        intent.putExtra(EXTRA_TITLE,title);
        intent.putExtra(EXTRA_DESC,desc);
        intent.putExtra(EXTRA_PRIORITY,priority);

        int id=getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!=-1){
            intent.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,intent);
        finish();
    }
}