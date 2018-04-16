package ru.tele2.mur51.terranova.mymoney.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import ru.tele2.mur51.terranova.mymoney.R;
import ru.tele2.mur51.terranova.mymoney.entities.Dealer;
import ru.tele2.mur51.terranova.mymoney.entities.DealerBonus;
import ru.tele2.mur51.terranova.mymoney.entities.Employee;
import ru.tele2.mur51.terranova.mymoney.entities.PointOfSales;
import ru.tele2.mur51.terranova.mymoney.entities.Salary;
import ru.tele2.mur51.terranova.mymoney.entities.SalesPlan;
import ru.tele2.mur51.terranova.mymoney.entities.WorkDay;
import ru.tele2.mur51.terranova.mymoney.helpers.RealmHelper;
import ru.tele2.mur51.terranova.mymoney.utilities.Const;

/**
 * A login screen that offers login via phone number/password.
 */
public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private boolean mSuccLogin;

    private FirebaseAuth mAuth;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private View mPickPosFormView;

    private Dealer mDealer;
    private String mSelectedPos;
    private String mLogin;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            mLogin = currentUser.getEmail();
            showPosPicker(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        Realm.deleteRealm(Realm.getDefaultConfiguration());

        Realm realm = Realm.getDefaultInstance();
        RealmHelper realmHelper = new RealmHelper();
        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mPasswordView = findViewById(R.id.password);
        mPickPosFormView = findViewById(R.id.pos_id_form);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    hideKeyboard(textView);
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                hideKeyboard(view);
                attemptLogin();
            }
        });

        //Set up form for POS ID's picking up
        Spinner mPosSpinner = (Spinner) findViewById(R.id.pos_ids_spinner);

        mDealer = setDummyDealer();
        realmHelper.addDealer(realm, mDealer);
        realmHelper.addSalary(realm, setDummySalary());
        realmHelper.addDealerBonus(realm, setDummyDealerBonus());
        realmHelper.addSalesPlan(realm, setDummySalesPlan());
        realmHelper.addSchedule(realm, setDummySchedule());

        List<String> posList = new ArrayList<>();
        for (int i = 0; i < mDealer.getPosList().size(); i++) {
            posList.add(String.valueOf(mDealer.getPosList().get(i).getId()));
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, posList);
        mPosSpinner.setAdapter(spinnerAdapter);

        mPosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedPos = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button confirmPosButton = (Button) findViewById(R.id.confirm_pos_button);
        confirmPosButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                mainActivityIntent.putExtra(Const.EXTRA_SELECTED_POS, Integer.valueOf(mSelectedPos));
                mainActivityIntent.putExtra(Const.EXTRA_USER_LOGIN, mLogin);
                mainActivityIntent.putExtra(Const.EXTRA_DEALER, mDealer.getId());
                startActivity(mainActivityIntent);
                finish();
            }
        });
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputManager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_phone));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            showProgress(true);
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mLogin = email;
                                showPosPicker(task.isSuccessful());
                            } else {
                                mPasswordView.setError(getString(R.string.error_incorrect_password));
                                mPasswordView.requestFocus();
                            }
                        }
                    }

            );

