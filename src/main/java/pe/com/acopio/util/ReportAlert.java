package pe.com.acopio.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Utilidad para mostrar alertas y reportes en JavaFX
 */
public class ReportAlert {

    /**
     * Muestra un reporte de JasperReports
     */
    public static void showReport(JasperPrint jasperPrint, String title) {
        if (jasperPrint == null) {
            showError("Error", "No se pudo generar el reporte");
            return;
        }

        // Mostrar en un visor de Jasper
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setTitle(title);
        viewer.setVisible(true);
    }

    /**
     * Muestra un diálogo para exportar el reporte a PDF
     */
    public static File showPDFSaveDialog(String defaultFileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte como PDF");

        // Configurar filtro de extensión
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos PDF (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        // Nombre de archivo por defecto con timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = defaultFileName + "_" + timestamp + ".pdf";
        fileChooser.setInitialFileName(fileName);

        // Directorio inicial (Documentos del usuario)
        String userHome = System.getProperty("user.home");
        File initialDirectory = new File(userHome + "/Documents");
        if (initialDirectory.exists()) {
            fileChooser.setInitialDirectory(initialDirectory);
        }

        // Mostrar diálogo
        return fileChooser.showSaveDialog(new Stage());
    }

    /**
     * Muestra un diálogo para exportar el reporte a Excel
     */
    public static File showExcelSaveDialog(String defaultFileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte como Excel");

        // Configurar filtro de extensión
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos Excel (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);

        // Nombre de archivo por defecto con timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = defaultFileName + "_" + timestamp + ".xlsx";
        fileChooser.setInitialFileName(fileName);

        // Directorio inicial (Documentos del usuario)
        String userHome = System.getProperty("user.home");
        File initialDirectory = new File(userHome + "/Documents");
        if (initialDirectory.exists()) {
            fileChooser.setInitialDirectory(initialDirectory);
        }

        // Mostrar diálogo
        return fileChooser.showSaveDialog(new Stage());
    }

    /**
     * Muestra un mensaje de información
     */
    public static void showInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de advertencia
     */
    public static void showWarning(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de error
     */
    public static void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Muestra un diálogo de confirmación
     * 
     * @return true si el usuario confirma, false en caso contrario
     */
    public static boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    /**
     * Muestra un mensaje de éxito
     */
    public static void showSuccess(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("✓ Operación exitosa");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
