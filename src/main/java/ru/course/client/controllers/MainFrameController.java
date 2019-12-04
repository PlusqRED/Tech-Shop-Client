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
import ru.course.client.auth.UserSession;
import ru.course.client.models.product.*;
import ru.course.client.models.product.commons.Purchase;
import ru.course.client.models.statistic.StatisticItem;
import ru.course.client.models.users.Role;
import ru.course.client.services.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Controller
@RequiredArgsConstructor
public class MainFrameController {

    private final MonitorService monitorService;
    private final MouseService mouseService;
    private final KeyboardService keyboardService;
    private final SmartphoneService smartphoneService;
    private final MonitorAddController monitorAddController;
    private final KeyboardAddController keyboardAddController;
    private final MouseAddController mouseAddController;
    private final SmartphoneAddController smartphoneAddController;
    private final UserSession userSession;
    private final PurchaseService purchaseService;

    @Setter
    private Stage stage;

    @FXML
    private JFXListView<Parent> monitors_lv;

    @FXML
    private JFXTextField monitors_search;

    @FXML
    private JFXTextField monitors_from;

    @FXML
    private JFXTextField monitors_till;

    @FXML
    private JFXListView<Parent> mouses_lv;

    @FXML
    private JFXTextField mouses_search;

    @FXML
    private JFXTextField mouses_from;

    @FXML
    private JFXTextField mouses_till;

    @FXML
    private JFXListView<Parent> keyboards_lv;

    @FXML
    private JFXTextField keyboards_search;

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
    private JFXTextField smartphones_till;

    @FXML
    private JFXComboBox<String> addProductComboBox;

    @FXML
    private Pane addPane;

    @FXML
    private Tab adminStatistic;

    @FXML
    private Tab seller_admin_add_product;

    @FXML
    private TableView<StatisticItem> statistic_tv;

    @FXML
    private JFXTextField topProductName;

    @FXML
    private JFXTextField topCustomerName;

    @FXML
    private JFXTextField totlalRevenue;

    public void initialize() {
        Role role = userSession.getAppUser().getRole();
        if (role.isRead() && !role.isWrite()) {
            adminStatistic.setDisable(true);
            seller_admin_add_product.setDisable(true);
        } else if (role.isWrite() && role.isRead()) {
            loadStatisticItems();
        }
        loadMonitorItems(monitorService.findAll());
        loadKeyboardItems(keyboardService.findAll());
        loadMouseItems(mouseService.findAll());
        loadSmartphoneItems(smartphoneService.findAll());
        addProductComboBox.getItems().addAll("Монитор", "Клавиатура", "Мышь", "Смартфон");
        addProductComboBox.setOnAction(e -> {
            renderProperPane(addProductComboBox.getSelectionModel().getSelectedItem());
        });
    }

    @FXML
    void sortKeyboardsByPrice(ActionEvent event) {

    }

    @FXML
    void sortMonitorsByPrice(ActionEvent event) {

    }

    @FXML
    void sortMousesByPrice(ActionEvent event) {

    }

    @FXML
    void sortSmartphonesByPrice(ActionEvent event) {

    }

    @FXML
    void printMonitorsDoc() {
        Stage docStage = new Stage();
        docStage.setTitle("Отчет");
        Pane pane = new Pane();
        pane.setPrefSize(600, 600);
        List<Monitor> monitors = monitorService.findAll();
        String productInfo = monitors.stream().map(Monitor::toString).collect(joining("\n"));
        JFXTextArea textArea = new JFXTextArea(productInfo);
        textArea.setPrefSize(600, 600);
        pane.getChildren().add(textArea);
        docStage.setScene(new Scene(pane));
        docStage.showAndWait();
    }

    @FXML
    void printMousesDoc() {
        Stage docStage = new Stage();
        docStage.setTitle("Отчет");
        Pane pane = new Pane();
        pane.setPrefSize(600, 600);
        List<Mouse> mouseList = mouseService.findAll();
        String productInfo = mouseList.stream().map(Mouse::toString).collect(joining("\n"));
        JFXTextArea textArea = new JFXTextArea(productInfo);
        textArea.setPrefSize(600, 600);
        pane.getChildren().add(textArea);
        docStage.setScene(new Scene(pane));
        docStage.showAndWait();
    }

