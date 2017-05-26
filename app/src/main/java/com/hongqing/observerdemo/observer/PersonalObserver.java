package com.hongqing.observerdemo.observer;

import com.hongqing.observerdemo.subject.ISubject;

/**
 * Created by 贺红清 on 2017/5/26.
 */

/**
 * 具体的观察者对象
 */
public class PersonalObserver implements IObserver {

  @Override
  public void update(ISubject subject) {
    System.out.println("你好小明："+subject.getTemperature()+"请注意！！！！");
  }
}
