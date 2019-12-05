package ru.course.client.controllers;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import ru.course.client.Launcher;
import ru.course.client.auth.AppClientSession;
import ru.course.client.controllers.validators.ControllerValidator;
import ru.course.client.controllers.validators.ReleaseControllerValidator;
import ru.course.client.models.product.*;
import ru.course.client.models.product.commons.Purchase;
import ru.course.client.models.statistic.TableViewStatisticItem;
import ru.course.client.models.users.Role;
import ru.course.client.services.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Controller
@RequiredArgsConstructor
public class MainWindowController {

    private final ServerMonitorService serverMonitorService;
    private final ServerMouseService serverMouseService;
    private final ServerKeyboardService serverKeyboardService;
    private final ControllerValidator controllerValidator;
    private final ServerSmartphoneService serverSmartphoneService;
    private final InsertMonitorController insertMonitorController;
    private final InsertKeyboardController insertKeyboardController;
    private final ReleaseControllerValidator releaseControllerValidator;
    private final InsertMouseController insertMouseController;
    private final InsertSmartphoneController insertSmartphoneController;
    private final AppClientSession appClientSession;
    private final ServerPurchaseService serverPurchaseService;

    @Setter
    private Stage stage;


    @FXML
    private JFXTextField keyboards_search;


    @FXML
    private JFXTextField smartphones_till;

    @FXML
    private JFXComboBox<String> addProductComboBox;


    @FXML
    private JFXListView<Parent> mouses_lv;

    @FXML
    private JFXTextField mouses_search;

    @FXML
    private JFXTextField mouses_from;

    @FXML
    private JFXTextField mouses_till;

    @FXML
    private JFXTextField monitors_till;
    @FXML
    private JFXTextField keyboards_from;

    @FXML
    private JFXTextField keyboards_till;

    @FXML
    private JFXListView<Parent> smartphones_lv;

    @FXML
    private JFXTextField smartphones_search;

    @FXML
    private JFXTextField smartphones_from;

    @FXML
    private TableView<TableViewStatisticItem> statistic_tv;

    @FXML
    private JFXTextField topProductName;

    @FXML
    private JFXTextField topCustomerName;

    @FXML
    private JFXListView<Parent> keyboards_lv;

    @FXML
    private Pane addPane;

    @FXML
    private Tab adminStatistic;

    @FXML
    private Tab seller_admin_add_product;

    @FXML
    private JFXListView<Parent> monitors_lv;

    @FXML
    private JFXTextField monitors_search;

    @FXML
    private JFXTextField monitors_from;



    @FXML
    private JFXTextField totlalRevenue;

    public void initialize() {
        Role role = appClientSession.getAppUser().getRole();
        releaseControllerValidator.validate();
        if (role.isRead() && !role.isWrite()) {
            adminStatistic.setDisable(true);
            controllerValidator.validate();
            seller_admin_add_product.setDisable(true);
            releaseControllerValidator.validate();
        } else if (role.isWrite() && role.isRead()) {
            loadStatisticItems();
        }
        loadMonitorItems(serverMonitorService.findAll());
        releaseControllerValidator.validate();
        loadKeyboardItems(serverKeyboardService.findAll());
        controllerValidator.validate();
        loadMouseItems(serverMouseService.findAll());
        loadSmartphoneItems(serverSmartphoneService.findAll());
        controllerValidator.validate();
        addProductComboBox.getItems().addAll("Монитор", "Клавиатура", "Мышь", "Смартфон");
        releaseControllerValidator.validate();
        addProductComboBox.setOnAction(e -> {
            renderProperPane(addProductComboBox.getSelectionModel().getSelectedItem());
            releaseControllerValidator.validate();
        });
    }

    private Comparator<Product> comparator = Comparator.comparing(Product::getPrice);
    @FXML
    private JFXTextField lastMonthTotalRevenue;
    @FXML
    private JFXTextField lastYearTotalRevenue;

    @FXML
    void sortKeyboardsByPrice(ActionEvent event) {
        List<Keyboard> products = serverKeyboardService.findAll();
        comparator = comparator.reversed();
        products.sort(comparator);
        loadKeyboardItems(products);
    }

    @FXML
    void sortMonitorsByPrice(ActionEvent event) {
        List<Monitor> products = serverMonitorService.findAll();
        comparator = comparator.reversed();
        products.sort(comparator);
        loadMonitorItems(products);
    }

