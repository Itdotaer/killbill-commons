/*
 * Copyright 2004-2014 Brian McCallister
 * Copyright 2010-2014 Ning, Inc.
 * Copyright 2014-2020 Groupon, Inc
 * Copyright 2020-2020 Equinix, Inc
 * Copyright 2014-2020 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.skife.jdbi.v2;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.skife.jdbi.derby.DerbyHelper;

import java.sql.Connection;

import static org.junit.Assert.assertFalse;

@Category(JDBITests.class)
public class TestDataSourceConnectionFactory
{
    private static final DerbyHelper DERBY_HELPER = new DerbyHelper();

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        DERBY_HELPER.start();
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        DERBY_HELPER.stop();
    }

    private DataSourceConnectionFactory f;

    @Before
    public void setUp() throws Exception
    {
        this.f = new DataSourceConnectionFactory(DERBY_HELPER.getDataSource());
    }

    @Test
    public void testObtainConnection() throws Exception
    {
        Connection c = f.openConnection();
        assertFalse(c.isClosed());
        c.close();
    }
}
