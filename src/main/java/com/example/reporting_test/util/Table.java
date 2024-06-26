package com.example.reporting_test.util;

import net.steppschuh.markdowngenerator.MarkdownElement;
import net.steppschuh.markdowngenerator.table.TableRow;
import net.steppschuh.markdowngenerator.util.StringUtil;

import java.util.*;

public class Table extends MarkdownElement {
    public static final String SEPERATOR = "|";
    public static final String WHITESPACE = " ";
    public static final String DEFAULT_TRIMMING_INDICATOR = "~";
    public static final int DEFAULT_MINIMUM_COLUMN_WIDTH = 3;
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_LEFT = 2;
    public static final int ALIGN_RIGHT = 3;
    private List<TableRow> rows;
    private List<Integer> alignments;
    private boolean firstRowIsHeader;
    private int minimumColumnWidth;
    private String trimmingIndicator;

    public Table() {
        this.firstRowIsHeader = true;
        this.minimumColumnWidth = 3;
        this.trimmingIndicator = "~";
        this.rows = new ArrayList();
        this.alignments = new ArrayList();
        this.firstRowIsHeader = true;
    }

    public Table(List<TableRow> rows) {
        this();
        this.rows = rows;
    }

    public Table(List<TableRow> rows, List<Integer> alignments) {
        this(rows);
        this.alignments = alignments;
    }

    public String serialize() {
        Map<Integer, Integer> columnWidths = getColumnWidths(this.rows, this.minimumColumnWidth);
        StringBuilder sb = new StringBuilder();
        String headerSeperator = generateHeaderSeperator(columnWidths, this.alignments);
        boolean headerSeperatorAdded = !this.firstRowIsHeader;
        if (!this.firstRowIsHeader) {
            sb.append(headerSeperator).append(System.lineSeparator());
        }

        Iterator var5 = this.rows.iterator();
        sb.append(headerSeperator).append(System.lineSeparator());
        while(var5.hasNext()) {
            TableRow row = (TableRow)var5.next();

            for(int columnIndex = 0; columnIndex < columnWidths.size(); ++columnIndex) {
                sb.append("|");
                String value = "";
                if (row.getColumns().size() > columnIndex) {
                    Object valueObject = row.getColumns().get(columnIndex);
                    if (valueObject != null) {
                        value = valueObject.toString();
                    }
                }

                if (value.equals(this.trimmingIndicator)) {
                    value = StringUtil.fillUpLeftAligned(value, this.trimmingIndicator, (Integer)columnWidths.get(columnIndex));
                    value = StringUtil.surroundValueWith(value, " ");
                } else {
                    int alignment = getAlignment(this.alignments, columnIndex);
                    value = StringUtil.surroundValueWith(value, " ");
                    value = StringUtil.fillUpAligned(value, " ", (Integer)columnWidths.get(columnIndex) + 2, alignment);
                }

                sb.append(value);
                if (columnIndex == row.getColumns().size() - 1) {
                    sb.append("|");
                }
            }

            if (this.rows.indexOf(row) < this.rows.size() - 1) {
                sb.append(System.lineSeparator());
            }

            if (!headerSeperatorAdded) {
                sb.append(headerSeperator).append(System.lineSeparator());
                headerSeperatorAdded = true;
            }
        }

        return sb.toString();
    }

    public Table trim(int rowsToKeep) {
        this.rows = trim(this, rowsToKeep, this.trimmingIndicator).getRows();
        return this;
    }

    public static Table trim(Table table, int rowsToKeep, String trimmingIndicator) {
        if (table.getRows().size() <= rowsToKeep) {
            return table;
        } else {
            int trimmedEntriesCount = table.getRows().size() - (table.getRows().size() - rowsToKeep);
            int trimmingStartIndex = Math.round((float)(trimmedEntriesCount / 2)) + 1;
            int trimmingStopIndex = table.getRows().size() - trimmingStartIndex;
            List<TableRow> trimmedRows = new ArrayList();

            for(int i = trimmingStartIndex; i <= trimmingStopIndex; ++i) {
                trimmedRows.add(table.getRows().get(i));
            }

            table.getRows().removeAll(trimmedRows);
            TableRow trimmingIndicatorRow = new TableRow();

            for(int columnIndex = 0; columnIndex < ((TableRow)table.getRows().get(0)).getColumns().size(); ++columnIndex) {
                trimmingIndicatorRow.getColumns().add(trimmingIndicator);
            }

            table.getRows().add(trimmingStartIndex, trimmingIndicatorRow);
            return table;
        }
    }

