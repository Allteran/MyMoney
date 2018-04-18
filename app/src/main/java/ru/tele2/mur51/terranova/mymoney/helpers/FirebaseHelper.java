package ru.tele2.mur51.terranova.mymoney.helpers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import io.realm.RealmList;
import ru.tele2.mur51.terranova.mymoney.entities.Dealer;
import ru.tele2.mur51.terranova.mymoney.entities.Employee;
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

    public Employee getSeller(FirebaseFirestore database, final String login) {
        final Employee seller = new Employee();
        CollectionReference sellerReference = database.collection("dealers").document("novayazemlia")
                .collection("sellers");
        Query sellerQuery = sellerReference.whereEqualTo("login", login);
        sellerQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult().getDocuments().get(0);
                    seller.setFirstName(snapshot.getString("firstName"));
                    seller.setSecondName(snapshot.getString("secondName"));
                    seller.setLogin(snapshot.getString("login"));
                    seller.setDealerId(20000085);
                }
            }
        });
        return seller;
    }

    public RealmList<PointOfSales> getPosList(FirebaseFirestore database) {
        final RealmList<PointOfSales> posList = new RealmList<>();
        CollectionReference posListRef = database.collection("dealers").document("novayazemlia")
                .collection("posList");
        posListRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PointOfSales pos = new PointOfSales();
                                long lngId = document.getLong("id");
                                int posId = (int) lngId;
                                pos.setId(posId);
                                pos.setRegion(document.getString("region"));
                                pos.setCity(document.getString("city"));
                                pos.setStreet(document.getString("street"));
                                long lngBuildingNo = document.getLong("buildingNumber");
                                int buildingNumber = (int) lngBuildingNo;
                                pos.setBuildingNumber(buildingNumber);
                                posList.add(pos);
                            }
                        }
                    }
                });
        return posList;
    }
}
