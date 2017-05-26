package com.hongqing.observerdemo.observer;

import com.hongqing.observerdemo.subject.ISubject;

/**
 * Created by 贺红清 on 2017/5/25.
 */

/**
 * 观察者抽象类
 */
public interface IObserver {
    void update(ISubject subject);
}
