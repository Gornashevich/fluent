package pages.corrections.widgets.webtables;

import java.util.List;

public interface TableRow {
    TableCell getCell(int index);
    List<TableCell> getCells();
}
