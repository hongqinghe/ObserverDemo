package com.hongqing.observerdemo.observer;

import com.hongqing.observerdemo.subject.ISubject;

/**
 * Created by 贺红清 on 2017/5/26.
 */

public class SchoolObserver implements IObserver {

  @Override
  public void update(ISubject subject) {
    System.out.println("希望小学你好："+subject.getTemperature()+"请注意！！！！");
  }
}
