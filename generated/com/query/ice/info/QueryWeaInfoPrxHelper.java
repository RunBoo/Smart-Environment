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

/**
 * Provides type-specific helper functions.
 **/
public final class QueryWeaInfoPrxHelper extends Ice.ObjectPrxHelperBase implements QueryWeaInfoPrx
{
    private static final String __queryweather_name = "queryweather";

    public int queryweather(int time)
    {
        return queryweather(time, null, false);
    }

    public int queryweather(int time, java.util.Map<String, String> __ctx)
    {
        return queryweather(time, __ctx, true);
    }

    private int queryweather(int time, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__queryweather_name);
        return end_queryweather(begin_queryweather(time, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_queryweather(int time)
    {
        return begin_queryweather(time, null, false, false, null);
    }

    public Ice.AsyncResult begin_queryweather(int time, java.util.Map<String, String> __ctx)
    {
        return begin_queryweather(time, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_queryweather(int time, Ice.Callback __cb)
    {
        return begin_queryweather(time, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_queryweather(int time, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_queryweather(time, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_queryweather(int time, Callback_QueryWeaInfo_queryweather __cb)
    {
        return begin_queryweather(time, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_queryweather(int time, java.util.Map<String, String> __ctx, Callback_QueryWeaInfo_queryweather __cb)
    {
        return begin_queryweather(time, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_queryweather(int time, 
                                              IceInternal.Functional_IntCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_queryweather(time, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_queryweather(int time, 
                                              IceInternal.Functional_IntCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_queryweather(time, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_queryweather(int time, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_IntCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_queryweather(time, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_queryweather(int time, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_IntCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_queryweather(time, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_queryweather(int time, 
                                               java.util.Map<String, String> __ctx, 
                                               boolean __explicitCtx, 
                                               boolean __synchronous, 
                                               IceInternal.Functional_IntCallback __responseCb, 
                                               IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                               IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_queryweather(time, __ctx, __explicitCtx, __synchronous, 
                                  new IceInternal.Functional_TwowayCallbackInt(__responseCb, __exceptionCb, __sentCb)
                                      {
                                          public final void __completed(Ice.AsyncResult __result)
                                          {
                                              QueryWeaInfoPrxHelper.__queryweather_completed(this, __result);
                                          }
                                      });
    }

    private Ice.AsyncResult begin_queryweather(int time, 
                                               java.util.Map<String, String> __ctx, 
                                               boolean __explicitCtx, 
                                               boolean __synchronous, 
                                               IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__queryweather_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__queryweather_name, __cb);
        try
        {
            __result.prepare(__queryweather_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeInt(time);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public int end_queryweather(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __queryweather_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            int __ret;
            __ret = __is.readInt();
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __queryweather_completed(Ice.TwowayCallbackInt __cb, Ice.AsyncResult __result)
    {
        com.query.ice.info.QueryWeaInfoPrx __proxy = (com.query.ice.info.QueryWeaInfoPrx)__result.getProxy();
        int __ret = 0;
        try
        {
            __ret = __proxy.end_queryweather(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    private static final String __shutdown_name = "shutdown";

    public void shutdown()
    {
        shutdown(null, false);
    }

    public void shutdown(java.util.Map<String, String> __ctx)
    {
        shutdown(__ctx, true);
    }

    private void shutdown(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        end_shutdown(begin_shutdown(__ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_shutdown()
    {
        return begin_shutdown(null, false, false, null);
    }

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx)
    {
        return begin_shutdown(__ctx, true, false, null);
    }

    public Ice.AsyncResult begin_shutdown(Ice.Callback __cb)
    {
        return begin_shutdown(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_shutdown(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_shutdown(Callback_QueryWeaInfo_shutdown __cb)
    {
        return begin_shutdown(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, Callback_QueryWeaInfo_shutdown __cb)
    {
        return begin_shutdown(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_shutdown(IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_shutdown(null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_shutdown(IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_shutdown(null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_shutdown(__ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_VoidCallback __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_shutdown(__ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, 
                                           boolean __explicitCtx, 
                                           boolean __synchronous, 
                                           IceInternal.Functional_VoidCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                           IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_shutdown(__ctx, 
                              __explicitCtx, 
                              __synchronous, 
                              new IceInternal.Functional_OnewayCallback(__responseCb, __exceptionCb, __sentCb));
    }

    private Ice.AsyncResult begin_shutdown(java.util.Map<String, String> __ctx, 
                                           boolean __explicitCtx, 
                                           boolean __synchronous, 
                                           IceInternal.CallbackBase __cb)
    {
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__shutdown_name, __cb);
        try
        {
            __result.prepare(__shutdown_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            __result.writeEmptyParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public void end_shutdown(Ice.AsyncResult __iresult)
    {
        __end(__iresult, __shutdown_name);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static QueryWeaInfoPrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), QueryWeaInfoPrx.class, QueryWeaInfoPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static QueryWeaInfoPrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), QueryWeaInfoPrx.class, QueryWeaInfoPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static QueryWeaInfoPrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), QueryWeaInfoPrx.class, QueryWeaInfoPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static QueryWeaInfoPrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), QueryWeaInfoPrx.class, QueryWeaInfoPrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @return A proxy for this type.
     **/
    public static QueryWeaInfoPrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, QueryWeaInfoPrx.class, QueryWeaInfoPrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    public static QueryWeaInfoPrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, QueryWeaInfoPrx.class, QueryWeaInfoPrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::info::QueryWeaInfo"
    };

    /**
     * Provides the Slice type ID of this type.
     * @return The Slice type ID.
     **/
    public static String ice_staticId()
    {
        return __ids[1];
    }

    public static void __write(IceInternal.BasicStream __os, QueryWeaInfoPrx v)
    {
        __os.writeProxy(v);
    }

    public static QueryWeaInfoPrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            QueryWeaInfoPrxHelper result = new QueryWeaInfoPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}