package com.example.rainbow.database.option;

import com.example.rainbow.base.RainBowApplication;
import com.example.rainbow.database.UserBeanDao;
import com.example.rainbow.database.entity.UserBean;
import java.util.List;

public class UserOption {

    private UserOption() {
    }

    private static UserOption defaultInstance;

    public static UserOption getInstance() {

        UserOption userOption = defaultInstance;
        if (defaultInstance == null) {
            synchronized (UserOption.class) {
                if (defaultInstance == null) {
                    userOption = new UserOption();
                    defaultInstance = userOption;
                }
            }
        }
        return userOption;
    }

    public void addUser(UserBean userBean) {
        UserBeanDao userBeanDao = RainBowApplication.getApplication().getDaoSession().getUserBeanDao();
        if (querryUser() == null) {
            userBeanDao.insert(userBean);
        }else {
            userBeanDao.update(userBean);
        }
    }

    public UserBean querryUser() {
        UserBeanDao userBeanDao = RainBowApplication.getApplication().getDaoSession().getUserBeanDao();
        List<UserBean> list = userBeanDao.queryBuilder().list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public void updateUser(UserBean userBean) {
        UserBeanDao userBeanDao = RainBowApplication.getApplication().getDaoSession().getUserBeanDao();
        userBeanDao.update(userBean);
    }


    public void delteUser() {
        UserBeanDao userBeanDao = RainBowApplication.getApplication().getDaoSession().getUserBeanDao();
        userBeanDao.deleteAll();
    }


}
