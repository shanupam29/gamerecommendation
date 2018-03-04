package com.assignment.game.recommendation.dto;

import com.assignment.game.recommendation.model.GameModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GamesDTO {

    private String rec1;
    private String rec2;
    private String rec3;
    private String rec4;
    private String rec5;
    private String rec6;
    private String rec7;
    private String rec8;
    private String rec9;
    private String rec10;

    public String getRec1() {
        return rec1;
    }

    public void setRec1(String rec1) {
        this.rec1 = rec1;
    }

    public String getRec2() {
        return rec2;
    }

    public void setRec2(String rec2) {
        this.rec2 = rec2;
    }

    public String getRec3() {
        return rec3;
    }

    public void setRec3(String rec3) {
        this.rec3 = rec3;
    }

    public String getRec4() {
        return rec4;
    }

    public void setRec4(String rec4) {
        this.rec4 = rec4;
    }

    public String getRec5() {
        return rec5;
    }

    public void setRec5(String rec5) {
        this.rec5 = rec5;
    }

    public String getRec6() {
        return rec6;
    }

    public void setRec6(String rec6) {
        this.rec6 = rec6;
    }

    public String getRec7() {
        return rec7;
    }

    public void setRec7(String rec7) {
        this.rec7 = rec7;
    }

    public String getRec8() {
        return rec8;
    }

    public void setRec8(String rec8) {
        this.rec8 = rec8;
    }

    public String getRec9() {
        return rec9;
    }

    public void setRec9(String rec9) {
        this.rec9 = rec9;
    }

    public String getRec10() {
        return rec10;
    }

    public void setRec10(String rec10) {
        this.rec10 = rec10;
    }

    /**
     * Util method to return list of GameModel object,
     * limited by count requested by the service.
     *
     * @param count
     * @return
     */
    public List<GameModel> toList(Long count){
        List<GameModel> list =  new ArrayList<>();
        list.add(new GameModel(rec1));
        list.add(new GameModel(rec2));
        list.add(new GameModel(rec3));
        list.add(new GameModel(rec4));
        list.add(new GameModel(rec5));
        list.add(new GameModel(rec6));
        list.add(new GameModel(rec7));
        list.add(new GameModel(rec8));
        list.add(new GameModel(rec9));
        list.add(new GameModel(rec10));
        count = count ==null || count < 0 ? list.size() : count;
        return list.stream().limit(count).collect(Collectors.toList());
    }
}
