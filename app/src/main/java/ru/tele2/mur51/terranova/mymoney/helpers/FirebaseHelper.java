package ru.tele2.mur51.terranova.mymoney.helpers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import io.realm.RealmList;
import ru.tele2.mur51.terranova.mymoney.entities.Dealer;
import ru.tele2.mur51.terranova.mymoney.entities.Employee;
import ru.tele2.mur51.terranova.mymoney.entities.PointOfSales;

public class FirebaseHelper {
    private static final String TAG = "FirebaseHelper";

    public Dealer getDealer(FirebaseFirestore database, RealmList<PointOfSales> posList) {
        final Dealer dealer = new Dealer();
        DocumentReference dealerRef = database.collection("novayazemlia").document("tlbmQ3cDr60sltVBtnqt");
        dealerRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Listener had access to database");
                    DocumentSnapshot doc = task.getResult();
                    Integer id = (Integer) doc.get("id");
                    Log.d(TAG, id.toString());

                    dealer.setId(id.intValue());

                    Log.d(TAG, "Dealer name = " + doc.getString("name"));
                    dealer.setName(doc.getString("name"));
                } else {
                    Log.d(TAG, "Task isn't successful, something went wrong");
                }

            }
        });
        dealer.setPosList(posList);
        return dealer;
    }

    public Employee getSeller(FirebaseFirestore database, final String login) {
        final Employee seller = new Employee();
        CollectionReference sellerRef = database.collection("novayazemlia").document("tlbmQ3cDr60sltVBtnqt")
                .collection("users");
        sellerRef.whereEqualTo("login", login)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Listener had access to DB");
                            DocumentSnapshot doc = task.getResult().getDocuments().get(0);
                            Log.d(TAG, "Doc exist? " + String.valueOf(doc.exists()));

                            seller.setLogin(doc.getString("login"));
                            Log.d(TAG, "Login " + doc.getString("login"));
                            seller.setFirstName(doc.getString("firstName"));
                            Log.d(TAG, "First Name " + doc.getString("firstName"));
                            seller.setSecondName(doc.getString("secondName"));
                            Log.d(TAG, "Second Name " + doc.getString("secondName"));
                        }
                    }
                });
        DocumentReference dealerIdRef = database.collection("novayazemlia").document("tlbmQ3cDr60sltVBtnqt");
        dealerIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Getting dealer's id via seller");
                    DocumentSnapshot doc = task.getResult();
                    Integer id = (Integer) doc.get("id");
                    seller.setDealerId(id.intValue());
                    Log.d(TAG, "Dealer's id = " + id.toString());
                }
            }
        });
        return seller;
    }

    public RealmList<PointOfSales> getPosList(FirebaseFirestore database) {
        final RealmList<PointOfSales> posList = new RealmList<>();
        //TODO: realize this sht
        return posList;
    }
}
