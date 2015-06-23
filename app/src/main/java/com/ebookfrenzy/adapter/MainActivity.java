package com.ebookfrenzy.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ListView list;
    String[] memeTitles;
    String[] memeDescriptions;
    int[] images = {R.drawable.burj_dubai_553368, R.drawable.dubai_553378, R.drawable.dubai_553380, R.drawable.dubai_august_2011_553385, R.drawable.dubai_august_2011_553388, R.drawable.dubai_creek_553384, R.drawable.dubai_marina_553387, R.drawable.emirates_towers_553377, R.drawable.emirates_towers_dubai_553370, R.drawable.kempinski_palm_jumeirah_dubai_553386};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        memeTitles = res.getStringArray(R.array.titles);
        memeDescriptions = res.getStringArray(R.array.description);

        list = (ListView) findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, memeTitles, images, memeDescriptions);
        list.setAdapter(adapter);

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        int[] images;
        String[] titleArray;
        String[] descriptionArray;

        MyAdapter(Context c, String[] titles, int[] imgs, String[] desc) {
            super(c, R.layout.single_row, R.id.textView, titles);
            this.context = c;
            this.images = imgs;
            this.titleArray = titles;
            this.descriptionArray = desc;
        }

        class myViewHolder {
            ImageView myImage;
            TextView myTitle;
            TextView myDescription;

            myViewHolder(View v) {
                myImage = (ImageView) v.findViewById(R.id.imageView);
                myTitle = (TextView) v.findViewById(R.id.textView);
                myDescription = (TextView) v.findViewById(R.id.textView2);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            myViewHolder holder = null;
            if (row == null) {


                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_row, parent, false);
                holder = new myViewHolder(row);
                row.setTag(holder);

                holder.myTitle.setText("this is a new line \nnot recycled ");

                Log.d("hello", "Creating a new row");
            } else {
                holder = (myViewHolder) row.getTag();

                holder.myTitle.setText(titleArray[position]);

                Log.d("hello", "Recycling stuff");
            }

            if ((position + 1)%3 == 0) {
                row.setBackgroundColor(Color.BLUE);
            }
            else if ((position + 1)%3 == 1) {
                row.setBackgroundColor(Color.GREEN);
            }
            else if ((position + 1)%3 == 2) {
                row.setBackgroundColor(Color.RED);
            }

            holder.myImage.setImageResource(images[position]);

            holder.myDescription.setText(descriptionArray[position]);


            return row;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
