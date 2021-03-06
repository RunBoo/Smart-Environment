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

public final class QueryPreferInfoHolder extends Ice.ObjectHolderBase<QueryPreferInfo>
{
    public
    QueryPreferInfoHolder()
    {
    }

    public
    QueryPreferInfoHolder(QueryPreferInfo value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof QueryPreferInfo)
        {
            value = (QueryPreferInfo)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return _QueryPreferInfoDisp.ice_staticId();
    }
}
