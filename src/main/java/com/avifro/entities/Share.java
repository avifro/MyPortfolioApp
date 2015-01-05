package com.avifro.entities;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 14:50
 */
public class Share extends TradableEntity {

    @Override
    public TradableEntityEnum getType() {
        return TradableEntityEnum.SHARE;
    }
}
