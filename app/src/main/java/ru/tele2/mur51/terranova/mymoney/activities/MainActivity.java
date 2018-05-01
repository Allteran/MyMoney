package ru.tele2.mur51.terranova.mymoney.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import io.realm.Realm;
import ru.tele2.mur51.terranova.mymoney.R;
import ru.tele2.mur51.terranova.mymoney.entities.Dealer;
import ru.tele2.mur51.terranova.mymoney.entities.DealerBonus;
import ru.tele2.mur51.terranova.mymoney.entities.Employee;
import ru.tele2.mur51.terranova.mymoney.entities.PointOfSales;
import ru.tele2.mur51.terranova.mymoney.fragments.DealerBonusFragment;
import ru.tele2.mur51.terranova.mymoney.fragments.MoneyChartHolderFragment;
import ru.tele2.mur51.terranova.mymoney.fragments.SalesPlanFragment;
import ru.tele2.mur51.terranova.mymoney.fragments.ScheduleFragment;
import ru.tele2.mur51.terranova.mymoney.helpers.RealmHelper;
import ru.tele2.mur51.terranova.mymoney.utilities.Const;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private MoneyChartHolderFragment mMoneyFragment;
    private DealerBonusFragment mDealerBonusFragment;
    private SalesPlanFragment mSalesPlanFragment;
    private ScheduleFragment mScheduleFragment;

    private Employee mSeller;
    private Dealer mDealer;
    private PointOfSales mPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSalesPlanFragment = SalesPlanFragment.newInstance(0, null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Get current user, dealer and POS from database with arguments
        Realm realm = Realm.getDefaultInstance();
        Intent intent = getIntent();
        RealmHelper realmHelper = new RealmHelper();
        if (intent != null) {
            mDealer = realmHelper.getDealer(realm);
            mSeller = realmHelper.getSeller(realm, intent.getStringExtra(Const.EXTRA_USER_LOGIN));
            int posId = intent.getIntExtra(Const.EXTRA_SELECTED_POS, 0);

            mMoneyFragment = MoneyChartHolderFragment.newInstance(null, posId, mSeller.getLogin());
            mDealerBonusFragment = DealerBonusFragment.newInstance("2018", mSeller.getLogin());
            mScheduleFragment = ScheduleFragment.newInstance("2018", posId);
            mPos = realmHelper.getPos(realm, posId);
        }
        TextView userName = (TextView) findViewById(R.id.seller_fullname_textview); //user
        TextView posData = (TextView) findViewById(R.id.pos_data_textview);
        TextView dealerName = (TextView) findViewById(R.id.dealer_name_textview);

        String userFullName = mSeller.getSecondName() + " " + mSeller.getFirstName();
        String posName = String.valueOf(mPos.getId()) + ", " + mPos.getStreet() + ", д. " +
                String.valueOf(mPos.getBuildingNumber()) + ", " + mPos.getCity();
        userName.setText(userFullName);
        posData.setText(posName);
        dealerName.setText(mDealer.getName());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_money:
                fTransaction.replace(R.id.main_data_container, mMoneyFragment);
                break;
            case R.id.nav_dealerbonus:
                fTransaction.replace(R.id.main_data_container, mDealerBonusFragment);
                break;
            case R.id.nav_sales_plan:
                fTransaction.replace(R.id.main_data_container, mSalesPlanFragment);
                break;
            case R.id.nav_schedule:
                fTransaction.replace(R.id.main_data_container, mScheduleFragment);
                break;
            case R.id.nav_instructions:
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_feedback:
                break;
            case R.id.nav_about:
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent logoutIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                finish();
                return true;
        }
        fTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