    @FXML
    void printKeyboardsDoc() {
        Stage docStage = new Stage();
        docStage.setTitle("Отчет");
        Pane pane = new Pane();
        pane.setPrefSize(600, 600);
        List<Keyboard> keyboards = keyboardService.findAll();
        String productInfo = keyboards.stream().map(Keyboard::toString).collect(joining("\n"));
        JFXTextArea textArea = new JFXTextArea(productInfo);
        textArea.setPrefSize(600, 600);
        pane.getChildren().add(textArea);
        docStage.setScene(new Scene(pane));
        docStage.showAndWait();
    }

    @FXML
    void printSmartphonesDoc() {
        Stage docStage = new Stage();
        docStage.setTitle("Отчет");
        Pane pane = new Pane();
        pane.setPrefSize(600, 600);
        List<Smartphone> smartphones = smartphoneService.findAll();
        String productInfo = smartphones.stream().map(Smartphone::toString).collect(joining("\n"));
        JFXTextArea textArea = new JFXTextArea(productInfo);
        textArea.setPrefSize(600, 600);
        pane.getChildren().add(textArea);
        docStage.setScene(new Scene(pane));
        docStage.showAndWait();
    }

    private void loadStatisticItems() {
        statistic_tv.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("dealDate"));
        statistic_tv.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("productName"));
        statistic_tv.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("dealPrice"));
        statistic_tv.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        updateStatisticItems();
    }

    @FXML
    private void updateStatisticItems() {
        List<StatisticItem> statisticItems = purchaseService.findAll().stream()
                .map(Purchase::toStatisticItem)
                .collect(Collectors.toList());
        statistic_tv.setItems(FXCollections.observableList(statisticItems));
        updateTopCustomerStatisticField(statisticItems);
        updateTotalRevenueStatisticField(statisticItems);
        updateTopProductStatisticField(statisticItems);
    }

    @SuppressWarnings("all")
    private void updateTopProductStatisticField(List<StatisticItem> statisticItems) {
        Map<String, Long> productNameAndSellCount = statisticItems.stream()
                .collect(groupingBy(StatisticItem::getProductName, counting()));
        productNameAndSellCount.values().stream()
                .mapToLong(Long::valueOf)
                .max()
                .ifPresent(value -> topProductName.setText(productNameAndSellCount.entrySet().stream()
                        .filter(entry -> entry.getValue().equals(value))
                        .map(Map.Entry::getKey)
                        .findAny()
                        .orElse("На данный момент отстутсвует")));
    }

    private void updateTotalRevenueStatisticField(List<StatisticItem> statisticItems) {
        totlalRevenue.setText(String.format("%.2f руб.", statisticItems.stream().map(StatisticItem::getDealPrice).mapToDouble(Double::valueOf).sum()));
    }

    @SuppressWarnings("all")
    private void updateTopCustomerStatisticField(List<StatisticItem> statisticItems) {
        Map<String, Long> customerNameAndCountOfCustomerProducts = statisticItems.stream()
                .collect(groupingBy(StatisticItem::getCustomerName, counting()));
        customerNameAndCountOfCustomerProducts.values().stream()
                .mapToLong(Long::valueOf)
                .max()
                .ifPresent(value -> topCustomerName.setText(customerNameAndCountOfCustomerProducts.entrySet().stream()
                        .filter(entry -> entry.getValue().equals(value))
                        .map(Map.Entry::getKey)
                        .findAny()
                        .orElse("На данный момент отстутсвует")));
    }

    @SneakyThrows
    private void renderProperPane(String selectedItem) {
        addPane.getChildren().clear();
        switch (selectedItem) {
            case "Монитор": {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/monitor_add.fxml"));
                loader.setController(monitorAddController);
                monitorAddController.setParentController(this);
                addPane.getChildren().add(loader.load());
            }
            break;
            case "Клавиатура": {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/keyboard_add.fxml"));
                loader.setController(keyboardAddController);
                keyboardAddController.setParentController(this);
                addPane.getChildren().add(loader.load());
            }
            break;
            case "Мышь": {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mouse_add.fxml"));
                loader.setController(mouseAddController);
                mouseAddController.setParentController(this);
                addPane.getChildren().add(loader.load());
            }
            break;
            case "Смартфон": {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/smartphone_add.fxml"));
                loader.setController(smartphoneAddController);
                smartphoneAddController.setParentController(this);
                addPane.getChildren().add(loader.load());
            }
            break;
        }
    }

    public void loadSmartphoneItems(List<Smartphone> all) {
        smartphones_lv.getItems().clear();
        all.forEach(this::renderSmartphoneItem);
    }

    @SneakyThrows
    private void renderSmartphoneItem(Smartphone smartphone) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/smartphones_list_view_item.fxml"));
        SmartphoneListViewItemController controller = Launcher.context.getBean("smartphoneListViewItemController", SmartphoneListViewItemController.class);
        loader.setController(controller);
        Parent parent = loader.load();
        fillSmartphoneController(controller, smartphone);
        parent.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                itemsPopup(parent, smartphone.getId(), Smartphone.class, smartphoneService, mouseEvent.getX(), mouseEvent.getY());
            }
        });
        smartphones_lv.getItems().add(parent);
    }

    private void fillSmartphoneController(SmartphoneListViewItemController controller, Smartphone smartphone) {
        fillProductController(controller, smartphone);
        controller.setOs(smartphone.getOs());
        controller.setDisplaySize(String.format("%.2f дюймов", smartphone.getDisplaySize()));
        controller.setRam(smartphone.getRam());
        controller.setNumberOfBackCameras(String.format("%d задних камер", smartphone.getNumberOfBackCameras()));
        controller.setNumberOfFrontCameras(String.format("%d фронт. камер", smartphone.getNumberOfFrontCameras()));
    }

    public void loadMouseItems(List<Mouse> all) {
        mouses_lv.getItems().clear();
        all.forEach(this::renderMouseItem);
    }

    @SneakyThrows
    private void renderMouseItem(Mouse mouse) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mouses_list_view_item.fxml"));
        MouseListViewItemController controller = Launcher.context.getBean("mouseListViewItemController", MouseListViewItemController.class);
        loader.setController(controller);
        Parent parent = loader.load();
        fillMouseController(controller, mouse);
        parent.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                itemsPopup(parent, mouse.getId(), Mouse.class, mouseService, mouseEvent.getX(), mouseEvent.getY());
            }
        });
        mouses_lv.getItems().add(parent);
    }

    private void fillMouseController(MouseListViewItemController controller, Mouse mouse) {
        fillProductController(controller, mouse);
        controller.setConnectionType(mouse.getConnectionType());
        controller.setSensorType(mouse.getSensorType());
        controller.setButtonAmount(String.format("%d кнопок", mouse.getButtonAmount()));
    }

    public void loadKeyboardItems(List<Keyboard> all) {
        keyboards_lv.getItems().clear();
        all.forEach(this::renderKeyboardItem);
    }

    @SneakyThrows
    private void renderKeyboardItem(Keyboard keyboard) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/keyboards_list_view_item.fxml"));
        KeyboardListViewItemController controller = Launcher.context.getBean("keyboardListViewItemController", KeyboardListViewItemController.class);
        loader.setController(controller);
        Parent parent = loader.load();
        fillKeyboardController(controller, keyboard);
        parent.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                itemsPopup(parent, keyboard.getId(), Keyboard.class, keyboardService, mouseEvent.getX(), mouseEvent.getY());
            }
        });
        keyboards_lv.getItems().add(parent);
    }

    private void fillKeyboardController(KeyboardListViewItemController controller, Keyboard keyboard) {
        fillProductController(controller, keyboard);
        controller.setConnectionType(keyboard.getConnectionType());
        controller.setBacklight(keyboard.isBacklight() ? "Есть подсветка" : "Нет подсветки");
        controller.setMoistureProtection(keyboard.isMoistureProtection() ? "Влагоустойчива" : "Влагоуязвима");
    }

    @SneakyThrows
    public void loadMonitorItems(List<Monitor> all) {
        monitors_lv.getItems().clear();
        all.forEach(this::renderMonitorItem);
    }

    @SneakyThrows
    private void renderMonitorItem(Monitor monitor) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/monitors_list_view_item.fxml"));
        MonitorListViewItemController controller = Launcher.context.getBean("monitorListViewItemController", MonitorListViewItemController.class);
        loader.setController(controller);
        Parent parent = loader.load();
        fillMonitorController(controller, monitor);
        parent.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                itemsPopup(parent, monitor.getId(), Monitor.class, monitorService, mouseEvent.getX(), mouseEvent.getY());
            }
        });
        monitors_lv.getItems().add(parent);
    }

    @SuppressWarnings("all")
    private void itemsPopup(Parent parent, Long itemId, Class<?> clazz, CommonService commonService, double sceneX, double sceneY) {
        Pane pane = new Pane();
        VBox vBox = new VBox();
        pane.getChildren().add(vBox);
        JFXPopup popup = new JFXPopup(pane);
        if (userSession.getAppUser().getRole().isWrite()) {
            JFXButton deleteButton = new JFXButton("Удалить");
            deleteButton.setPrefSize(100, 50);
            vBox.getChildren().add(deleteButton);
            deleteButton.setOnMouseClicked(e -> {
                commonService.deleteById(itemId);
                if (clazz.equals(Monitor.class)) {
                    loadMonitorItems(commonService.findAll());
                } else if (clazz.equals(Keyboard.class)) {
                    loadKeyboardItems(commonService.findAll());
                } else if (clazz.equals(Mouse.class)) {
                    loadMouseItems(commonService.findAll());
                } else if (clazz.equals(Smartphone.class)) {
                    loadSmartphoneItems(commonService.findAll());
                }
                popup.hide();
            });
        }
        JFXButton purchaseButton = new JFXButton("Купить");
        purchaseButton.setPrefSize(100, 50);
        vBox.getChildren().add(purchaseButton);
        purchaseButton.setOnMouseClicked(e -> {
            purchaseService.save(itemId, userSession.getAppUser().getId());
            popup.hide();
        });
        popup.show(parent, JFXPopup.PopupVPosition.BOTTOM, JFXPopup.PopupHPosition.LEFT, sceneX, sceneY - 420);
    }

    @SuppressWarnings("all")
    private void fillMonitorController(MonitorListViewItemController controller, Monitor e) {
        fillProductController(controller, e);
        controller.setDiagonal(String.format("%.2f дюймов", e.getDiagonal()));
        controller.setAspectRatio(e.getAspectRatio());
        controller.setResolution(e.getResolution());
        controller.setMatrixType(e.getMatrixType());
        controller.setMatrixFrequency(e.getMatrixFrequency());
        controller.setColor(e.getColor().getName());
        controller.setMaterial(e.getMaterial().getName());
        controller.setBuiltInSpeakers(e.isBuiltInSpeakers() ? "Встроенные динамики" : "Без динамиков");
    }

    private void fillProductController(ProductController controller, Product e) {
        ImageView imageView = new ImageView(getClass().getClassLoader().getResource("data/images/" + e.getImageUrl()).toExternalForm());
        imageView.setFitWidth(385);
        imageView.setFitHeight(370);
        controller.setId(e.getId());
        controller.setType(e.getType());
        controller.setName(e.getName());
        controller.setDescription(e.getDescription());
        controller.setPrice(String.format("%.2f руб.", e.getPrice().doubleValue()));
        controller.setManufacturer(e.getManufacturer());
        controller.setReleaseDate(e.getReleaseDate());
        controller.setWeight(String.format("%.2f кг", e.getWeight()));
        controller.setImage(imageView.getImage());
    }

    @FXML
    void keyboardsApply(ActionEvent event) {
        List<Keyboard> all = keyboardService.findAll();
        List<Keyboard> filteredItems = filter(keyboards_search, keyboards_from, keyboards_till, all);
        loadKeyboardItems(filteredItems);
    }

    @FXML
    void monitorsApply(ActionEvent event) {
        List<Monitor> all = monitorService.findAll();
        List<Monitor> filteredItems = filter(monitors_search, monitors_from, monitors_till, all);
        loadMonitorItems(filteredItems);
    }

    private <T> List<T> filter(JFXTextField searchTextField, JFXTextField priceFromTextField, JFXTextField priceTillTextField, List<?> items) {
        String searchText = searchTextField.getText();
        String fromText = priceFromTextField.getText();
        String tillText = priceTillTextField.getText();
        double fromPrice = Double.parseDouble(StringUtils.isEmpty(fromText) ? "0" : fromText);
        double tillPrice = Double.parseDouble(StringUtils.isEmpty(tillText) ? String.valueOf(Double.MAX_VALUE) : tillText);

        return items.stream()
                .map(e -> (Product) e)
                .filter(e -> e.getName().toLowerCase().contains(searchText.toLowerCase()))
                .filter(e -> e.getPrice().doubleValue() > fromPrice && e.getPrice().doubleValue() < tillPrice)
                .map(e -> (T) e)
                .collect(Collectors.toList());
    }

    @FXML
    void mousesApply(ActionEvent event) {
        List<Mouse> all = mouseService.findAll();
        List<Mouse> filteredItems = filter(mouses_search, mouses_from, mouses_till, all);
        loadMouseItems(filteredItems);
    }

    @FXML
    void smartphonesApply(ActionEvent event) {
        List<Smartphone> all = smartphoneService.findAll();
        List<Smartphone> filteredItems = filter(smartphones_search, smartphones_from, smartphones_till, all);
        loadSmartphoneItems(filteredItems);
    }
}
