module Banking_app {
	requires javafx.controls;
	requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires  com.jfoenix;
	requires javafx.base;
	requires java.sql;
	requires ojdbc10;
	requires java.desktop;
	requires javafx.graphics;

	exports  application;
	opens application to javafx.graphics, javafx.fxml;
	
}