    public static String generateHeaderSeperator(Map<Integer, Integer> columnWidths, List<Integer> alignments) {
        StringBuilder sb = new StringBuilder();

        for(int columnIndex = 0; columnIndex < columnWidths.entrySet().size(); ++columnIndex) {
            sb.append("-");
            String value = StringUtil.fillUpLeftAligned("", "-", (Integer)columnWidths.get(columnIndex));
            int alignment = getAlignment(alignments, columnIndex);
            switch (alignment) {
                case 1:
                    value = ":" + value + ":";
                    break;
                case 3:
                    value = " " + value + ":";
                    break;
                default:
                    value = StringUtil.surroundValueWith(value, "-");
            }

            sb.append(value);
            if (columnIndex == columnWidths.entrySet().size() - 1) {
                sb.append("-");
            }
        }

        return sb.toString();
    }

    public static Map<Integer, Integer> getColumnWidths(List<TableRow> rows, int minimumColumnWidth) {
        Map<Integer, Integer> columnWidths = new HashMap();
        if (rows.isEmpty()) {
            return columnWidths;
        } else {
            for(int columnIndex = 0; columnIndex < ((TableRow)rows.get(0)).getColumns().size(); ++columnIndex) {
                columnWidths.put(columnIndex, getMaximumItemLength(rows, columnIndex, minimumColumnWidth));
            }

            return columnWidths;
        }
    }

    public static int getMaximumItemLength(List<TableRow> rows, int columnIndex, int minimumColumnWidth) {
        int maximum = minimumColumnWidth;
        Iterator var4 = rows.iterator();

        while(var4.hasNext()) {
            TableRow row = (TableRow)var4.next();
            if (row.getColumns().size() >= columnIndex + 1) {
                Object value = row.getColumns().get(columnIndex);
                if (value != null) {
                    maximum = Math.max(value.toString().length(), maximum);
                }
            }
        }

        return maximum;
    }

    public static int getAlignment(List<Integer> alignments, int columnIndex) {
        if (alignments.isEmpty()) {
            return 2;
        } else {
            if (columnIndex >= alignments.size()) {
                columnIndex = alignments.size() - 1;
            }

            return (Integer)alignments.get(columnIndex);
        }
    }

    public List<TableRow> getRows() {
        return this.rows;
    }

    public void setRows(List<TableRow> rows) {
        this.rows = rows;
        this.invalidateSerialized();
    }

    public List<Integer> getAlignments() {
        return this.alignments;
    }

    public void setAlignments(List<Integer> alignments) {
        this.alignments = alignments;
        this.invalidateSerialized();
    }

    public boolean isFirstRowHeader() {
        return this.firstRowIsHeader;
    }

    public void useFirstRowAsHeader(boolean firstRowIsHeader) {
        this.firstRowIsHeader = firstRowIsHeader;
        this.invalidateSerialized();
    }

    public int getMinimumColumnWidth() {
        return this.minimumColumnWidth;
    }

    public void setMinimumColumnWidth(int minimumColumnWidth) {
        this.minimumColumnWidth = minimumColumnWidth;
        this.invalidateSerialized();
    }

    public String getTrimmingIndicator() {
        return this.trimmingIndicator;
    }

    public void setTrimmingIndicator(String trimmingIndicator) {
        this.trimmingIndicator = trimmingIndicator;
    }

    public static class Builder {
        private Table table = new Table();
        private int rowLimit;

        public Builder() {
        }

        public Table.Builder withRows(List<TableRow> tableRows) {
            this.table.setRows(tableRows);
            return this;
        }

        public Table.Builder addRow(TableRow tableRow) {
            this.table.getRows().add(tableRow);
            return this;
        }

        public Table.Builder addRow(Object... objects) {
            TableRow tableRow = new TableRow(Arrays.asList(objects));
            this.table.getRows().add(tableRow);
            return this;
        }

        public Table.Builder withAlignments(List<Integer> alignments) {
            this.table.setAlignments(alignments);
            return this;
        }

        public Table.Builder withAlignments(Integer... alignments) {
            return this.withAlignments(Arrays.asList(alignments));
        }

        public Table.Builder withAlignment(int alignment) {
            return this.withAlignments(Collections.singletonList(alignment));
        }

        public Table.Builder withRowLimit(int rowLimit) {
            this.rowLimit = rowLimit;
            return this;
        }

        public Table.Builder withTrimmingIndicator(String trimmingIndicator) {
            this.table.setTrimmingIndicator(trimmingIndicator);
            return this;
        }

        public Table build() {
            if (this.rowLimit > 0) {
                this.table.trim(this.rowLimit);
            }

            return this.table;
        }
    }
}
