package osinnowo.com.xlift;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.ButterKnife;
import osinnowo.com.xlift.fragment.ETAFragment;
import osinnowo.com.xlift.fragment.LocationFragment;
import osinnowo.com.xlift.fragment.RideFragment;

public class MainActivity extends AppCompatActivity {

    final Fragment FragmentLocation = new LocationFragment();
    final Fragment FragmentETA = new ETAFragment();
    final Fragment FragmentRide = new RideFragment();
    private Fragment FragmentActive = FragmentLocation;
    final FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.SetDefaultFragment();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        BottomBarTab nearby = bottomBar.getTabWithId(R.id.tab_nearby);
        nearby.setBadgeCount(5);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            Fragment fragment = null;
            @Override
            public void onTabSelected(@IdRes int tabId) {

                switch (tabId) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    case R.id.tab_nearby:
                        fragment = new LocationFragment();
                        break;

                    case R.id.tab_favorites:
                        fragment = new ETAFragment();
                        break;

                    case R.id.tab_ride:
                            fragment = new RideFragment();
                        break;
                }
                if(fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.commit();
                }
            }
        });


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

    private void SetDefaultFragment () {
        Fragment fragment = new LocationFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();
    }
}
