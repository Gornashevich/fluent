package pages.corrections.widgets.webtables;

import pages.corrections.widgets.Row;

import java.util.List;

public interface TableBody {
    Row getRow(int index);

    List<Row> getRows();

}
