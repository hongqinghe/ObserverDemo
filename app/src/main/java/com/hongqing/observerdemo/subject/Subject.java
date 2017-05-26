package com.hongqing.observerdemo.subject;

import com.hongqing.observerdemo.observer.IObserver;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by 贺红清 on 2017/5/25.
 */

/**
 * 具体的被观察者
 */
public class Subject implements ISubject {

  //温度
  private float temperature;
  //预警级别
  private String level;
  //观察者集合
  private Vector<IObserver> vectors;

  public Subject() {
    vectors = new Vector<>();
  }

  @Override
  public boolean addObserver(IObserver observer) {

    if (observer != null && !vectors.contains(observer)) {
      return vectors.add(observer);
    }
    return false;
  }

  @Override
  public boolean removeObServer(IObserver observer) {
    return vectors.remove(observer);
  }

  @Override
  public void notifyAllObserver() {
    System.out.println("=======气象部门发布温度预警========="+this.level);
    for (IObserver vector : vectors) {
      vector.update(this);
    }
  }

  /**
   * 根据当时的温度去更新状态给观察者
   */
  @Override
  public void setTemperature(float temperature) {
    this.temperature = temperature;
    this.invoke();
  }

  private void invoke() {
    if (this.temperature >= 35) {
      if (this.temperature >= 35 && this.temperature < 37) {
        this.level = "黄色";
      } else if (this.temperature >= 37 && this.temperature < 40) {
        this.level = "橙色";
      } else {
        this.level = "红色";
      }
      //通知所有观察者
      this.notifyAllObserver();
    }
  }

  @Override
  public String getTemperature() {

    return "目前的温度" + this.temperature;
  }
}
