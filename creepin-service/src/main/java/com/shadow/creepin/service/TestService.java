package com.shadow.creepin.service;

import com.shadow.common.bean.creepin.ao.TestAO;

/**
 * @author cuipeng 2020/7/2 16:33
 */
public interface TestService {

    Object test(TestAO ao);

    void getMerch();

    void updateMerch();
}
