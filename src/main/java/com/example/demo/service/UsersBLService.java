package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.*;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UsersBLService{
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private PlansDao plansDao;
    @Autowired
    private PhoneBillsDao phoneBillsDao;
    @Autowired
    private MessageBillsDao messageBillsDao;
    @Autowired
    private LocalDataBillsDao localDataBillsDao;
    @Autowired
    private DomesticDataBillsDao domesticDataBillsDao;
    @Autowired
    private FreesDao freesDao;
    @Autowired
    private BillsDao billsDao;

    private int usedMinutes = 0;
    private int usedMessages = 0;
    private int usedLocalDatas = 0;
    private int usedDomesticDatas = 0;

    public UsersBLService(){
    }

    public void addUser(String name, String number){
        Users user = new Users(name, number);
        usersDao.save(user);
    }

    public void addPlan(String name, Integer monthFee, Integer freePhone, Integer freeMessage, Integer freeLocalData, Integer freeDomesticData){
        Plans plan = new Plans(name, monthFee, freePhone, freeMessage, freeLocalData, freeDomesticData);
        plansDao.save(plan);
    }

    public void createFrees(String number){

    }

    public void createOrder(String number, Integer planId, String planName){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Orders orders = new Orders(number, planId, planName, dateString, "生效中" );
        ordersDao.save(orders);

        Plans plan = plansDao.findByName(planName).get(0);
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM");
        String month = formatter1.format(date);

        List<Frees> list = freesDao.findByNumberAndMonth(number, month);

        if (list.size() == 0){
            Frees free = new Frees(number, month);
            free.setFreePhone(plan.getFreePhone());
            free.setFreeMessagee(plan.getFreeMessage());
            free.setFreeLocalData(plan.getFreeLocalData());
            free.setFreeDomesticData(plan.getFreeDomesticData());

            freesDao.save(free);
        }else{
            Frees free = list.get(0);
            free.setFreePhone(free.getFreePhone() + plan.getFreePhone());
            free.setFreeMessagee(free.getFreeMessage() + plan.getFreeMessage());
            free.setFreeLocalData(free.getFreeLocalData() + plan.getFreeLocalData());
            free.setFreeDomesticData(free.getFreeDomesticData() + plan.getFreeDomesticData());

            freesDao.save(free);
        }
    }

    public void cancelOrder(String number, String planName){
        Orders order = ordersDao.findByPlanName(planName).get(0);
        order.setState("已失效");
        ordersDao.save(order);
    }

    public void showOrders(String number){
        List<Orders> orders = ordersDao.findByNumber(number);
        if (orders.size() == 0){
            System.out.println(number + " has no history orders.");
        }
        for (int i = 0; i < orders.size(); i++){
            System.out.print(orders.get(i).getNumber() + " ");
            System.out.print(orders.get(i).getPlanName() + " ");
            System.out.print(orders.get(i).getBuyTime() + " ");
            System.out.println(orders.get(i).getState());
        }
    }

    public void makeCall(String number, String startTime, String endTime){
        //更新free表
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = df.parse(startTime);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            String month = formatter.format(date);

            List<Frees> frees = freesDao.findByNumberAndMonth(number, month);
            if (frees.size() ==0 ){
                List<Orders> orders = ordersDao.findByNumberAndState(number, "生效中");

                if (orders.size() ==0){
                    Frees free = new Frees(number, month, 0, 0, 0, 0);
                    freesDao.save(free);
                }else {
                    Frees free = new Frees(number, month, 0, 0, 0, 0);
                    for (int i = 0; i < orders.size(); i++){
                        String planName = orders.get(i).getPlanName();
                        List<Plans> plans = plansDao.findByName(planName);
                        Plans plan = plans.get(0);
                        free.setFreePhone(free.getFreePhone() + plan.getFreePhone());
                        free.setFreeMessagee(free.getFreeMessage() + plan.getFreeMessage());
                        free.setFreeLocalData(free.getFreeLocalData() + plan.getFreeLocalData());
                        free.setFreeDomesticData(free.getFreeDomesticData() + plan.getFreeDomesticData());
                    }
                    freesDao.save(free);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        PhoneBills phoneBill = new PhoneBills(number, startTime, endTime);
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date d1 = dateFormat.parse(startTime);
            Date d2 = dateFormat.parse(endTime);
            long t1 = d1.getTime();
            long t2 = d2.getTime();
            long millis = t2 - t1;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
            long minutes = 0;
            if (seconds % 60 == 0){
                minutes = seconds / 60;
            }else {
                minutes = seconds / 60 + 1;
            }

            DateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = df.parse(startTime);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            String month = formatter.format(date);
            List<Frees> frees = freesDao.findByNumberAndMonth(number, month);
            Frees free = frees.get(0);
            Integer freeMinutes = free.getFreePhone();

            if (minutes <= freeMinutes){
                phoneBill.setFee(0.0);
                free.setFreePhone((int)(freeMinutes - minutes));
                freesDao.save(free);
            }else if (freeMinutes >= 0){
                double fee = (minutes - freeMinutes) * 0.5;
                phoneBill.setFee(fee);
                free.setFreePhone(0);
                freesDao.save(free);
            }else {
                double fee = minutes * 0.5;
                phoneBill.setFee(fee);
            }

            phoneBillsDao.save(phoneBill);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String number, String sendTime){
        //更新free表
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = df.parse(sendTime);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            String month = formatter.format(date);

            List<Frees> frees = freesDao.findByNumberAndMonth(number, month);
            if (frees.size() ==0 ){
                List<Orders> orders = ordersDao.findByNumberAndState(number, "生效中");

                if (orders.size() ==0){
                    Frees free = new Frees(number, month, 0, 0, 0, 0);
                    freesDao.save(free);
                }else {
                    Frees free = new Frees(number, month, 0, 0, 0, 0);
                    for (int i = 0; i < orders.size(); i++){
                        String planName = orders.get(i).getPlanName();
                        List<Plans> plans = plansDao.findByName(planName);
                        Plans plan = plans.get(0);
                        free.setFreePhone(free.getFreePhone() + plan.getFreePhone());
                        free.setFreeMessagee(free.getFreeMessage() + plan.getFreeMessage());
                        free.setFreeLocalData(free.getFreeLocalData() + plan.getFreeLocalData());
                        free.setFreeDomesticData(free.getFreeDomesticData() + plan.getFreeDomesticData());
                    }
                    freesDao.save(free);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        MessageBills messageBill = new MessageBills(number, sendTime);
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        DateFormat df = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = df.parse(sendTime);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            String month = formatter.format(date);
            List<Frees> frees = freesDao.findByNumberAndMonth(number, month);
            Frees free = frees.get(0);

            int freeMessages = free.getFreeMessage();

            if (freeMessages > 0){
                messageBill.setFee(0.0);
                free.setFreeMessagee((freeMessages - 1));
                freesDao.save(free);
            }else {
                messageBill.setFee(0.1);
                free.setFreeMessagee(0);
                freesDao.save(free);
            }

            messageBillsDao.save(messageBill);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void useLocalData(String number, String startTime, String endTime, Integer data){
        //更新free表
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = df.parse(startTime);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            String month = formatter.format(date);

            List<Frees> frees = freesDao.findByNumberAndMonth(number, month);
            if (frees.size() ==0 ){
                List<Orders> orders = ordersDao.findByNumberAndState(number, "生效中");

                if (orders.size() ==0){
                    Frees free = new Frees(number, month, 0, 0, 0, 0);
                    freesDao.save(free);
                }else {
                    Frees free = new Frees(number, month, 0, 0, 0, 0);
                    for (int i = 0; i < orders.size(); i++){
                        String planName = orders.get(i).getPlanName();
                        List<Plans> plans = plansDao.findByName(planName);
                        Plans plan = plans.get(0);
                        free.setFreePhone(free.getFreePhone() + plan.getFreePhone());
                        free.setFreeMessagee(free.getFreeMessage() + plan.getFreeMessage());
                        free.setFreeLocalData(free.getFreeLocalData() + plan.getFreeLocalData());
                        free.setFreeDomesticData(free.getFreeDomesticData() + plan.getFreeDomesticData());
                    }
                    freesDao.save(free);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        LocalDataBills localDataBill = new LocalDataBills(number, startTime, endTime, data);
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = df.parse(startTime);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            String month = formatter.format(date);
            List<Frees> frees = freesDao.findByNumberAndMonth(number, month);
            Frees free = frees.get(0);
            Integer freeLocalDatas = free.getFreeLocalData();

            if (data <= freeLocalDatas){
                localDataBill.setFee(0.0);
                free.setFreeLocalData((int)(freeLocalDatas - data));
                freesDao.save(free);
            }else if (freeLocalDatas >= 0){
                double fee = (data - freeLocalDatas) * 2.0;
                localDataBill.setFee(fee);
                free.setFreeLocalData(0);
                freesDao.save(free);
            }else {
                double fee = data * 2.0;
                localDataBill.setFee(fee);
            }

            localDataBillsDao.save(localDataBill);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void useDomesticData(String number, String startTime, String endTime, Integer data){
        //更新free表
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = df.parse(startTime);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            String month = formatter.format(date);

            List<Frees> frees = freesDao.findByNumberAndMonth(number, month);
            if (frees.size() ==0 ){
                List<Orders> orders = ordersDao.findByNumberAndState(number, "生效中");

                if (orders.size() ==0){
                    Frees free = new Frees(number, month, 0, 0, 0, 0);
                    freesDao.save(free);
                }else {
                    Frees free = new Frees(number, month, 0, 0, 0, 0);
                    for (int i = 0; i < orders.size(); i++){
                        String planName = orders.get(i).getPlanName();
                        List<Plans> plans = plansDao.findByName(planName);
                        Plans plan = plans.get(0);
                        free.setFreePhone(free.getFreePhone() + plan.getFreePhone());
                        free.setFreeMessagee(free.getFreeMessage() + plan.getFreeMessage());
                        free.setFreeLocalData(free.getFreeLocalData() + plan.getFreeLocalData());
                        free.setFreeDomesticData(free.getFreeDomesticData() + plan.getFreeDomesticData());
                    }
                    freesDao.save(free);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        DomesticDataBills domesticDataBill = new DomesticDataBills(number, startTime, endTime, data);
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = df.parse(startTime);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            String month = formatter.format(date);
            List<Frees> frees = freesDao.findByNumberAndMonth(number, month);
            Frees free = frees.get(0);
            Integer freeDomesticDatas = free.getFreeDomesticData();

            if (data <= freeDomesticDatas){
                domesticDataBill.setFee(0.0);
                free.setFreeDomesticData((int)(freeDomesticDatas - data));
                freesDao.save(free);
            }else if (freeDomesticDatas >= 0){
                double fee = (data - freeDomesticDatas) * 5.0;
                domesticDataBill.setFee(fee);
                free.setFreeDomesticData(0);
                freesDao.save(free);
            }else {
                double fee = data * 2.0;
                domesticDataBill.setFee(fee);
            }

            domesticDataBillsDao.save(domesticDataBill);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void showFees(String number, String month){
        Integer monthFee = 0;
        double phoneFee = 0.0;
        double messageFee = 0.0;
        double localDataFee = 0.0;
        double domesticDataFee = 0.0;
        List<PhoneBills> phoneBillsList = phoneBillsDao.findByNumber(number);
        for (int i = 0; i < phoneBillsList.size(); i++){
            PhoneBills phoneBill = phoneBillsList.get(i);
            String startTime = phoneBill.getStartTime();
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            try {
                Date date = df.parse(startTime);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
                String billMonth = formatter.format(date);

                if (billMonth.equals(month)){
                    phoneFee += phoneBill.getFee();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<MessageBills> messageBillsList = messageBillsDao.findByNumber(number);
        for (int i = 0; i < messageBillsList.size(); i++){
            MessageBills messageBill = messageBillsList.get(i);
            String sendTime = messageBill.getSendTime();
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = null;
            try {
                date = df.parse(sendTime);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
                String billMonth = formatter.format(date);

                if (billMonth.equals(month)){
                    messageFee += messageBill.getFee();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<LocalDataBills> localDataBillsList = localDataBillsDao.findByNumber(number);
        for (int i = 0; i < localDataBillsList.size(); i++){
            LocalDataBills localDataBill = localDataBillsList.get(i);
            String startTime = localDataBill.getStartTime();
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = null;
            try {
                date = df.parse(startTime);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
                String billMonth = formatter.format(date);

                if (billMonth.equals(month)){
                    localDataFee += localDataBill.getFee();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<DomesticDataBills> domesticDataBillsList = domesticDataBillsDao.findByNumber(number);
        for (int i = 0; i < domesticDataBillsList.size(); i++){
            DomesticDataBills domesticDataBill = domesticDataBillsList.get(i);
            String startTime = domesticDataBill.getStartTime();
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = null;
            try {
                date = df.parse(startTime);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
                String billMonth = formatter.format(date);

                if (billMonth.equals(month)){
                    domesticDataFee += domesticDataBill.getFee();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<Orders> ordersList = ordersDao.findByNumberAndState(number, "生效中");
        if (ordersList.size() != 0){
            for (int i = 0; i < ordersList.size(); i++) {
                Orders order = ordersList.get(i);
                String planName = order.getPlanName();
                List<Plans> plansList = plansDao.findByName(planName);
                Plans plan = plansList.get(0);
                monthFee += plan.getMonthFee();
            }
        }

        Bills bills = new Bills(number, month, monthFee, phoneFee, messageFee, localDataFee, domesticDataFee);
        double fee = monthFee + phoneFee + messageFee + localDataFee + domesticDataFee;
        bills.setFee(fee);
        billsDao.save(bills);
    }
}
