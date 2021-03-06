/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gobblin.compliance.purger;

import gobblin.configuration.WorkUnitState;
import gobblin.converter.Converter;
import gobblin.converter.SingleRecordIterable;


/**
 * A {@link Converter} to build compliance purge queries. Queries are added to the {@link PurgeableHivePartitionDataset}.
 *
 * @author adsharma
 */
public class HivePurgerConverter extends Converter<PurgeableHivePartitionDatasetSchema, PurgeableHivePartitionDatasetSchema, PurgeableHivePartitionDataset, PurgeableHivePartitionDataset> {

  @Override
  public PurgeableHivePartitionDatasetSchema convertSchema(PurgeableHivePartitionDatasetSchema schema,
      WorkUnitState state) {
    return schema;
  }

  @Override
  public Iterable<PurgeableHivePartitionDataset> convertRecord(PurgeableHivePartitionDatasetSchema schema,
      PurgeableHivePartitionDataset record, WorkUnitState state) {
    record.setPurgeQueries(HivePurgerQueryTemplate.getPurgeQueries(record));
    return new SingleRecordIterable<>(record);
  }
}

