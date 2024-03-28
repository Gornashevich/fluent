package pages.corrections.widgets.webtables;

import java.util.List;

public interface TableHead {
    TableRow getRow(int index);
    List<TableRow> getRows();
}
