package com.avifro.entities;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 14:50
 */
public class Bond extends TradableEntity {

    @Override
    public TradableEntityEnum getType() {
        return TradableEntityEnum.BOND;
    }
}
