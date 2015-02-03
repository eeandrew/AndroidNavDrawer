package eeandrew.drawersamplenew;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    private DrawerLayout mDrawerLayout;

    // ListView represents Navigation Drawer
    private ListView mDrawerList;

    // ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
    private ActionBarDrawerToggle mDrawerToggle;

    ScrimInsetsFrameLayout mDrawer;

    // Title of the action bar
    private String mTitle = "";

    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);
        toolBar = (Toolbar)findViewById(R.id.my_awesome_toolbar);

        setSupportActionBar(toolBar);
        // Getting reference to the DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.status_bar_bg_color));

        mDrawer = (ScrimInsetsFrameLayout) findViewById(R.id.my_drawer);

        mDrawerList = (ListView) findViewById(R.id.drawer_list);

        // Getting reference to the ActionBarDrawerToggle
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {

            /** Called when drawer is closed */
            public void onDrawerClosed(View view) {
                //getActionBar().setTitle(mTitle);
                //invalidateOptionsMenu();

            }

            /** Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
            }

        };

        // Setting DrawerToggle on DrawerLayout
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Creating an ArrayAdapter to add items to the listview mDrawerList
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                R.layout.drawer_list_item, getResources().getStringArray(R.array.menus));

//        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(),getSettingItems(),R.layout.setting_list_item,
//                new String[]{"itemName","itemIcon"},
//                new int[]{R.id.setting_item_name,R.id.setting_item_icon});


        // Setting the adapter on mDrawerList
        mDrawerList.setAdapter(adapter);

        // Enabling Home button
        //getActionBar().setHomeButtonEnabled(true);

        // Enabling Up navigation
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        // Setting item click listener for the listview mDrawerList
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // Getting an array of rivers
                String[] menuItems = getResources().getStringArray(R.array.menus);

                // Currently selected river
                mTitle = menuItems[position];

                // Creating a fragment object
                WebViewFragment rFragment = new WebViewFragment();

                // Passing selected item information to fragment
                Bundle data = new Bundle();
                data.putInt("position", position);
                data.putString("url", getUrl(position));
                rFragment.setArguments(data);


                // Getting reference to the FragmentManager
                FragmentManager fragmentManager = getFragmentManager();

                // Creating a fragment transaction
                FragmentTransaction ft = fragmentManager.beginTransaction();

                // Adding a fragment to the fragment transaction
                ft.replace(R.id.content_frame, rFragment);

                // Committing the transaction
                ft.commit();
                // Closing the drawer
                mDrawerLayout.closeDrawer(mDrawer);

            }
        });
    }

    private List<Map<String,Object>> getSettingItems(){
        List<Map<String,Object>> settingItems = new ArrayList<>();
        Map<String,Object> item = new HashMap<String,Object>();
        item.put("itemName","Test");
        item.put("itemIcon",R.drawable.ic_drawer);
        settingItems.add(item);

        item = new HashMap<String,Object>();
        item.put("itemName","Test");
        item.put("itemIcon",R.drawable.ic_drawer);
        settingItems.add(item);


        return settingItems;
    }

    protected String getUrl(int position) {
        switch (position) {
            case 0:
                return "http://javatechig.com";
            case 1:
                return "http://javatechig.com/category/android/";
            case 2:
                return "http://javatechig.com/category/blackberry/";
            case 3:
                return "http://javatechig.com/category/j2me/";
            case 4:
                return "http://javatechig.com/category/sencha-touch/";
            case 5:
                return "http://javatechig.com/category/phonegap/";
            case 6:
                return "http://javatechig.com/category/java/";
            default:
                return "http://javatechig.com";
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