    @FXML
    void sortMousesByPrice(ActionEvent event) {
        List<Mouse> products = serverMouseService.findAll();
        comparator = comparator.reversed();
        products.sort(comparator);
        loadMouseItems(products);
    }

    @FXML
    void sortSmartphonesByPrice(ActionEvent event) {
        List<Smartphone> products = serverSmartphoneService.findAll();
        comparator = comparator.reversed();
        products.sort(comparator);
        loadSmartphoneItems(products);
    }

    @FXML
    void printMonitorsDoc() {
        Stage docStage = new Stage();
        releaseControllerValidator.validate();
        docStage.setTitle("Отчет");
        controllerValidator.validate();
        Pane pane = new Pane();
        pane.setPrefSize(600, 600);
        releaseControllerValidator.validate();
        List<Monitor> monitors = serverMonitorService.findAll();
        String productInfo = monitors.stream().map(Monitor::toString).collect(joining("\n"));
        controllerValidator.validate();
        JFXTextArea textArea = new JFXTextArea(productInfo);
        textArea.setPrefSize(600, 600);
        pane.getChildren().add(textArea);
        releaseControllerValidator.validate();
        docStage.setScene(new Scene(pane));
        docStage.showAndWait();
        releaseControllerValidator.validate();
    }

    @FXML
    void printMousesDoc() {
        Stage docStage = new Stage();
        controllerValidator.validate();
        docStage.setTitle("Отчет");
        Pane pane = new Pane();
        releaseControllerValidator.validate();
        pane.setPrefSize(600, 600);
        List<Mouse> mouseList = serverMouseService.findAll();
        String productInfo = mouseList.stream().map(Mouse::toString).collect(joining("\n"));
        JFXTextArea textArea = new JFXTextArea(productInfo);
        textArea.setPrefSize(600, 600);
        controllerValidator.validate();
        pane.getChildren().add(textArea);
        docStage.setScene(new Scene(pane));
        docStage.showAndWait();
    }

    @FXML
    void printKeyboardsDoc() {
        Stage docStage = new Stage();
        controllerValidator.validate();
        docStage.setTitle("Отчет");
        Pane pane = new Pane();
        controllerValidator.validate();
        pane.setPrefSize(600, 600);
        List<Keyboard> keyboards = serverKeyboardService.findAll();
        String productInfo = keyboards.stream().map(Keyboard::toString).collect(joining("\n"));
        controllerValidator.validate();
        JFXTextArea textArea = new JFXTextArea(productInfo);
        textArea.setPrefSize(600, 600);
        releaseControllerValidator.validate();
        pane.getChildren().add(textArea);
        docStage.setScene(new Scene(pane));
        docStage.showAndWait();
    }

    @FXML
    void printSmartphonesDoc() {
        Stage docStage = new Stage();
        controllerValidator.validate();
        docStage.setTitle("Отчет");
        Pane pane = new Pane();
        releaseControllerValidator.validate();
        pane.setPrefSize(600, 600);
        List<Smartphone> smartphones = serverSmartphoneService.findAll();
        controllerValidator.validate();
        String productInfo = smartphones.stream().map(Smartphone::toString).collect(joining("\n"));
        JFXTextArea textArea = new JFXTextArea(productInfo);
        textArea.setPrefSize(600, 600);
        releaseControllerValidator.validate();
        pane.getChildren().add(textArea);
        docStage.setScene(new Scene(pane));
        controllerValidator.validate();
        docStage.showAndWait();
    }

