package com.example.jasperxiao.slidingconflicttest;

import java.util.List;

/**
 * Created by jasperxiao on 2018/7/11
 */
public class ElementInfo {
    private List<Element> elementList; // layout_item
    private List<List<Element>> elementListList; // layout_list

    public ElementInfo(List<Element> elementList, List<List<Element>> elementListList) {
        this.elementList = elementList;
        this.elementListList = elementListList;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }

    public List<List<Element>> getElementListList() {
        return elementListList;
    }

    public void setElementListList(List<List<Element>> elementListList) {
        this.elementListList = elementListList;
    }
}
