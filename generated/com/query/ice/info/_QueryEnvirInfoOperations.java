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

import java.io.IOException;

public interface _QueryEnvirInfoOperations
{
    EnvirInformation queryenvir(String username, int time, Ice.Current __current) throws IOException;

    void shutdown(Ice.Current __current);
}