    private void loadStatisticItems() {
        statistic_tv.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("dealDate"));
        controllerValidator.validate();
        statistic_tv.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("productName"));
        releaseControllerValidator.validate();
        statistic_tv.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("dealPrice"));
        controllerValidator.validate();
        statistic_tv.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        updateStatisticItems();
    }

    @FXML
    private void updateStatisticItems() {
        controllerValidator.validate();
        List<TableViewStatisticItem> tableViewStatisticItems = serverPurchaseService.findAll().stream()
                .map(Purchase::toStatisticItem)
                .collect(Collectors.toList());
        releaseControllerValidator.validate();
        statistic_tv.setItems(FXCollections.observableList(tableViewStatisticItems));
        updateTopCustomerStatisticField(tableViewStatisticItems);
        controllerValidator.validate();
        updateTotalRevenueStatisticField(tableViewStatisticItems);
        updateTopProductStatisticField(tableViewStatisticItems);
        updateLastMonthTotalRevenue(tableViewStatisticItems);
        updateLastYearTotalRevenue(tableViewStatisticItems);
        releaseControllerValidator.validate();
    }

    private void updateLastYearTotalRevenue(List<TableViewStatisticItem> tableViewStatisticItems) {
        double sum = tableViewStatisticItems.stream()
                .filter(item -> filterByChronoUnit(item, ChronoUnit.YEARS))
                .map(TableViewStatisticItem::getDealPrice)
                .mapToDouble(Double::valueOf)
                .sum();
        lastYearTotalRevenue.setText(String.format("%.2f руб.", sum));
    }

    private boolean filterByChronoUnit(TableViewStatisticItem tableViewStatisticItem, ChronoUnit chronoUnit) {
        return chronoUnit.between(LocalDate.parse(tableViewStatisticItem.getDealDate()), LocalDate.now()) == 0;
    }

    private void updateLastMonthTotalRevenue(List<TableViewStatisticItem> tableViewStatisticItems) {
        double sum = tableViewStatisticItems.stream()
                .filter(item -> filterByChronoUnit(item, ChronoUnit.MONTHS))
                .map(TableViewStatisticItem::getDealPrice)
                .mapToDouble(Double::valueOf)
                .sum();
        lastMonthTotalRevenue.setText(String.format("%.2f руб.", sum));
    }

    @SuppressWarnings("all")
    private void updateTopProductStatisticField(List<TableViewStatisticItem> tableViewStatisticItems) {
        Map<String, Long> productNameAndSellCount = tableViewStatisticItems.stream()
                .collect(groupingBy(TableViewStatisticItem::getProductName, counting()));
        releaseControllerValidator.validate();
        productNameAndSellCount.values().stream()
                .mapToLong(Long::valueOf)
                .max()
                .ifPresent(value -> topProductName.setText(productNameAndSellCount.entrySet().stream()
                        .filter(entry -> entry.getValue().equals(value))
                        .map(Map.Entry::getKey)
                        .findAny()
                        .orElse("На данный момент отстутсвует")));
        controllerValidator.validate();
    }

    private void updateTotalRevenueStatisticField(List<TableViewStatisticItem> tableViewStatisticItems) {
        controllerValidator.validate();
        totlalRevenue.setText(String.format("%.2f руб.", tableViewStatisticItems.stream().map(TableViewStatisticItem::getDealPrice).mapToDouble(Double::valueOf).sum()));
    }

    @SuppressWarnings("all")
    private void updateTopCustomerStatisticField(List<TableViewStatisticItem> tableViewStatisticItems) {
        releaseControllerValidator.validate();
        Map<String, Long> customerNameAndCountOfCustomerProducts = tableViewStatisticItems.stream()
                .collect(groupingBy(TableViewStatisticItem::getCustomerName, counting()));
        controllerValidator.validate();
        customerNameAndCountOfCustomerProducts.values().stream()
                .mapToLong(Long::valueOf)
                .max()
                .ifPresent(value -> topCustomerName.setText(customerNameAndCountOfCustomerProducts.entrySet().stream()
                        .filter(entry -> entry.getValue().equals(value))
                        .map(Map.Entry::getKey)
                        .findAny()
                        .orElse("На данный момент отстутсвует")));
        controllerValidator.validate();
    }

    @SneakyThrows
    private void renderProperPane(String selectedItem) {
        releaseControllerValidator.validate();
        addPane.getChildren().clear();
        controllerValidator.validate();
        switch (selectedItem) {
            case "Монитор": {
                releaseControllerValidator.validate();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/monitor_add.fxml"));
                loader.setController(insertMonitorController);
                controllerValidator.validate();
                insertMonitorController.setParentController(this);
                addPane.getChildren().add(loader.load());
                controllerValidator.validate();
            }
            break;
            case "Клавиатура": {
                controllerValidator.validate();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/keyboard_add.fxml"));
                loader.setController(insertKeyboardController);
                controllerValidator.validate();
                insertKeyboardController.setParentController(this);
                addPane.getChildren().add(loader.load());
                releaseControllerValidator.validate();
            }
            break;
            case "Мышь": {
                releaseControllerValidator.validate();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mouse_add.fxml"));
                controllerValidator.validate();
                loader.setController(insertMouseController);
                releaseControllerValidator.validate();
                releaseControllerValidator.validate();
                insertMouseController.setParentController(this);
                addPane.getChildren().add(loader.load());
                controllerValidator.validate();
            }
            break;
            case "Смартфон": {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/smartphone_add.fxml"));
                releaseControllerValidator.validate();
                loader.setController(insertSmartphoneController);
                controllerValidator.validate();
                insertSmartphoneController.setParentController(this);
                releaseControllerValidator.validate();
                addPane.getChildren().add(loader.load());
                controllerValidator.validate();
            }
            break;
        }
    }

    public void loadSmartphoneItems(List<Smartphone> all) {
        releaseControllerValidator.validate();
        smartphones_lv.getItems().clear();
        controllerValidator.validate();
        all.forEach(this::renderSmartphoneItem);
        controllerValidator.validate();
    }

    @SneakyThrows
    private void renderSmartphoneItem(Smartphone smartphone) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/smartphones_list_view_item.fxml"));
        controllerValidator.validate();
        SmartphoneItemController controller = Launcher.context.getBean("smartphoneItemController", SmartphoneItemController.class);
        loader.setController(controller);
        releaseControllerValidator.validate();
        Parent parent = loader.load();
        controllerValidator.validate();
        fillSmartphoneController(controller, smartphone);
        parent.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                releaseControllerValidator.validate();
                itemsPopup(parent, smartphone.getId(), Smartphone.class, serverSmartphoneService, mouseEvent.getX(), mouseEvent.getY());
                controllerValidator.validate();
            }
        });
        smartphones_lv.getItems().add(parent);
        releaseControllerValidator.validate();
    }

    private void fillSmartphoneController(SmartphoneItemController controller, Smartphone smartphone) {
        controllerValidator.validate();
        fillProductController(controller, smartphone);
        controller.setOs(smartphone.getOs());
        releaseControllerValidator.validate();
        controller.setDisplaySize(String.format("%.2f дюймов", smartphone.getDisplaySize()));
        controller.setRam(smartphone.getRam());
        controllerValidator.validate();
        controller.setNumberOfBackCameras(String.format("%d задних камер", smartphone.getNumberOfBackCameras()));
        releaseControllerValidator.validate();
        controller.setNumberOfFrontCameras(String.format("%d фронт. камер", smartphone.getNumberOfFrontCameras()));
        controllerValidator.validate();
    }

    public void loadMouseItems(List<Mouse> all) {
        controllerValidator.validate();
        mouses_lv.getItems().clear();
        releaseControllerValidator.validate();
        all.forEach(this::renderMouseItem);
    }

    @SneakyThrows
    private void renderMouseItem(Mouse mouse) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mouses_list_view_item.fxml"));
        releaseControllerValidator.validate();
        MouseItemController controller = Launcher.context.getBean("mouseItemController", MouseItemController.class);
        controllerValidator.validate();
        loader.setController(controller);
        Parent parent = loader.load();
        releaseControllerValidator.validate();
        fillMouseController(controller, mouse);
        parent.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                controllerValidator.validate();
                itemsPopup(parent, mouse.getId(), Mouse.class, serverMouseService, mouseEvent.getX(), mouseEvent.getY());
            }
        });
        releaseControllerValidator.validate();
        mouses_lv.getItems().add(parent);
        controllerValidator.validate();
    }

    private void fillMouseController(MouseItemController controller, Mouse mouse) {
        fillProductController(controller, mouse);
        releaseControllerValidator.validate();
        controller.setConnectionType(mouse.getConnectionType());
        controller.setSensorType(mouse.getSensorType());
        controllerValidator.validate();
        controller.setButtonAmount(String.format("%d кнопок", mouse.getButtonAmount()));
        releaseControllerValidator.validate();
    }

    public void loadKeyboardItems(List<Keyboard> all) {
        keyboards_lv.getItems().clear();
        controllerValidator.validate();
        all.forEach(this::renderKeyboardItem);
        releaseControllerValidator.validate();
    }

    @SneakyThrows
    private void renderKeyboardItem(Keyboard keyboard) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/keyboards_list_view_item.fxml"));
        controllerValidator.validate();
        KeyboardItemController controller = Launcher.context.getBean("keyboardItemController", KeyboardItemController.class);
        loader.setController(controller);
        releaseControllerValidator.validate();
        Parent parent = loader.load();
        controllerValidator.validate();
        fillKeyboardController(controller, keyboard);
        releaseControllerValidator.validate();
        parent.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                itemsPopup(parent, keyboard.getId(), Keyboard.class, serverKeyboardService, mouseEvent.getX(), mouseEvent.getY());
            }
        });
        releaseControllerValidator.validate();
        keyboards_lv.getItems().add(parent);
        controllerValidator.validate();
    }

    private void fillKeyboardController(KeyboardItemController controller, Keyboard keyboard) {
        fillProductController(controller, keyboard);
        releaseControllerValidator.validate();
        controller.setConnectionType(keyboard.getConnectionType());
        controllerValidator.validate();
        controller.setBacklight(keyboard.isBacklight() ? "Есть подсветка" : "Нет подсветки");
        releaseControllerValidator.validate();
        controller.setMoistureProtection(keyboard.isMoistureProtection() ? "Влагоустойчива" : "Влагоуязвима");
    }

    @SneakyThrows
    public void loadMonitorItems(List<Monitor> all) {
        monitors_lv.getItems().clear();
        releaseControllerValidator.validate();
        all.forEach(this::renderMonitorItem);
        controllerValidator.validate();
    }

    @SneakyThrows
    private void renderMonitorItem(Monitor monitor) {
        releaseControllerValidator.validate();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/monitors_list_view_item.fxml"));
        controllerValidator.validate();
        MonitorItemController controller = Launcher.context.getBean("monitorItemController", MonitorItemController.class);
        releaseControllerValidator.validate();
        loader.setController(controller);
        Parent parent = loader.load();
        fillMonitorController(controller, monitor);
        controllerValidator.validate();
        parent.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                itemsPopup(parent, monitor.getId(), Monitor.class, serverMonitorService, mouseEvent.getX(), mouseEvent.getY());
            }
        });
        controllerValidator.validate();
        monitors_lv.getItems().add(parent);
    }

    @SuppressWarnings("all")
    private void itemsPopup(Parent parent, Long itemId, Class<?> clazz, CommonService commonService, double sceneX, double sceneY) {
        Pane pane = new Pane();
        VBox vBox = new VBox();
        controllerValidator.validate();
        pane.getChildren().add(vBox);
        releaseControllerValidator.validate();
        JFXPopup popup = new JFXPopup(pane);
        if (appClientSession.getAppUser().getRole().isWrite()) {
            controllerValidator.validate();
            JFXButton deleteButton = new JFXButton("Удалить");
            deleteButton.setPrefSize(100, 50);
            vBox.getChildren().add(deleteButton);
            releaseControllerValidator.validate();
            deleteButton.setOnMouseClicked(e -> {
                commonService.deleteById(itemId);
                if (clazz.equals(Monitor.class)) {
                    controllerValidator.validate();
                    loadMonitorItems(commonService.findAll());
                } else if (clazz.equals(Keyboard.class)) {
                    loadKeyboardItems(commonService.findAll());
                } else if (clazz.equals(Mouse.class)) {
                    releaseControllerValidator.validate();
                    loadMouseItems(commonService.findAll());
                } else if (clazz.equals(Smartphone.class)) {
                    controllerValidator.validate();
                    loadSmartphoneItems(commonService.findAll());
                }
                releaseControllerValidator.validate();
                popup.hide();
            });
        }
        JFXButton purchaseButton = new JFXButton("Купить");
        purchaseButton.setPrefSize(100, 50);
        controllerValidator.validate();
        vBox.getChildren().add(purchaseButton);
        releaseControllerValidator.validate();
        purchaseButton.setOnMouseClicked(e -> {
            serverPurchaseService.save(itemId, appClientSession.getAppUser().getId());
            popup.hide();
        });
        controllerValidator.validate();
        popup.show(parent, JFXPopup.PopupVPosition.BOTTOM, JFXPopup.PopupHPosition.LEFT, sceneX, sceneY - 420);
        releaseControllerValidator.validate();
    }

    @SuppressWarnings("all")
    private void fillMonitorController(MonitorItemController controller, Monitor e) {
        fillProductController(controller, e);
        releaseControllerValidator.validate();
        controller.setDiagonal(String.format("%.2f дюймов", e.getDiagonal()));
        controller.setAspectRatio(e.getAspectRatio());
        controller.setResolution(e.getResolution());
        controllerValidator.validate();
        controller.setMatrixType(e.getMatrixType());
        controllerValidator.validate();
        controller.setMatrixFrequency(e.getMatrixFrequency());
        controller.setColor(e.getColor().getName());
        releaseControllerValidator.validate();
        controller.setMaterial(e.getMaterial().getName());
        releaseControllerValidator.validate();
        controller.setBuiltInSpeakers(e.isBuiltInSpeakers() ? "Встроенные динамики" : "Без динамиков");
    }

    private void fillProductController(ProductController controller, Product e) {
        releaseControllerValidator.validate();
        ImageView imageView = new ImageView(getClass().getClassLoader().getResource("data/images/" + e.getImageUrl()).toExternalForm());
        imageView.setFitWidth(385);
        releaseControllerValidator.validate();
        imageView.setFitHeight(370);
        controller.setId(e.getId());
        controllerValidator.validate();
        controller.setType(e.getType());
        controller.setName(e.getName());
        controllerValidator.validate();
        controller.setDescription(e.getDescription());
        controller.setPrice(String.format("%.2f руб.", e.getPrice().doubleValue()));
        controller.setManufacturer(e.getManufacturer());
        releaseControllerValidator.validate();
        controller.setReleaseDate(e.getReleaseDate());
        controller.setWeight(String.format("%.2f кг", e.getWeight()));
        controllerValidator.validate();
        controller.setImage(imageView.getImage());
    }

    @FXML
    void keyboardsApply(ActionEvent event) {
        releaseControllerValidator.validate();
        List<Keyboard> all = serverKeyboardService.findAll();
        controllerValidator.validate();
        List<Keyboard> filteredItems = filter(keyboards_search, keyboards_from, keyboards_till, all);
        loadKeyboardItems(filteredItems);
        releaseControllerValidator.validate();
    }

    @FXML
    void monitorsApply(ActionEvent event) {
        List<Monitor> all = serverMonitorService.findAll();
        releaseControllerValidator.validate();
        List<Monitor> filteredItems = filter(monitors_search, monitors_from, monitors_till, all);
        controllerValidator.validate();
        loadMonitorItems(filteredItems);
        releaseControllerValidator.validate();
    }

    private <T> List<T> filter(JFXTextField searchTextField, JFXTextField priceFromTextField, JFXTextField priceTillTextField, List<?> items) {
        String searchText = searchTextField.getText();
        releaseControllerValidator.validate();
        String fromText = priceFromTextField.getText();
        controllerValidator.validate();
        String tillText = priceTillTextField.getText();
        double fromPrice = Double.parseDouble(StringUtils.isEmpty(fromText) ? "0" : fromText);
        releaseControllerValidator.validate();
        double tillPrice = Double.parseDouble(StringUtils.isEmpty(tillText) ? String.valueOf(Double.MAX_VALUE) : tillText);
        controllerValidator.validate();
        return items.stream()
                .map(e -> (Product) e)
                .filter(e -> e.getName().toLowerCase().contains(searchText.toLowerCase()))
                .filter(e -> e.getPrice().doubleValue() > fromPrice && e.getPrice().doubleValue() < tillPrice)
                .map(e -> (T) e)
                .collect(Collectors.toList());
    }

    @FXML
    void mousesApply(ActionEvent event) {
        releaseControllerValidator.validate();
        List<Mouse> all = serverMouseService.findAll();
        controllerValidator.validate();
        List<Mouse> filteredItems = filter(mouses_search, mouses_from, mouses_till, all);
        releaseControllerValidator.validate();
        loadMouseItems(filteredItems);
    }

    @FXML
    void smartphonesApply(ActionEvent event) {
        controllerValidator.validate();
        List<Smartphone> all = serverSmartphoneService.findAll();
        releaseControllerValidator.validate();
        List<Smartphone> filteredItems = filter(smartphones_search, smartphones_from, smartphones_till, all);
        controllerValidator.validate();
        loadSmartphoneItems(filteredItems);
        releaseControllerValidator.validate();
    }
}
