package com.hongqing.observerdemo.subject;

import com.hongqing.observerdemo.observer.IObserver;

/**
 * Created by 贺红清 on 2017/5/25.
 */

/**
 * 被观察者接口
 */
public interface ISubject {

  /**
   * 添加观察者
   * @param observer
   * @return
   */
  boolean addObserver(IObserver observer);

  /**
   * 移除
   * @param observer
   * @return
   */
  boolean removeObServer(IObserver observer);

  /**
   * 通知所有观察者
   */
  void notifyAllObserver();

  /**
   * 设置温度预警
   * @param temperature
   */
  void setTemperature(float temperature);

  /**
   * 获得温度预警
   * @return
   */
  String getTemperature();
}
