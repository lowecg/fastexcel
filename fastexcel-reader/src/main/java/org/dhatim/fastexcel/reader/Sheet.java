/*
 * Copyright 2016 Dhatim.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dhatim.fastexcel.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sheet {

    private final ReadableWorkbook workbook;
    private final int index;
    private final String id;
    private final String name;
    private final List<CellRangeAddress> mergeCells = new ArrayList<>();

    Sheet(ReadableWorkbook workbook, int index, String id, String name) {
        this.workbook = workbook;
        this.index = index;
        this.id = id;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Stream<Row> openStream() throws IOException {
        return workbook.openStream(this);
    }

    public List<Row> read() throws IOException {
        try (Stream<Row> stream = openStream()) {
            return stream.collect(Collectors.toList());
        }
    }

    public void addMergeCell(final String ref) {
        mergeCells.add(CellRangeAddress.valueOf(ref));
    }

    public List<CellRangeAddress> getMergeCells() {
        return mergeCells;
    }
}
