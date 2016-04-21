package com.udacity.gradle.builditbigger.paid;

import com.udacity.gradle.builditbigger.IVersionType;

/**
 * Created by Admin on 12-Apr-16.
 */
public class VersionType implements IVersionType {
    @Override
     public String appType(){ return "PAID"; }
}
