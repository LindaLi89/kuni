package spielbrettview.customviews;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

public class BestListView extends ViewPart{
	public BestListView() {
	}
	
	public static final String ID = "SpielbrettView.blv";
	private Table table;

	@Override
	public void createPartControl(Composite parent) {
		
		parent.setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(parent, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.bottom = new FormAttachment(0, 52);
		fd_lblNewLabel.right = new FormAttachment(0, 158);
		fd_lblNewLabel.top = new FormAttachment(0, 31);
		fd_lblNewLabel.left = new FormAttachment(0, 35);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNewLabel.setText("Bestenliste");
		
		table = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(lblNewLabel, 53);
		fd_table.left = new FormAttachment(0, 79);
		fd_table.bottom = new FormAttachment(0, 327);
		fd_table.right = new FormAttachment(0, 421);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(38);
		tblclmnNewColumn.setText("Platz");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Spieler");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("Punkte");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("Zeit");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("1");
		
		TableItem tableItem_10 = new TableItem(table, SWT.NONE);
		tableItem_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		tableItem_10.setText("2");
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText("3");
		
		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		tableItem_2.setText("4");
		
		TableItem tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText("5");
		
		TableItem tableItem_4 = new TableItem(table, SWT.NONE);
		tableItem_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		tableItem_4.setText("6");
		
		TableItem tableItem_5 = new TableItem(table, SWT.NONE);
		tableItem_5.setText("7");
		
		TableItem tableItem_6 = new TableItem(table, SWT.NONE);
		tableItem_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		tableItem_6.setText("8");
		
		TableItem tableItem_7 = new TableItem(table, SWT.NONE);
		tableItem_7.setText("9");
		
		TableItem tableItem_8 = new TableItem(table, SWT.NONE);
		tableItem_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		tableItem_8.setText("10");

		
	}

	@Override
	public void setFocus() {

		
	}
}
