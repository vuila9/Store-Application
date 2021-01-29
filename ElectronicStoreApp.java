/* Designed by
 * Name: John Nguyen
 * ID: 101 162 387
**/
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.Comparator;

public class ElectronicStoreApp extends Application {
    private ElectronicStoreView view;
    // when a product is out-of-stock, they will be pushed down to the bottom of the list and are hid from sight on the
    // UI, the in-stock ones remain being displayed on UI while also being sorted based on their ID
    private ElectronicStore model;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);
        primaryStage.setTitle(model.getName());                   // set title of window
        primaryStage.setResizable(false);                         // prevent the application from being resizable
        primaryStage.setScene(new Scene(view, 840, 420));  // set size of window
        displayIniPopularList();
        primaryStage.show();

        // Handle when clicking at Stock list
        view.getStockListView().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) { view.update(); }
        });
        // Handle when clicking at Cart List
        view.getCartListView()  .setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) { view.update(); }
        });
        // Handle when click on "Add to Cart" Button
        view.getAddItemButt().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) { handleAddItem(); }
        });
        // Handle when click on "Complete Sale" Button
        view.getCompleteSaleButt().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) { handleCompleteSale(); }
        });
        // Handle when click on "Remove from Cart" Button
        view.getRemoveItemButt().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) { handleRemoveItem(); }
        });
        // Handle when click on "Reset Store" Button
        view.getResetStoreButt().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) { handleResetStore(); }
        });

        view.update();
    }
    // ↓↓↓ this method will add selected item from Stock list to Cart list
    public void handleAddItem() {
        int index = view.getStockListView().getSelectionModel().getSelectedIndex();
        model.sellProducts(index, 1);
        // update cart list every time an item is added from stock list
        model.getCartItem().add(model.getItem()[index]);
        view.getCartListView().setItems((FXCollections.observableArrayList(model.getCartItem())));
        // update current price of items in cart
        view.getPriceLabel().setText(String.format("($%.2f)", model.getCartRevenue()));
        // once a selected item is out-of-stock, reorder the stock list
        if (model.getItem()[index].getStockQuantity() == 0)
            model.reOrderingStockList();

        view.update();
    }
    // ↓↓↓ this method will update the 'revenue', 'numSale', 'popItem', and erase the Cart List
    public void handleCompleteSale() {
        // reset cart list, increase number of sale by 1
        model.getCartItem().clear();
        view.getCartListView().setItems(FXCollections.observableArrayList());   // reset Cart List View
        model.setSaleCounter(model.getSaleCounter() + 1);
        view.getPriceLabel().setText("($0.00)");
        view.getSaleField().setText(String.valueOf(model.getSaleCounter()));
        // update total Revenue, then set the current price of items in cart list to 0
        model.setTotalRevenue(model.getTotalRevenue() + model.getCartRevenue());
        model.setCartRevenue(0.0);
        view.getRevenueField().setText(String.format("$%.2f", model.getTotalRevenue()));
        view.getAverageField().setText(String.format("$%.2f", (model.getTotalRevenue() / model.getSaleCounter())));
        // add all non-null items from 'stock' array to an ArrayList called 'popItem'
        for (int i = 0; i < model.getCurProducts(); i++) {
            model.getPopItem().add(model.getItem()[i]);
        }
        // ↓↓↓ This line will sort all product in the 'popItem' based on their sold quantity (descending order)
        model.getPopItem().sort(Comparator.comparingInt(Product::getSoldQuantity).reversed());
        // get only 3 most-sold items and store them in a temporary array, then display that array onto the UI
        Product[] temp = new Product[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = model.getPopItem().get(i);
        }
        view.getPopularListView().setItems(FXCollections.observableArrayList(temp));
        // reset Popular sold items list since we always have the Stock quantity of items change every time a sale is complete.
        model.getPopItem().clear();
        view.getStockListView().getSelectionModel().clearSelection();

        view.update();
    }
    // ↓↓↓ this method will remove selected item from cart and place it back to the Stock list, increase its stockQ by 1
    public void handleRemoveItem() {
        int index = view.getCartListView().getSelectionModel().getSelectedIndex();
        for (int i = 0; i < model.getCurProducts(); i++) {
            // when an item in cart list gets removed, increase the stock quantity of corresponding item in stock list by 1
            if (model.getItem()[i].equals(model.getCartItem().get(index))) {
                model.getItem()[i].setStockQuantity(model.getItem()[i].getStockQuantity() + 1);
                model.reOrderingStockList();
                break;
            }
        }
        // update current price of item in cart
        model.setCartRevenue((model.getCartRevenue() - model.getCartItem().get(index).getPrice()));
        view.getPriceLabel().setText(String.format("($%.2f)", model.getCartRevenue()));
        // remove item from cart list, update cart list view
        model.getCartItem().remove(index);
        view.getCartListView().setItems((FXCollections.observableArrayList(model.getCartItem())));

        view.update();
    }
    // ↓↓↓ this method will reset everything once called
    public void handleResetStore() {
        model = ElectronicStore.createStore();
        view.getSaleField().setText("0");
        view.getRevenueField().setText("$0.00");
        view.getAverageField().setText("N/A");
        view.getPriceLabel().setText("($0.00)");
        view.getStockListView().getSelectionModel().clearSelection();
        displayIniPopularList();
        view.getCartListView().setItems(FXCollections.observableArrayList());
        view.setModel(model);

        view.update();
    }
    // ↓↓↓ this method will display the initial Popular Items List
    public void displayIniPopularList() {
        Product[] temp = new Product[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = model.getItem()[i];
        }
        view.getPopularListView().setItems(FXCollections.observableArrayList(temp));
    }
}
