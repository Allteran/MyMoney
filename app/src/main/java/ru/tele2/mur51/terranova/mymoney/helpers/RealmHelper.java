package ru.tele2.mur51.terranova.mymoney.helpers;

import android.support.annotation.NonNull;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import ru.tele2.mur51.terranova.mymoney.entities.Dealer;
import ru.tele2.mur51.terranova.mymoney.entities.DealerBonus;
import ru.tele2.mur51.terranova.mymoney.entities.Employee;
import ru.tele2.mur51.terranova.mymoney.entities.PointOfSales;
import ru.tele2.mur51.terranova.mymoney.entities.Salary;
import ru.tele2.mur51.terranova.mymoney.entities.SalesPlan;

/**
 * Created by Allteran on 15.02.2018.
 */

public class RealmHelper {

    public boolean addDealer(final Realm realm, final Dealer dealer) {
        final boolean[] addedWithSucc = {false};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm innerRealm) {
                Dealer realmDealer = innerRealm.createObject(Dealer.class);
                realmDealer.setId(dealer.getId());
                realmDealer.setName(dealer.getName());
                /**
                 * If there any complicated object in object you want to add to realm DB
                 * you should expand inner object into simple types and add each of em
                 */
                RealmList<PointOfSales> realmPosList = new RealmList<>();
                for (int i = 0; i < dealer.getPosList().size(); i++) {
                    PointOfSales realmPos = innerRealm.createObject(PointOfSales.class);
                    realmPos.setId(dealer.getPosList().get(i).getId());
                    realmPos.setRegion(dealer.getPosList().get(i).getRegion());
                    realmPos.setCity(dealer.getPosList().get(i).getCity());
                    realmPos.setStreet(dealer.getPosList().get(i).getStreet());
                    realmPos.setBuildingNumber(dealer.getPosList().get(i).getBuildingNumber());
                    realmPosList.add(realmPos);
                }

                realmDealer.setPosList(realmPosList);
                /**
                 * Same trick with List of Employee class
                 */
                RealmList<Employee> realmSellersList = new RealmList<>();
                for (int i = 0; i < dealer.getSellersList().size(); i++) {
                    Employee realmSeller = innerRealm.createObject(Employee.class);
                    realmSeller.setLogin(dealer.getSellersList().get(i).getLogin());
                    realmSeller.setPassword(dealer.getSellersList().get(i).getPassword());
                    realmSeller.setFirstName(dealer.getSellersList().get(i).getFirstName());
                    realmSeller.setSecondName(dealer.getSellersList().get(i).getSecondName());
                    realmSellersList.add(realmSeller);
                }
                realmDealer.setSellersList(realmSellersList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                addedWithSucc[0] = true;
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.getStackTrace();
                addedWithSucc[0] = false;
            }
        });
        return addedWithSucc[0];
    }

    public boolean addSalary(final Realm realm, final Salary salary) {
        final boolean[] success = {false};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm innerRealm) {
                Salary realmSalary = innerRealm.createObject(Salary.class);
                realmSalary.setPointOfSales(salary.getPointOfSales());
                realmSalary.setReportDate(salary.getReportDate());
                realmSalary.setSeller(salary.getSeller());
                realmSalary.setBonusPay(salary.getBonusPay());
                realmSalary.setEquipPay(salary.getEquipPay());
                realmSalary.setKpiPay(salary.getKpiPay());
                realmSalary.setRatePay(salary.getRatePay());
                realmSalary.setServicesPay(salary.getServicesPay());
                realmSalary.setSimPay(salary.getSimPay());
            }


        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                success[0] = true;
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                success[0] = false;
            }
        });
        return success[0];
    }

    public boolean addDealerBonus(final Realm realm, final List<DealerBonus> dealerBonusList) {
        final boolean[] success = {false};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm innerRealm) {
                List<DealerBonus> realmDbList = new RealmList<>();
                for (int i = 0; i < dealerBonusList.size(); i++) {
                    DealerBonus realmBonus = innerRealm.createObject(DealerBonus.class);
                    realmBonus.setSeller(dealerBonusList.get(i).getSeller());
                    realmBonus.setAmount(dealerBonusList.get(i).getAmount());
                    realmBonus.setPaidMonth(dealerBonusList.get(i).getPaidMonth());
                    realmBonus.setPaidYear(dealerBonusList.get(i).getPaidYear());
                    realmBonus.setPaid(dealerBonusList.get(i).isPaid());

                    realmDbList.add(realmBonus);
                }
            }
        }, new Realm.Transaction.OnSuccess() {

            @Override
            public void onSuccess() {
                success[0] = true;
            }
        }, new Realm.Transaction.OnError() {

            @Override
            public void onError(@NonNull Throwable error) {
                error.getStackTrace();
                success[0] = false;
            }
        });
        return success[0];
    }

    public boolean addSalesPlan(Realm realm, final SalesPlan plan) {
        final boolean[] success = {false};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm innerRealm) {
                SalesPlan realmPlan = innerRealm.createObject(SalesPlan.class);

                realmPlan.setAccessFact(plan.getAccessFact());
                realmPlan.setAccessPlan(plan.getAccessPlan());
                realmPlan.setAoFact(plan.getAoFact());
                realmPlan.setAoPlan(plan.getAoPlan());
                realmPlan.setGiFact(plan.getGiFact());
                realmPlan.setGiPlan(plan.getGiPlan());
                realmPlan.setGsmFact(plan.getGsmFact());
                realmPlan.setGsmPlan(plan.getGsmPlan());
                realmPlan.setInsuranceFact(plan.getInsuranceFact());
                realmPlan.setInsurancePlan(plan.getInsurancePlan());
                realmPlan.setMnpFact(plan.getMnpFact());
                realmPlan.setMnpPlan(plan.getMnpPlan());
                realmPlan.setServicesFact(plan.getServicesFact());
                realmPlan.setServicesPlan(plan.getServicesPlan());
                realmPlan.setUpSaleFact(plan.getUpSaleFact());
                realmPlan.setUpSalePlan(plan.getUpSalePlan());
                realmPlan.setYaddFact(plan.getYaddFact());
                realmPlan.setYaddPlan(plan.getYaddPlan());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                success[0] = true;
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                success[0] = false;
            }
        });
        return success[0];
    }

    public SalesPlan getSalesPlan(Realm realm, int posId, String date) {
        realm.beginTransaction();
        SalesPlan salesPlan = realm.copyFromRealm(realm.where(SalesPlan.class)
                .equalTo("posId", posId)
                .and()
                .equalTo("date", date)
                .findFirst());
        realm.commitTransaction();
        return salesPlan;
    }

    public List<DealerBonus> getDealerBonus(Realm realm, String seller) {
        realm.beginTransaction();
        List<DealerBonus> dealerBonusList = realm.copyFromRealm(realm.where(DealerBonus.class)
                .equalTo("seller", seller)
                .findAll());
        realm.commitTransaction();
        return dealerBonusList;
    }

    public Salary getSalary(Realm realm, int posId, String seller) {
        realm.beginTransaction();
        Salary salary = realm.copyFromRealm(realm.where(Salary.class)
                .equalTo("pointOfSales", posId)
                .and()
                .equalTo("seller", seller)
                .findFirst());
        realm.commitTransaction();
        return salary;
    }

    public Dealer getDealer(Realm realm, int id) {
        realm.beginTransaction();
        Dealer dealer = realm.copyFromRealm(realm.where(Dealer.class)
                .equalTo("id", id)
                .findFirst());
        realm.commitTransaction();
        return dealer;
    }

    public Employee getSeller(Realm realm, String login) {
        realm.beginTransaction();
        Employee seller = realm.copyFromRealm(realm.where(Employee.class)
                .equalTo("login", login)
                .findFirst());
        realm.commitTransaction();
        return seller;
    }

    public PointOfSales getPos(Realm realm, int id) {
        realm.beginTransaction();
        PointOfSales pos = realm.copyFromRealm(realm.where(PointOfSales.class)
                .equalTo("id", id)
                .findFirst());
        realm.commitTransaction();
        return pos;
    }

}