//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        if (!mSuccLogin) {
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);

            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        }
    }

    /**
     * Shows the UI to pick up the POS
     */

    private void showPosPicker(final boolean show) {
        mProgressView.animate().cancel();
        mProgressView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mPickPosFormView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private List<WorkDay> setDummySchedule() {
        List<WorkDay> workDays = new RealmList<>();
        //First item should be empty entity with only current month cuz it will be replaced with header
        workDays.add(new WorkDay(0, "", "", "", "март"));
        workDays.add(new WorkDay(853307, "Пн", "05.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Вт", "06.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Ср", "07.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Чт", "08.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Пт", "09.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Сб", "10.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Вс", "11.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Пн", "12.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Вт", "13.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Ср", "14.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Чт", "15.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Пт", "16.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Сб", "17.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Вс", "18.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Пн", "19.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Вт", "20.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Ср", "21.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Чт", "22.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Пт", "23.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Сб", "24.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Вс", "25.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Пн", "26.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Вт", "27.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Ср", "28.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Чт", "29.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Пт", "30.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307, "Сб", "31.03.2018", "Прозапас В.А.", "март"));

        return workDays;
    }

    private SalesPlan setDummySalesPlan() {
        SalesPlan plan = new SalesPlan(110, 810, 9, 111,
                1200, 3, 2000, 27000, 15000);

        plan.setGiFact(35);
        plan.setAoFact(690);
        plan.setMnpFact(3);
        plan.setUpSaleFact(84);
        plan.setInsuranceFact(690);
        plan.setYaddFact(2);
        plan.setServicesFact(1100);
        plan.setAccessFact(21000);
        plan.setGsmFact(11240);
        return plan;
    }

    private List<DealerBonus> setDummyDealerBonus() {
        List<DealerBonus> dbList = new ArrayList<>();

        dbList.add(new DealerBonus("79021335276", 2, 2018, 3280, true));
        dbList.add(new DealerBonus("79021335276", 1, 2018, 3050, true));
        dbList.add(new DealerBonus("79021335276", 12, 2017, 4180, true));
        dbList.add(new DealerBonus("79021335276", 11, 2017, 3000, false));
        dbList.add(new DealerBonus("79021335276", 10, 2017, 1050, false));
        dbList.add(new DealerBonus("79021335276", 9, 2017, 2190, true));
        dbList.add(new DealerBonus("79021335276", 8, 2017, 1800, false));

        return dbList;
    }

    private Salary setDummySalary() {
        Salary salary = new Salary("79021335276", 853307, "Январь");

        salary.setRatePay(10000);
        salary.setSimPay(3125);
        salary.setKpiPay(5526);
        salary.setServicesPay(3150);
        salary.setEquipPay(2580);
        salary.setBonusPay(5861);

        return salary;
    }

    private Dealer setDummyDealer() {
        Dealer dummyDealer = new Dealer();
        dummyDealer.setId(20005);
        dummyDealer.setName("ООО Новая Земля");
        RealmList<PointOfSales> posList = new RealmList<>();
        PointOfSales murPost = new PointOfSales(853307, "Мурманская обл.", "г. Мурманск",
                "ул. Книповича", 37);
        PointOfSales olenPost = new PointOfSales(3473, "Мурманская обл.", "г. Оленегорск",
                "пр-кт Ленинградский", 5);
        PointOfSales apatityBredPost = new PointOfSales(933098, "Мурманская обл.", "г. Апатиты",
                "ул. Бредова", 26);
        PointOfSales apatityLenPos = new PointOfSales(3484, "Мурманская обл.", "г. Апатиты",
                "ул. Ленина", 4);
        PointOfSales kirovskPos = new PointOfSales(3478, "Мурманская обл.", "г. Кировск",
                "ул. Олимпийская", 17);
        PointOfSales pzPos = new PointOfSales(28457, "Мурманская обл.", "г. Полярные Зори",
                "ул. Энергетиков", 33);
        PointOfSales kovdorPos = new PointOfSales(927099, "Мурманская обл.", "г. Ковдор",
                "ул. Кирова", 9);
        PointOfSales alakurttiPos = new PointOfSales(927101, "Мурманская обл.", "с. Алакуртти",
                "ул. Содружества", 9);

        posList.add(murPost);
        posList.add(olenPost);
        posList.add(apatityBredPost);
        posList.add(apatityLenPos);
        posList.add(kirovskPos);
        posList.add(pzPos);
        posList.add(kovdorPos);
        posList.add(alakurttiPos);

        dummyDealer.setPosList(posList);

        RealmList<Employee> sellersList = new RealmList<>();
        Employee demidenkoNatalia = new Employee("9211679499", "123456", "Демиденко",
                "Наталья");
        Employee lishenkoDasha = new Employee("9508956482", "123456", "Лищенко",
                "Дарья");
        Employee kolodkoYulia = new Employee("9533060003", "123456", "Колодько",
                "Юлия");
        Employee pariiKsenia = new Employee("9533055511", "123456", "Парий",
                "Ксения");
        Employee prozapasVitalii = new Employee("danteakuma7@gmail.com", "123456", "Прозапас",
                "Виталий");
        Employee kuznetsovaViktoria = new Employee("9537546605", "123456", "Кузнецова",
                "Виктория");
        Employee solovievaNatalia = new Employee("9537577092", "123456", "Сольвёва",
                "Наталья");

        sellersList.add(demidenkoNatalia);
        sellersList.add(lishenkoDasha);
        sellersList.add(kolodkoYulia);
        sellersList.add(pariiKsenia);
        sellersList.add(prozapasVitalii);
        sellersList.add(kuznetsovaViktoria);
        sellersList.add(solovievaNatalia);

        dummyDealer.setSellersList(sellersList);

        return dummyDealer;
    }

}

