comp1406a3;

import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import java.util.ArrayList;

public class ElectronicStoreView extends Pane {
    private Button addItemButt, completeSaleButt, removeItemButt, resetStoreButt;
    private ListView<Product> stockListView, cartListView, popularListView;
    private TextField saleField, revenueField, averageField;
    private Label priceLabel;
    private ElectronicStore model;

    public ElectronicStoreView(ElectronicStore model){
        this.model = model;

        //Field
        saleField = new TextField("0");
        saleField.setEditable(false);
        saleField.setMouseTransparent(true);
        saleField.setFocusTraversable(false);
        saleField.relocate(105, 70);
        saleField.setPrefSize(105, 30);

        revenueField = new TextField("$0.00");
        revenueField.setEditable(false);
        revenueField.setMouseTransparent(true);
        revenueField.setFocusTraversable(false);
        revenueField.relocate(105, 110);
        revenueField.setPrefSize(105, 30);

        averageField = new TextField("N/A");
        averageField.setEditable(false);
        averageField.setMouseTransparent(true);
        averageField.setFocusTraversable(false);
        averageField.relocate(105, 150);
        averageField.setPrefSize(105, 30);

        //List
        popularListView = new ListView<>();
        popularListView.setFocusTraversable(false);
        popularListView.setMouseTransparent(true);
        popularListView.relocate(35, 210);
        popularListView.setPrefSize(175, 105);

        stockListView = new ListView<>();
        stockListView.setFocusTraversable(false);
        stockListView.relocate(227.5, 70);
        stockListView.setPrefSize(280, 245);

        cartListView = new ListView<>();
        cartListView.setFocusTraversable(false);
        cartListView.relocate(525, 70);
        cartListView.setPrefSize(280, 245);

        //Button
        resetStoreButt = new Button("Reset Store");
        resetStoreButt.setFocusTraversable(false);
        resetStoreButt.relocate(52.5, 320);
        resetStoreButt.setPrefSize(140, 65);

        addItemButt = new Button("Add to Cart");
        addItemButt.setFocusTraversable(false);
        addItemButt.relocate(297.5, 320);
        addItemButt.setPrefSize(140, 65);

        removeItemButt = new Button("Remove from Cart");
        removeItemButt.setFocusTraversable(false);
        removeItemButt.relocate(525, 320);
        removeItemButt.setPrefSize(140, 65);

        completeSaleButt = new Button("Complete Sale");
        completeSaleButt.setFocusTraversable(false);
        completeSaleButt.relocate(665, 320);
        completeSaleButt.setPrefSize(140, 65);

        //Label
        Label tag1 = new Label("Store Summary:");
        tag1.relocate(80.5, 35);
        tag1.setPrefSize(84, 30);
        getChildren().add(tag1);

        Label tag2 = new Label("Store Stock:");
        tag2.relocate(335.5, 35);
        tag2.setPrefSize(64, 30);
        getChildren().add(tag2);

        Label tag3 = new Label("Current Cart");
        tag3.relocate(616, 35);
        tag3.setPrefSize(68, 30);
        getChildren().add(tag3);

        Label tag4 = new Label("# Sales:");
        tag4.relocate(60, 70);
        tag4.setPrefSize(40, 30);
        getChildren().add(tag4);

        Label tag5 = new Label("Revenue:");
        tag5.relocate(52, 110);
        tag5.setPrefSize(48, 30);
        getChildren().add(tag5);

        Label tag6 = new Label("$ / Sale:");
        tag6.relocate(58, 150);
        tag6.setPrefSize(42, 30);
        getChildren().add(tag6);

        Label tag7 = new Label("Most Popular Items:");
        tag7.relocate(69.5, 180);
        tag7.setPrefSize(106, 30);
        getChildren().add(tag7);

        priceLabel = new Label("($0.00):");
        priceLabel.relocate(685, 35);
        priceLabel.setPrefSize(68, 30);

        getChildren().addAll(saleField, revenueField, averageField, stockListView, cartListView, resetStoreButt,
                addItemButt, removeItemButt, completeSaleButt, popularListView, priceLabel);

        update();
    }
    // Getters and a setter
    public void setModel(ElectronicStore model){ this.model = model; }

    public Button getAddItemButt() { return addItemButt; }

    public Button getCompleteSaleButt() { return completeSaleButt; }

    public Button getRemoveItemButt() { return removeItemButt; }

    public Button getResetStoreButt() { return resetStoreButt; }

    public ListView<Product> getStockListView() { return stockListView; }

    public ListView<Product> getCartListView() { return cartListView; }

    public ListView<Product> getPopularListView() { return popularListView; }

    public Label getPriceLabel(){ return priceLabel; }

    public TextField getSaleField() { return saleField; }

    public TextField getRevenueField() { return revenueField; }

    public TextField getAverageField() { return averageField; }

    // this method will update the StockListView and the buttons when it is called
    public void update(){
        ArrayList<Product> products = new ArrayList<>(); // this 'item' ArrayList is used to hold in-stock products
        for(int i = 0; i < model.getCurProducts(); i++){
            // ↓↓↓ this line does the magic, disable it to see that all the out-of-stock items are still there
            if (model.getItem()[i].getStockQuantity() != 0)
                products.add(model.getItem()[i]);
        }
        // only display in-stock products onto the UI, out-of-stock products will be hid from sight (aka invisible)
        stockListView.setItems(FXCollections.observableArrayList(products));

        int selection = stockListView.getSelectionModel().getSelectedIndex();   // get index of stock list
        int choosing = cartListView.getSelectionModel().getSelectedIndex();     // get index of cart list

        // Buttons' reaction
        if (selection != -1)                     // 'Add to Cart' button
            addItemButt.setDisable(false);       // Enabled only when an item in Stock list is selected, else off
        else
            addItemButt.setDisable(true);

        if (!model.getCartItem().isEmpty())      // 'Complete Sale' button
            completeSaleButt.setDisable(false);  // Enabled only when there's item in Cart list, else off
        else
            completeSaleButt.setDisable(true);

        if (choosing != -1)                      // 'Remove from Cart' button
            removeItemButt.setDisable(false);    // Enabled only when an item in Cart list is selected, else off
        else
            removeItemButt.setDisable(true);
    }
}
