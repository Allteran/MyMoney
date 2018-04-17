package ru.tele2.mur51.terranova.mymoney.helpers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import io.realm.RealmList;
import ru.tele2.mur51.terranova.mymoney.entities.Dealer;
import ru.tele2.mur51.terranova.mymoney.entities.PointOfSales;

public class FirebaseHelper {

    public Dealer getDealer(FirebaseFirestore database, final RealmList<PointOfSales> posList) {
        final Dealer dealer = new Dealer();
        DocumentReference dealerReference = database.collection("dealers").document("novayazemlia");
        dealerReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult();
                    long dlrId = snapshot.getLong("id");
                    int intId = (int) dlrId;
                    dealer.setId(intId);
                    dealer.setName(snapshot.getString("name"));
                    dealer.setPosList(posList);
                }
            }
        });
        return dealer;
    }

//    public Employee getSeller(FirebaseFirestore database, final String login ) {
//        final Employee seller = new Employee();
//        CollectionReference sellerReference = database.collection("dealers").document("novayazemlia")
//                .collection("sellers");
//        Query sellerQuery = sellerReference.whereEqualTo("login", login);
//        sellerQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    QuerySnapshot snapshot = task.getResult();
//                    snapshot
//                }
//            }
//        })
//
//        return seller;
//    }
}
