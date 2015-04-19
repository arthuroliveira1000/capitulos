package br.com.livroandroid.helloviews;

import android.content.Intent;
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
                "CustomCardView",
                "CardFrame",
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
            String item = parent.getAdapter().getItem(position).toString();
            if("ViewPager".equals(item)) {
                startActivity(new Intent(this,HelloViewPagerActivity.class));
            }
            // Envia a mensagem com o texto do item selecinoado
            wearUtil.sendMessage("/hello", item.getBytes());
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
