package com.sixkery.common;

/**
 * @author sixkery
 * @date 2020/7/31
 */
public class Constant {

    //***********方便在 @ApiImplicitParam 的 dataType 属性使用**************//
    public final static String STRING = "String";
    public final static String INT = "int";
    public final static String LONG = "long";
    public final static String DOUBLE = "double";
    public final static String FLOAT = "float";
    public final static String BYTE = "byte";
    public final static String BOOLEAN = "boolean";
    public final static String ARRAY = "array";
    public final static String BINARY = "binary";
    public final static String DATETIME = "dateTime";
    public final static String PASSWORD = "password";


    //*********** 方便在 @ApiImplicitParam 的 paramType 属性使用 **************//
    public final static String QUERY = "query";
    public final static String HEADER = "header";
    public final static String PATH = "path";
    public final static String BODY = "body";
    public final static String FORM = "form";
}
