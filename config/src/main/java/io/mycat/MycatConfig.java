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
package io.mycat;

import io.mycat.config.*;
import io.mycat.util.YamlUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class MycatConfig {
    List<PatternRootConfig> interceptors = new ArrayList<>();
    ShardingQueryRootConfig metadata = new ShardingQueryRootConfig();
    DatasourceRootConfig datasource = new DatasourceRootConfig();
    ClusterRootConfig cluster = new ClusterRootConfig();
    PlugRootConfig plug = new PlugRootConfig();
    ManagerConfig manager = new ManagerConfig();
    List<String> properties;

    public static void main(String[] args) {
        MycatConfig mycatConfig = new MycatConfig();
        String dump = YamlUtil.dump(mycatConfig);
        System.out.println(dump);
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class SqlsGroup {
        List<PatternRootConfig> sqls = new ArrayList<>();
    }
}