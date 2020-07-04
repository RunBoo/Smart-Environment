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

public class PreferenceIn implements java.lang.Cloneable, java.io.Serializable
{
    public int type;

    public String service;

    public double temthreshold;

    public PreferenceIn()
    {
        service = "";
    }

    public PreferenceIn(int type, String service, double temthreshold)
    {
        this.type = type;
        this.service = service;
        this.temthreshold = temthreshold;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        PreferenceIn _r = null;
        if(rhs instanceof PreferenceIn)
        {
            _r = (PreferenceIn)rhs;
        }

        if(_r != null)
        {
            if(type != _r.type)
            {
                return false;
            }
            if(service != _r.service)
            {
                if(service == null || _r.service == null || !service.equals(_r.service))
                {
                    return false;
                }
            }
            if(temthreshold != _r.temthreshold)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::info::PreferenceIn");
        __h = IceInternal.HashUtil.hashAdd(__h, type);
        __h = IceInternal.HashUtil.hashAdd(__h, service);
        __h = IceInternal.HashUtil.hashAdd(__h, temthreshold);
        return __h;
    }

    public PreferenceIn
    clone()
    {
        PreferenceIn c = null;
        try
        {
            c = (PreferenceIn)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeInt(type);
        __os.writeString(service);
        __os.writeDouble(temthreshold);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        type = __is.readInt();
        service = __is.readString();
        temthreshold = __is.readDouble();
    }

    static public void
    __write(IceInternal.BasicStream __os, PreferenceIn __v)
    {
        if(__v == null)
        {
            __nullMarshalValue.__write(__os);
        }
        else
        {
            __v.__write(__os);
        }
    }

    static public PreferenceIn
    __read(IceInternal.BasicStream __is, PreferenceIn __v)
    {
        if(__v == null)
        {
             __v = new PreferenceIn();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final PreferenceIn __nullMarshalValue = new PreferenceIn();

    public static final long serialVersionUID = 771090192L;
}