package br.com.livroandroid.helloviews;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.com.livroandroid.shared.WearUtil;
import livroandroid.lib.activity.BaseActivity;


public class MainMobileActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "wear";
    private WearUtil wearUtil;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main_mobile);

        wearUtil = new WearUtil(this);

        String[] items = new String[]{
                "CardView",
                "ListView",
                "ViewPager",
                "Sair"};

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (position) {
                case 0:
                    wearUtil.sendMessage("/hello", "CardView".getBytes());
                    break;
                case 1:
                    wearUtil.sendMessage("/hello","ListView".getBytes());
                    break;
                case 2:

                    break;
                default:
                    finish();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        wearUtil.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wearUtil.disconnect();
    }
}
