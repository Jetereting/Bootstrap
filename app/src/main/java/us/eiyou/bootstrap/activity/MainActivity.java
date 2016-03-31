package us.eiyou.bootstrap.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import us.eiyou.bootstrap.R;
import us.eiyou.bootstrap.model.Probability;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isPay();
    }

    public void isPay() {
        Bmob.initialize(this, "52c499abafd075320161de647bdf5dfa");
        BmobQuery<Probability> bmobQuery1 = new BmobQuery<>();
        bmobQuery1.getObject(getApplicationContext(), "bDry7770", new GetListener<Probability>() {
            @Override
            public void onSuccess(Probability probability) {
                Double d_probability = probability.getProbability();
                Log.e("d_probability", d_probability + "");
                if (d_probability == 1.0) {
                    showDialogWrong();
                }
            }

            @Override
            public void onFailure(int i, String s) {
            }
        });
    }
    public void showDialogWrong() {
        new AlertDialog.Builder(MainActivity.this).setTitle("还差200.").setIcon(
                android.R.drawable.ic_dialog_info).setPositiveButton("马上发工资！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + "17096241774"));
                startActivity(phoneIntent);
            }
        }).setNegativeButton("帮忙催款去", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + "13386175737"));
                startActivity(phoneIntent);
            }
        }).setCancelable(false).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Intent.ACTION_MAIN).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addCategory(Intent.CATEGORY_HOME));
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPay();
    }
}


