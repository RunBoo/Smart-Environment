// **********************************************************************
//
// Copyright (c) 2003-2017 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.4
//
// <auto-generated>
//
// Generated from file `EnviroSmart.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.query.ice.info;

public interface QueryWeaInfoPrx extends Ice.ObjectPrx
{
    public int queryweather(int time);

    public int queryweather(int time, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryweather(int time);

    public Ice.AsyncResult begin_queryweather(int time, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryweather(int time, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryweather(int time, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryweather(int time, Callback_QueryWeaInfo_queryweather __cb);

    public Ice.AsyncResult begin_queryweather(int time, java.util.Map<String, String> __ctx, Callback_QueryWeaInfo_queryweather __cb);

    public Ice.AsyncResult begin_queryweather(int time, 
                                              IceInternal.Functional_IntCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryweather(int time, 
                                              IceInternal.Functional_IntCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryweather(int time, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_IntCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryweather(int time, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_IntCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb);

    public int end_queryweather(Ice.AsyncResult __result);

    public void shutdown();

    public void shutdown(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_shutdown();

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_shutdown(Ice.Callback __cb);

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_shutdown(Callback_QueryWeaInfo_shutdown __cb);

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, Callback_QueryWeaInfo_shutdown __cb);

    public Ice.AsyncResult begin_shutdown(IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_shutdown(IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public void end_shutdown(Ice.AsyncResult __result);
}
