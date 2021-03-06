/**
 * Copyright (C) <2021>  <chen junwen>
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program.  If
 * not, see <http://www.gnu.org/licenses/>.
 */
package io.mycat.beans.resultset;

import io.mycat.ExecuteType;
import lombok.Getter;

import java.io.IOException;


@Getter
public class MycatProxyResponse implements MycatResponse {
    final ExecuteType executeType;
    final String targetName;
    final String sql;

    public MycatProxyResponse(ExecuteType executeType,
                              String targetName,
                              String sql) {
        this.executeType = executeType;
        this.targetName = targetName;
        this.sql = sql;
    }

    public static MycatProxyResponse create(ExecuteType executeType, String targetName, String sql) {
        return new MycatProxyResponse(executeType, targetName, sql);
    }

    @Override
    public MycatResultSetType getType() {
        return MycatResultSetType.PROXY;
    }

    @Override
    public void close() throws IOException {

    }
}